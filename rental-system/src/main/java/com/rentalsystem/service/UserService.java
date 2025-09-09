package com.rentalsystem.service;

import com.rentalsystem.model.Role;
import com.rentalsystem.model.User;
import com.rentalsystem.repository.RoleRepository;
import com.rentalsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // GET όλα τα users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET user με id
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Δημιουργία νέου user
    public User createUser(User user) {
        Set<Role> roles = new HashSet<>();
        if (user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                Optional<Role> existingRole = roleRepository.findByName(role.getName());
                existingRole.ifPresent(roles::add);
            }
        }
        user.setRoles(roles);
        return userRepository.save(user);
    }

    // Ενημέρωση user
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setUsername(userDetails.getUsername());
            user.setPassword(userDetails.getPassword());

            Set<Role> roles = new HashSet<>();
            if (userDetails.getRoles() != null) {
                for (Role role : userDetails.getRoles()) {
                    Optional<Role> existingRole = roleRepository.findByName(role.getName());
                    existingRole.ifPresent(roles::add);
                }
            }
            user.setRoles(roles);

            return userRepository.save(user);
        }
        return null;
    }

    // Διαγραφή user
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
