package com.example.flashcash.repository;

import com.example.flashcash.model.Transfer;
import com.example.flashcash.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    Transfer findByFromOrToOrderByDateDesc(User from, User to);
}