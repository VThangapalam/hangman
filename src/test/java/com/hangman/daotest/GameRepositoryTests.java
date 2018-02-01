/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman.daotest;

import com.hangman.dao.GameRepository;
import com.hangman.entity.Game;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author roger
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class GameRepositoryTests {

    @Autowired
    GameRepository gameRepository;

    @Test
    public void insertGameTest() {

        Game testgame = new Game("game1", 1, 3);
        gameRepository.save(testgame);
        int wordId = gameRepository.findOne("game1").getWordId();
        System.out.println("game after insert " + wordId);
        Assert.assertEquals("insert failed", 1, testgame.getWordId());
    }

    @Test
    public void updateGameTest() {

        Game testgame = new Game("game1", 1, 3);
        gameRepository.save(testgame);
        int wordId = gameRepository.findOne("game1").getWordId();
        System.out.println("game after insert " + wordId);
        Assert.assertEquals("insert failed", testgame.getWordId(), 1);
        testgame.setWordId(5);
        gameRepository.save(testgame);
        wordId = gameRepository.findOne("game1").getWordId();
        Assert.assertEquals("update failed", 5, testgame.getWordId());

    }

    @Test
    public void deleteGameTest() {
        Game testgame = new Game("game1", 1, 3);
        gameRepository.save(testgame);
        int wordId = gameRepository.findOne("game1").getWordId();
        System.out.println("game after insert " + wordId);
        Assert.assertEquals("insert failed", 1, testgame.getWordId());

        gameRepository.delete("game1");
        try {
            gameRepository.findOne("game1").getWordId();
            org.junit.Assert.fail("Deletion of game failed");
        } catch (Exception e) {

        }

    }
}
