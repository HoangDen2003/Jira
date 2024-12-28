package com.jira.identity_service.configuration;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import com.jira.identity_service.entity.GlobalRole;
import com.jira.identity_service.entity.User;
import com.jira.identity_service.repository.GlobalRoleRepository;
import com.jira.identity_service.repository.InvalidatedTokenRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.context.annotation.Configuration;

import com.jira.identity_service.constant.ProviderJwt;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
// @EnableMethodSecurity
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtTokenProvider {
    ProviderJwt provider;
    private final GlobalRoleRepository globalRoleRepository;

    public String generateToken(User user) {
         //      Đây là một lớp đại diện cho phần "header" của một JSON Web Signature (JWS). Header này chứa thông tin về
         // thuật toán mã hóa và kiểu của token.
         JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

         //      Payload JWT
         JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                 .subject(user.getEmail())
                 .issuer("Hoang") // Thiết lập người phát hành (issuer) của JWT
                 .issueTime(new Date())
                 .expirationTime(new Date(Instant.now()
                         .plus(provider.VALID_DURATION, ChronoUnit.SECONDS)
                         .toEpochMilli()))
                 .jwtID(UUID.randomUUID().toString())
                 .claim("scope", buildScope(user))
                 .build();

         Payload payload = new Payload(jwtClaimsSet.toJSONObject());

         JWSObject jwsObject = new JWSObject(header, payload);

         try {
             jwsObject.sign(new MACSigner(provider.SIGNER_KEY.getBytes()));
             return jwsObject.serialize();
         } catch (JOSEException e) {
             log.error("Cannot create token", e);
             throw new RuntimeException(e);
         }
    }

    private String buildScope(User user) {
     if (user.getGlobal_role() == null) return "ROLE_GUEST";
     GlobalRole globalRole = globalRoleRepository.findById(user.getGlobal_role().getId()).orElse(null);
     // Nếu không tìm thấy GlobalRole từ DB, trả về null (có thể bạn cần xử lý trường hợp này ở nơi khác)
     if (globalRole == null) {
         return "ROLE_GUEST";  // Giá trị mặc định, có thể thay đổi tùy theo yêu cầu
     }

     // Chuyển dấu cách trong tên GlobalRole thành dấu "_"
     return "ROLE_" + globalRole.getName().replace(" ", "_");
    }
}
