package com.example.PrestaBanco_Backend.services;
import com.example.PrestaBanco_Backend.entities.UserEntity;
import com.example.PrestaBanco_Backend.repositories.UserRepository;
import com.example.PrestaBanco_Backend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUsers() {
        ArrayList<UserEntity> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        ArrayList<UserEntity> result = userService.getUsers();

        assertEquals(users, result);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testGetUserById() {
        Long userId = 1L;
        UserEntity user = new UserEntity();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserEntity result = userService.getUSerById(userId);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    public void testSaveUser() {
        UserEntity user = new UserEntity();

        when(userRepository.save(user)).thenReturn(user);

        UserEntity result = userService.saveUser(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser() {
        UserEntity user = new UserEntity();

        when(userRepository.save(user)).thenReturn(user);

        UserEntity result = userService.updateUser(user);

        assertNotNull(result);
        assertEquals(user, result);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() throws Exception {
        Long id = 1L;

        doNothing().when(userRepository).deleteById(id);

        boolean result = userService.deleteUser(id);

        assertTrue(result);
        verify(userRepository, times(1)).deleteById(id);
    }
}
