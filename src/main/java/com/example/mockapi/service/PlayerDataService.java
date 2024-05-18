package com.example.mockapi.service;

import com.example.mockapi.model.PlayerData;
import com.example.mockapi.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PlayerDataService {

    @Autowired
    private PlayerRepository playerRepository;

    public Page<PlayerData> getAllData(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    public Optional<PlayerData> getPlayerData(String playerId) {
        return playerRepository.findPlayerByStringId(playerId);
    }
}