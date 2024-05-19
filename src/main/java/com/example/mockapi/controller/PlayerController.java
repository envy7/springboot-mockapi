package com.example.mockapi.controller;

import com.example.mockapi.model.player.PlayerData;
import com.example.mockapi.service.PlayerDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    private PlayerDataService playerDataService;

    @GetMapping("/players")
    public ResponseEntity<Page<PlayerData>> getAllData(Pageable pageable) {
        return ResponseEntity.ok(playerDataService.getAllData(pageable));
    }

    @GetMapping("/players/{playerId}")
    public ResponseEntity<PlayerData> getPlayerData(@PathVariable String playerId) {
        Optional<PlayerData> playerData = playerDataService.getPlayerData(playerId);
        return playerData.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
