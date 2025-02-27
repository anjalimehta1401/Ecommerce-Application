package com.youtube.demo.ecoomerce.model.dao;

import com.youtube.demo.ecoomerce.api.model.VerificationToken;
import com.youtube.demo.ecoomerce.model.LocalUser;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface VerificationTokenDAO extends ListCrudRepository<VerificationToken, Long> {

    Optional<VerificationToken> findByToken(String token);

    void deleteByUser(LocalUser user);

}