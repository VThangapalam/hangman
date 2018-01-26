/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author roger
 */
@Document
public class Game {
    @Id
    private String gameId;

    private int wordId;

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
    private int wordSize;
    private Date startTime;
    private Date lastUpdatedTime;
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public int getWordSize() {
        return wordSize;
    }

    public void setWordSize(int wordSize) {
        this.wordSize = wordSize;
    }
    private int numberOfGamesWon;
    private int numberOfGamesLost;
    private int numberOfWrongGuess;
    private ArrayList<Character> lettersGuessedCorrectly;
    private ArrayList<Character> lettersGuessedIncorrectly;

    public ArrayList<Character> getLettersGuessedIncorrectly() {
        return lettersGuessedIncorrectly;
    }

    public void setLettersGuessedIncorrectly(ArrayList<Character> lettersGuessedIncorrectly) {
        this.lettersGuessedIncorrectly = lettersGuessedIncorrectly;
    }
    private String wordGuessed;
    
    public String getGameId() {
        return gameId;
    }

    public Game(String gameId, int wordId,int wordSize) {
        this.gameId = gameId;
        this.wordId = wordId;
        this.wordSize=wordSize;
        this.numberOfGamesLost=0;
        this.numberOfGamesWon=0;
        this.wordGuessed="";
        this.lettersGuessedCorrectly = new ArrayList<>();
        this.lettersGuessedIncorrectly = new ArrayList<>();
        char[] chars = new char[wordSize];
        Arrays.fill(chars, '_');
        this.wordGuessed = new String(chars);
        this.numberOfWrongGuess = 0;
        this.startTime = new Date();
        
       
    }
    
    public Game() {
        
    }
    
    public Game(String gameId, int wordId,int wordSize,int won, int lost,Date start,Date updated) {
        this.gameId = gameId;
        this.wordId = wordId;
        this.wordSize=wordSize;
        this.numberOfGamesLost=lost;
        this.numberOfGamesWon=won;
        this.wordGuessed="";
        this.lettersGuessedCorrectly = new ArrayList<>();
        this.lettersGuessedIncorrectly = new ArrayList<>();
        char[] chars = new char[wordSize];
        Arrays.fill(chars, '_');
        this.wordGuessed = new String(chars);
        this.numberOfWrongGuess = 0;
        this.startTime = start;
        this.lastUpdatedTime = updated;
    }
    

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getNumberOfGamesWon() {
        return numberOfGamesWon;
    }

    public void setNumberOfGamesWon(int numberOfGamesWon) {
        this.numberOfGamesWon = numberOfGamesWon;
    }

    public int getNumberOfGamesLost() {
        return numberOfGamesLost;
    }

    public void setNumberOfGamesLost(int numberOfGamesLost) {
        this.numberOfGamesLost = numberOfGamesLost;
    }

    public int getNumberOfWrongGuess() {
        return numberOfWrongGuess;
    }

    public void setNumberOfWrongGuess(int numberOfWrongGuess) {
        this.numberOfWrongGuess = numberOfWrongGuess;
    }

    public ArrayList<Character> getLettersGuessedCorrectly() {
        return lettersGuessedCorrectly;
    }

    public void setLettersGuessedCorrectly(ArrayList<Character> lettersGuessedCorrectly) {
        this.lettersGuessedCorrectly = lettersGuessedCorrectly;
    }

    public String getWordGuessed() {
        return wordGuessed;
    }

    public void setWordGuessed(String wordGuessed) {
        this.wordGuessed = wordGuessed;
    }

       
}
