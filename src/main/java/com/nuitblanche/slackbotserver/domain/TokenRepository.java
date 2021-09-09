package com.nuitblanche.slackbotserver.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token,Long> {

    @Query(value = "SELECT t FROM Token t WHERE t.token IS NOT NULL")
    List<Token> findAllTokens();

    Token findByBotUserId(String botUserId);
}
