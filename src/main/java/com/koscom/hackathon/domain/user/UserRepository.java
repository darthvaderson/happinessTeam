package com.koscom.hackathon.domain.user;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

}
