package com.example.mockapi.repository;

import com.example.mockapi.model.player.PlayerData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PlayerRepositoryTest {
    @Mock
    private PlayerRepository playerRepository;

    @Test
    public void testFindPlayerByStringId_ExistingPlayer() {
        // Sample player data
        String playerId = "ABC123";
        PlayerData expectedPlayer = new PlayerData();
        expectedPlayer.setPlayerId(playerId);
        expectedPlayer.setNameFirst("test");

        // Mock repository behavior
        Mockito.when(playerRepository.findPlayerByStringId(playerId)).thenReturn(Optional.of(expectedPlayer));

        // Call the repository method
        Optional<PlayerData> foundPlayer = playerRepository.findPlayerByStringId(playerId);

        // Assertions
        assertTrue(foundPlayer.isPresent());
        assertEquals(foundPlayer.get(), expectedPlayer);
    }

    @Test
    public void testFindPlayerByStringId_NonexistentPlayer() {
        // Sample player ID
        String playerId = "XYZ987";

        // Mock repository behavior (no player found)
        Mockito.when(playerRepository.findPlayerByStringId(playerId)).thenReturn(Optional.empty());

        // Call the repository method
        Optional<PlayerData> foundPlayer = playerRepository.findPlayerByStringId(playerId);

        // Assertions
        assertFalse(foundPlayer.isPresent());
    }
}
