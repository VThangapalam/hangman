/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman.controller;

import com.hangman.DBInit;
import java.util.Date;
import com.hangman.entity.Game;
import com.hangman.entity.Word;
import com.hangman.service.GameService;
import com.hangman.service.WordService;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author roger
 */
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    WordService wordService;

    public GameController(GameService gameServ, WordService wrdServ) {
        this.gameService = gameServ;
        this.wordService = wrdServ;
    }

    @RequestMapping("/{id}")
    public Game read(@PathVariable String id) {
        return gameService.read(id);
    }

    //get a gameId
    @RequestMapping("/getGameId")
    public Game getGameId() {
        Random rand = new Random();
        int totalWordCnt = DBInit.wordCount;
        int randomNum = rand.nextInt((totalWordCnt - 1) + 1) + 1;
        String uniqueID = UUID.randomUUID().toString();
        Word gameWord = wordService.read(randomNum);
        int wordsize = gameWord.getWord().length();
        Game newGame = new Game(uniqueID, randomNum, wordsize);
        gameService.create(newGame);
        return newGame;
    }

    @RequestMapping("/{id}/guess/{guessChar}")
    public Game guess(@PathVariable String id, @PathVariable char guessChar) {

        Game currGame = gameService.read(id);
        currGame.setLastUpdatedTime(new Date());
        String word = wordService.read(currGame.getWordId()).getWord().trim();
        System.out.println("game : " + id + " word: " + word);
        String wordGuessed = currGame.getWordGuessed().trim();
        StringBuilder wordSB = new StringBuilder(wordGuessed);
        if (word.indexOf(guessChar) >= 0) {

            char[] wordCharArray = word.trim().toCharArray();
            int len = wordCharArray.length;
            for (int i = 0; i < len; i++) {

                if (wordCharArray[i] == guessChar) {
                    wordSB.setCharAt(i, guessChar);
                }
            }
            ArrayList<Character> charGuessed = currGame.getLettersGuessedCorrectly();
            charGuessed.add(guessChar);
            currGame.setLettersGuessedCorrectly(charGuessed);
            currGame.setWordGuessed(wordSB.toString());
            //System.out.println("guessed word ! "+ wordSB.toString());
            wordGuessed = wordSB.toString();
            if (wordGuessed.indexOf('_') == -1) {
                //all characters guessed correctly
                //System.out.println("won game!!!! ");
                int won = currGame.getNumberOfGamesWon();
                won = won + 1;
                currGame.setNumberOfGamesWon(won);
            }

        } else {

            ArrayList<Character> charGuessedIncorrect = currGame.getLettersGuessedIncorrectly();
            charGuessedIncorrect.add(guessChar);
            currGame.setLettersGuessedIncorrectly(charGuessedIncorrect);
            int wrongGuesses = currGame.getNumberOfWrongGuess();
            wrongGuesses = wrongGuesses + 1;
            currGame.setNumberOfWrongGuess(wrongGuesses);
            if (wrongGuesses == 10) {
                int lost = currGame.getNumberOfGamesLost();
                lost = lost + 1;
                currGame.setNumberOfGamesLost(lost);
            }

        }
        gameService.update(currGame);
        return currGame;

    }

    @RequestMapping("/{id}/newWord")
    public Game getNewWordForGame(@PathVariable String id) {
        Random rand = new Random();
        int totalWordCnt = DBInit.wordCount;
        int randomNum = rand.nextInt((totalWordCnt - 1) + 1) + 1;
        Word gameWord = wordService.read(randomNum);
        int wordsize = gameWord.getWord().length();
        Game curr = gameService.read(id);
        int won = curr.getNumberOfGamesWon();
        int lost = curr.getNumberOfGamesLost();
        Game newGame = new Game(id, randomNum, wordsize, won, lost, curr.getStartTime(), curr.getLastUpdatedTime());
        gameService.create(newGame);
        return newGame;
    }

}
