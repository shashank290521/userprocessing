package com.example.userprocessing.service;

import com.example.userprocessing.entity.User;
import com.example.userprocessing.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserProcessor {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Scheduled(fixedRate = 5000) // Poll every 5 seconds
    public void processUsers() {
        List<User> usersToProcess = userRepository.findUnprocessedUsersWithLock();

        for (User user : usersToProcess) {
            System.out.println("Processing user: " + user.getName() + ", Email: " + user.getEmail());
            // Mark the user as processed
            user.setProcessed(true);
            userRepository.save(user);
        }
    }
}
