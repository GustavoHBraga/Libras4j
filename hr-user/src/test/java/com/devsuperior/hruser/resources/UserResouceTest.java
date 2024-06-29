package com.devsuperior.hruser.resources;

import com.devsuperior.hruser.entities.User;
import com.devsuperior.hruser.repositories.UserRepository;
import com.devsuperior.hruser.resources.UserResouce;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserResourceTest {
    
    @InjectMocks
    private UserResouce userResource;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listAll_shouldReturnListOfUsers() {
        List<User> users = Arrays.asList(new User(1L, "User1", "user1@example.com", "password"),
                new User(2L, "User2", "user2@example.com", "password"));
        when(userRepository.findAll()).thenReturn(users);

        ResponseEntity<List<User>> response = userResource.listAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users.size(), response.getBody().size());
    }

    @Test
    void findById_shouldReturnUserWhenFound() {
        User user = new User(1L, "User1", "user1@example.com", "password");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userResource.findById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void findById_shouldReturnNotFoundWhenUserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userResource.findById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void createUser_shouldCreateAndReturnUser() {
        User user = new User(null, "User1", "user1@example.com", "password");
        User savedUser = new User(1L, "User1", "user1@example.com", "encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        ResponseEntity<User> response = userResource.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
    }

    @Test
    void updateUser_shouldUpdateAndReturnUpdatedUser() {
        User existingUser = new User(1L, "User1", "user1@example.com", "password");
        User userUpdate = new User(null, "UpdatedUser", "updated@example.com", "newpassword");
        User updatedUser = new User(1L, "UpdatedUser", "updated@example.com", "encoded_newpassword");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        ResponseEntity<User> response = userResource.updateUser(1L, userUpdate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    void updateUser_shouldReturnNotFoundWhenUserDoesNotExist() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        User userUpdate = new User(null, "UpdatedUser", "updated@example.com", "newpassword");
        ResponseEntity<User> response = userResource.updateUser(1L, userUpdate);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void findByEmail_shouldReturnUserWhenFound() {
        User user = new User(1L, "User1", "user1@example.com", "password");
        when(userRepository.findByEmail("user1@example.com")).thenReturn(user);

        ResponseEntity<User> response = userResource.findByEmail("user1@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void findByEmail_shouldReturnNotFoundWhenUserDoesNotExist() {
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        ResponseEntity<User> response = userResource.findByEmail("nonexistent@example.com");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
