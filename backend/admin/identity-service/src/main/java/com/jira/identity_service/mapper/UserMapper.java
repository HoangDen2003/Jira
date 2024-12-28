package com.jira.identity_service.mapper;

import com.jira.identity_service.dto.response.ProfileResponse;
import com.jira.identity_service.dto.response.RoleResponse;
import com.jira.identity_service.entity.GlobalRole;
import com.jira.identity_service.entity.InvalidatedToken;
import com.jira.identity_service.entity.Profile;
import org.mapstruct.Mapper;

import com.jira.identity_service.dto.request.UserProfileCreationRequest;
import com.jira.identity_service.dto.response.UserResponse;
import com.jira.identity_service.dto.response.UserTokenResponse;
import com.jira.identity_service.entity.User;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.Set;

@Mapper(componentModel = "spring", uses = GlobalRoleMapper.class)
public interface UserMapper {
    @Mapping(target = "global_role", ignore = true)
    User toUser(UserProfileCreationRequest userProfileCreationRequest);

    @Mapping(target = "roles", expression = "java(mapGlobalRoleToRoles(user.getGlobal_role()))")  // Ánh xạ global_role thành roles
    UserResponse toUserResponse(User user, Profile profile);

    UserTokenResponse toUserTokenRepsonse(User user, InvalidatedToken invalidatedToken);

    // Phương thức ánh xạ GlobalRole sang Set<RoleResponse>
    default Set<RoleResponse> mapGlobalRoleToRoles(GlobalRole globalRole) {
        if (globalRole == null) {
            return Collections.emptySet();  // Nếu globalRole null, trả về Set rỗng
        }
        return Collections.singleton(new RoleResponse(globalRole.getName()));  // Chuyển GlobalRole thành RoleResponse và cho vào Set
    }

}
