package ru.melnikov.oauthserver.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.melnikov.oauthserver.model.UserEntity;

public interface UserRepo extends MongoRepository<UserEntity, String> {

    UserEntity findByUsername(String username);
}
