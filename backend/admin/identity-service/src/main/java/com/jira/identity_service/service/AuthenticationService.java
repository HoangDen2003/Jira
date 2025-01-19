package com.jira.identity_service.service;

import java.text.ParseException;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jira.identity_service.configuration.JwtTokenProvider;
import com.jira.identity_service.dto.request.LogoutRequest;
import com.jira.identity_service.dto.request.RefreshRequest;
import com.jira.identity_service.dto.request.UserRequest;
import com.jira.identity_service.dto.response.AuthenticationResponse;
import com.jira.identity_service.dto.response.UserTokenResponse;
import com.jira.identity_service.entity.InvalidatedToken;
import com.jira.identity_service.entity.User;
import com.jira.identity_service.exception.AppException;
import com.jira.identity_service.exception.ErrorCode;
import com.jira.identity_service.mapper.UserMapper;
import com.jira.identity_service.repository.InvalidatedTokenRepository;
import com.jira.identity_service.repository.UserRepository;
import com.nimbusds.jose.JOSEException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {

    UserRepository userRepository;
    InvalidatedTokenRepository invalidatedTokenRepository;
    UserMapper userMapper;
    JwtTokenProvider jwtTokenProvider;

    public AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException {
        var signedJWT = jwtTokenProvider.verifyToken(request.getToken(), true);
        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        InvalidatedToken invalidatedToken =
                InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();
        invalidatedTokenRepository.save(invalidatedToken);
        var email = signedJWT.getJWTClaimsSet().getSubject();
        var user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        var token = jwtTokenProvider.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }

    public UserTokenResponse signIn(UserRequest userRequest) {

        User user = userRepository
                .findByEmail(userRequest.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        boolean authenticated = passwordEncoder.matches(userRequest.getPassword(), user.getPassword());
        if (!authenticated) throw new AppException(ErrorCode.USER_NOT_EXISTED);

        // generate token
        var token = jwtTokenProvider.generateToken(user);

        InvalidatedToken invalidatedToken = new InvalidatedToken();
        invalidatedToken.setId(token);

        return userMapper.toUserTokenRepsonse(user, invalidatedToken);
    }

    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        try {
            var signToken = jwtTokenProvider.verifyToken(request.getToken(), true);

            String jit = signToken.getJWTClaimsSet().getJWTID();
            Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

            InvalidatedToken invalidatedToken =
                    InvalidatedToken.builder().id(jit).expiryTime(expiryTime).build();
            invalidatedTokenRepository.save(invalidatedToken);

        } catch (AppException exception) {
            log.info("Token already expired");
        }
    }

    //    public UserResponse rePassword(UserRequest userRequest) {}

}
