package com.example.mockapi.controller;

import com.example.mockapi.model.reddit.dto.RedditUserRequestDto;
import com.example.mockapi.model.reddit.dto.RedditUserResponseDto;
import com.example.mockapi.repository.RedditUserRepository;
import com.example.mockapi.service.RedditService;
import com.example.mockapi.service.mapper.RedditUserMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RedditControllerTest {

    @Autowired
    private RedditController redditController;
    @MockBean
    private RedditService redditService;

    @MockBean
    private RedditUserRepository redditUserRepository;

    @MockBean
    private RedditUserMapper redditUserMapper;

    @Test
    public void testCreateUserSuccess() {
        RedditUserRequestDto userDto = new RedditUserRequestDto("test_user");
        RedditUserResponseDto expectedResponse = new RedditUserResponseDto(1L, "test_user");

        Mockito.when(redditService.createUser(userDto)).thenReturn(Optional.of(expectedResponse));
        ResponseEntity<Object> response = redditController.createUser(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
        URI location = response.getHeaders().getLocation();
        assertNotNull(location);
        assertEquals("/users/1", location.getPath());
    }

    @Test
    public void testCreateUserFailure() {
        RedditUserRequestDto userDto = new RedditUserRequestDto("existing_user");

        Mockito.when(redditService.createUser(userDto)).thenReturn(Optional.empty());
        ResponseEntity<Object> response = redditController.createUser(userDto);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Username already exists", response.getBody());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        Pageable pageable = PageRequest.of(0, 10); // Simulate pagination request
        List<RedditUserResponseDto> redditUsers = new ArrayList<>();
        redditUsers.add(new RedditUserResponseDto(1L, "user1"));
        redditUsers.add(new RedditUserResponseDto(2L, "user2"));

        Mockito.when(redditService.getAllData(pageable)).thenReturn(new PageImpl<>(redditUsers, pageable, 10));
        ResponseEntity<Page<RedditUserResponseDto>> response = redditController.getAllData(pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        Page<RedditUserResponseDto> actualPage = response.getBody();
        assertEquals(redditUsers.size(), actualPage.getContent().size());
    }

}
