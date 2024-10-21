package tn.esprit.spring.services;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class UserServiceImplMockTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "John", "Doe", new Date(), Role.INGENIEUR); // Set role to INGENIEUR
    }

    @Test
    void testAddUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.addUser(user);

        assertNotNull(createdUser);
        assertEquals("John", createdUser.getFirstName());
        assertEquals(Role.INGENIEUR, createdUser.getRole()); // Verify role
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testUpdateUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(user);

        assertNotNull(updatedUser);
        assertEquals("John", updatedUser.getFirstName());
        assertEquals(Role.INGENIEUR, updatedUser.getRole()); // Verify role
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(anyLong());

        userService.deleteUser("1");

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void testRetrieveUser() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        User retrievedUser = userService.retrieveUser("1");

        assertNotNull(retrievedUser);
        assertEquals("John", retrievedUser.getFirstName());
        assertEquals(Role.INGENIEUR, retrievedUser.getRole()); // Verify role
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testRetrieveAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<User> users = userService.retrieveAllUsers();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals("John", users.get(0).getFirstName());
        assertEquals(Role.INGENIEUR, users.get(0).getRole()); // Verify role
        verify(userRepository, times(1)).findAll();
    }
}


