package com.example.mockapi.repository;

import com.example.mockapi.model.player.PlayerData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends PagingAndSortingRepository<PlayerData, Long> {

    @Query("SELECT p FROM PlayerData p where p.playerId = :playerId")
    Optional<PlayerData> findPlayerByStringId(String playerId);
}
