package com.myproject.quiz.database.entities;

import com.myproject.quiz.database.repositories.PlayerRepository;
import com.myproject.quiz.services.QuizDataService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Log
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private QuizDataService quizDataService;

    @Override
    @Transactional
    public void run(String...args) throws Exception {
        log.info("Executing startup actions...");

        playerRepository.save(new PlayerEntity("John"));
        playerRepository.save(new PlayerEntity("Harry"));
        playerRepository.save(new PlayerEntity("George"));

        log.info("List of players from database:");
        List<PlayerEntity> playersFromDatabase = playerRepository.findAll();
        for (PlayerEntity player : playersFromDatabase) {
            log.info("Retrieved player: " + player);
        }

        quizDataService.getQuizCategories();
    }
}