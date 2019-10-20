package ru.melnikov.oauthserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.melnikov.oauthserver.model.Role;
import ru.melnikov.oauthserver.model.UserEntity;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class OauthServerApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx = SpringApplication.run(OauthServerApplication.class, args);
//        createUsers(ctx.getBean(MongoTemplate.class),ctx.getBean(PasswordEncoder.class));
    }

    private static void createUsers(MongoTemplate template, PasswordEncoder encoder) {
        UserEntity admin = new UserEntity();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("password"));
        admin.setRoles(Set.of(Role.ROLE_ADMIN));

        template.save(admin);

        UserEntity user = new UserEntity();
        user.setUsername("user");
        user.setPassword(encoder.encode("password"));
        user.setRoles(Set.of(Role.ROLE_USER));

        template.save(user);

    }

}
