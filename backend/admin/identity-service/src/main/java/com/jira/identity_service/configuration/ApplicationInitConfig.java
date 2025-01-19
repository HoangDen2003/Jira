package com.jira.identity_service.configuration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jira.identity_service.entity.GlobalRole;
import com.jira.identity_service.entity.User;
import com.jira.identity_service.repository.GlobalRoleRepository;
import com.jira.identity_service.repository.ProfileReposity;
import com.jira.identity_service.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String SYSTEM_ADMIN_GLOBAL_ROLE = "SYSTEM ADMIN";

    @NonFinal
    static final String SYSTEM_ADMIN_PASSWORD = "admin";

    @NonFinal
    static final String SYSTEM_ADMIN_EMAIL = "admin@admin.com";

    @NonFinal
    static final String SYSTEM_ADMIN_FULLNAME = "ADMIN";

    @NonFinal
    static final String SYSTEM_ADMIN_PHONE = "0365203656";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(
            UserRepository userRepository, ProfileReposity profileReposity, GlobalRoleRepository globalRoleRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (userRepository.findByEmail(SYSTEM_ADMIN_EMAIL).isEmpty()) {
                //                roleRepository.save(Role.builder()
                //                        .name(PredefinedRole.USER_ROLE)
                //                        .description("User role")
                //                        .build());
                //
                //                Role adminRole = roleRepository.save(Role.builder()
                //                        .name(PredefinedRole.ADMIN_ROLE)
                //                        .description("Admin role")
                //                        .build());

                //                var roles = new HashSet<Role>();
                //                roles.add(adminRole);

                //                GlobalRole globalRole =
                // globalRoleRepository.findByName(SYSTEM_ADMIN_GLOBAL_ROLE).orElse(null);

                GlobalRole globalRole = globalRoleRepository
                        .findByName(SYSTEM_ADMIN_GLOBAL_ROLE)
                        .orElseGet(() -> globalRoleRepository.save(GlobalRole.builder()
                                .name("SYSTEM_ADMIN")
                                .description("Quản lý hệ thống")
                                .build()));

                User user = User.builder()
                        .email(SYSTEM_ADMIN_EMAIL)
                        .password(passwordEncoder.encode(SYSTEM_ADMIN_PASSWORD))
                        .global_role(globalRole)
                        .build();

                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
    }
}
