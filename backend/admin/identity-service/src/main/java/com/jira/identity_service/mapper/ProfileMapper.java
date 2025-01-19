package com.jira.identity_service.mapper;

import org.mapstruct.Mapper;

import com.jira.identity_service.dto.request.UserProfileCreationRequest;
import com.jira.identity_service.dto.response.ProfileResponse;
import com.jira.identity_service.entity.Profile;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toProfile(UserProfileCreationRequest userProfileCreationRequest);

    ProfileResponse toProfileResponse(Profile profile);
}
