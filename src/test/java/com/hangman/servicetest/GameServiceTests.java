/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman.servicetest;

import com.hangman.dao.GameRepository;
import com.hangman.entity.Game;
import com.hangman.service.GameService;
import com.hangman.service.GameServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
public class GameServiceTests {
    
    GameService gameService;

    private GameRepository gameRepositoryMock;
    
     @Before
    public void setUp() {
      gameRepositoryMock = Mockito.mock(GameRepository.class);
      gameService = new GameServiceImpl(gameRepositoryMock);
    }
    
    
	@Test
	public void insertGameTest() {       
            Game testgame = new Game("game1", 1, 3);
            Mockito.when(gameRepositoryMock.save(testgame)).thenReturn(testgame);
            gameService.create(testgame);
            Mockito.when(gameRepositoryMock.findOne("game1")).thenReturn(testgame);
            Game game = gameService.read("game1");
            System.out.println("game after insert "+game.getGameId());
            Assert.assertEquals("insert failed",game.getWordId(),testgame.getWordId());
	}
        
        
        	@Test
	public void updateGameTest() {
             Game testgame = new Game("game1", 1, 3);
             
            Mockito.when(gameRepositoryMock.save(testgame)).thenReturn(testgame);
            gameService.create(testgame);
            testgame.setNumberOfWrongGuess(5);
            Mockito.when(gameRepositoryMock.save(testgame)).thenReturn(testgame);
            gameService.update(testgame);
            Mockito.when(gameRepositoryMock.findOne("game1")).thenReturn(testgame);
            Game game = gameService.read("game1");
            System.out.println("game after insert "+game.getGameId());
            Assert.assertEquals("insert failed",5,game.getNumberOfWrongGuess());

        
	}
        
              	@Test
	public void readGameTest() {
             Game testgame = new Game("game1", 1, 3);
             
            Mockito.when(gameRepositoryMock.save(testgame)).thenReturn(testgame);
            gameService.create(testgame);
            Mockito.when(gameRepositoryMock.findOne("game1")).thenReturn(testgame);
            Game game = gameService.read("game1");

            Assert.assertEquals("read failed",game.getWordId(),testgame.getWordId());

        
	}
        
        @Test
      public void deleteGameTest() {
            Game testgame = new Game("game1", 1, 3);          
            Mockito.when(gameRepositoryMock.save(testgame)).thenReturn(testgame);
            gameService.create(testgame);
            gameRepositoryMock.delete("game1");
            gameService.delete("game1");
            Mockito.when(gameRepositoryMock.findOne("game1")).thenThrow(NullPointerException.class);
            try {
            Game game = gameService.read("game1");
            Assert.fail("Delete failed");
            }catch(Exception e) {
                
            }

	}
    
    
}
