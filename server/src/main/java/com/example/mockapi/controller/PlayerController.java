package com.example.mockapi.controller;

import com.example.mockapi.model.PlayerData;
import com.example.mockapi.service.PlayerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    private PlayerDataService playerDataService;

    @GetMapping("/players")
    public ResponseEntity<List<PlayerData>> getAllData() {
        return ResponseEntity.ok(playerDataService.getAllData());
    }

    @GetMapping("/players/{playerId}")
    public ResponseEntity<PlayerData> getPlayerData(@PathVariable String playerId) {
        Optional<PlayerData> playerData = playerDataService.getPlayerData(playerId);
        return playerData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
