package com.example.mockapi.repository;

import com.example.mockapi.model.PlayerData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<PlayerData, Long> {

    @Query("SELECT p FROM PlayerData p where p.playerId = :playerId")
    Optional<PlayerData> findPlayerByStringId(String playerId);
}
