package com.jira.identity_service.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import com.jira.identity_service.entity.GlobalRole;
import com.jira.identity_service.entity.InvalidatedToken;
import com.jira.identity_service.entity.Profile;
import com.jira.identity_service.mapper.ProfileMapper;
import com.jira.identity_service.repository.GlobalRoleRepository;
import com.jira.identity_service.repository.ProfileReposity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.jira.identity_service.configuration.JwtTokenProvider;
import com.jira.identity_service.dto.request.UserProfileCreationRequest;
import com.jira.identity_service.dto.request.UserRequest;
import com.jira.identity_service.dto.response.UserResponse;
import com.jira.identity_service.dto.response.UserTokenResponse;
import com.jira.identity_service.entity.User;
import com.jira.identity_service.exception.AppException;
import com.jira.identity_service.exception.ErrorCode;
import com.jira.identity_service.mapper.UserMapper;
import com.jira.identity_service.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@Slf4j
public class UserService {
    UserRepository userRepository;
    GlobalRoleRepository globalRoleRepository;
    ProfileReposity profileReposity;

    UserMapper userMapper;
    ProfileMapper profileMapper;
    JwtTokenProvider jwtTokenProvider;

    public UserResponse createUser(UserProfileCreationRequest userProfileCreationRequester) {
        User user = userMapper.toUser(userProfileCreationRequester);
        Profile profile = profileMapper.toProfile(userProfileCreationRequester);
        profile.setUser(user);

        GlobalRole globalRole = globalRoleRepository.findById(userProfileCreationRequester.getGlobal_role_id()).orElse(null);
        user.setGlobal_role(globalRole);

        user = userRepository.save(user);
        profile = profileReposity.save(profile);
        return userMapper.toUserResponse(user, profile);
    }

    public UserTokenResponse signIn(UserRequest userRequest) {
        User user = userRepository
                .findByEmail(userRequest.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // generate token
        var token = jwtTokenProvider.generateToken(user);
        InvalidatedToken invalidatedToken = new InvalidatedToken();
        invalidatedToken.setToken(token);

        return userMapper.toUserTokenRepsonse(user, invalidatedToken);
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    public UserResponse getUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
//        return userMapper.toUserResponse(user);

        log.info("User id: {}", user.getId());
        log.info("User email: {}", user.getEmail());

        Profile profile = profileReposity.findProfileWithUserByUserId(userId).orElse(null);

        return userMapper.toUserResponse(user, profile);
    }

    public List<UserResponse> getAllUsers() {
        var users = userRepository.findAll().stream().toList();
        return users.stream()
                .map( (user -> {
                    Profile profile = profileReposity.findProfileWithUserByUserId(user.getId()).orElse(null);
                    return userMapper.toUserResponse(user, profile);
                }) )
                .toList();
    }

    @PreAuthorize("hasRole('ROLE_SYSTEM_ADMIN')")
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        userRepository.delete(user);
    }

    public UserResponse updateUser(Integer userId, UserProfileCreationRequest userProfileCreationRequest) {
        // entity
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Profile profile = profileReposity.findProfileWithUserByUserId(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        // update user
        user.setEmail(user.getEmail().equals(userProfileCreationRequest.getEmail()) ? user.getEmail() : userProfileCreationRequest.getEmail());
        user.setPassword(
                user.getPassword().equals(userProfileCreationRequest.getPassword()) ? user.getPassword() : userProfileCreationRequest.getPassword());
        user.setUpdated_at(Timestamp.from(Instant.now()));

        GlobalRole globalRole = globalRoleRepository.findById(userProfileCreationRequest.getGlobal_role_id()).orElse(null);

        user.setGlobal_role(user.getGlobal_role().equals(globalRole) ? user.getGlobal_role() : globalRole);

        // update profil
        profile.setFull_name(profile.getFull_name().equals(userProfileCreationRequest.getFull_name()) ? profile.getFull_name() : userProfileCreationRequest.getFull_name());
        profile.setAddress(profile.getAddress().equals(userProfileCreationRequest.getAddress()) ? profile.getAddress() : userProfileCreationRequest.getAddress());
        profile.setPhone(profile.getPhone().equals(userProfileCreationRequest.getPhone()) ? profile.getPhone() : userProfileCreationRequest.getPhone());
        profile.setBirthday(profile.getBirthday().equals(userProfileCreationRequest.getBirthday()) ? profile.getBirthday() : userProfileCreationRequest.getBirthday());
        profile.setGender(profile.getGender().equals(userProfileCreationRequest.getGender()) ? profile.getGender() : userProfileCreationRequest.getGender());

        user = userRepository.save(user);
        profile = profileReposity.save(profile);
        return userMapper.toUserResponse(user, profile);
    }
}
