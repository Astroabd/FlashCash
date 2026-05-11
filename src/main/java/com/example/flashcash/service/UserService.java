package com.example.flashcash.service;


import com.example.flashcash.model.User;
import com.example.flashcash.model.UserAccount;
import com.example.flashcash.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(User user) {
        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Create a new empty UserAccount and attach it
        UserAccount account = new UserAccount();
        account.setAmount(0.0);
        account.setIban(""); // optional, can be set later
        user.setAccount(account);
        // Save user (cascade saves account)
        userRepository.save(user);
    }
}
