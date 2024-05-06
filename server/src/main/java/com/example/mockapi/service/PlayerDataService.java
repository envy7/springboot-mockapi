package com.example.mockapi.service;

import com.example.mockapi.model.PlayerData;
import com.example.mockapi.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlayerDataService {

    @Autowired
    private PlayerRepository playerRepository;

    public List<PlayerData> getAllData() {
        return playerRepository.findAll();
    }

    public Optional<PlayerData> getPlayerData(String playerId) {
        return playerRepository.findPlayerByStringId(playerId);
    }
}