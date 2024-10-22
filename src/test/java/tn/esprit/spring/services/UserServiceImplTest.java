package tn.esprit.spring.services;


import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService; // Directly use the real service
    @Autowired
    private UserRepository userRepository;

    @Test
    void testAddUser() {
        User user = new User(); // Create a new user
        user.setFirstName("walid");
        user.setLastName("hsn");
        user.setRole(Role.INGENIEUR);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date birthDate = dateFormat.parse("08/06/2000");
            user.setDateNaissance(birthDate);
        } catch (ParseException e) {
            fail("Date parsing failed: " + e.getMessage());
        }
        User createdUser = userService.addUser(user); // Call the real addUser method

        assertNotNull(createdUser); // Check that a user is returned
        assertEquals("walid", createdUser.getFirstName());
        assertEquals("hsn", createdUser.getLastName());
    }
    @Test
    void testAddUserWithInvalidData() {
        User user = new User(); // Create a user with missing fields
        user.setFirstName(""); // Invalid first name
        user.setLastName("hsn");
        user.setRole(Role.INGENIEUR);

        User createdUser = userService.addUser(user); // Call the real addUser method

        assertNull(createdUser); // Check that no user is returned
    }

    @Test
    void testRetrieveUserWithInvalidId() {
        User retrievedUser = userService.retrieveUser("invalid_id");

        assertNull(retrievedUser); // Check that null is returned
    }

}
