/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman.servicetest;

import com.hangman.dao.WordRepository;
import com.hangman.entity.Word;
import com.hangman.service.WordService;
import com.hangman.service.WordServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
public class WordServiceTests {

    WordService wordService;

    private WordRepository wordRepositoryMock;

    @Before
    public void setUp() {
        wordRepositoryMock = Mockito.mock(WordRepository.class);
        wordService = new WordServiceImpl(wordRepositoryMock);
    }

    @Test
    public void insertWordTest() {
        Word testword = new Word(1, "apple");
        Mockito.when(wordRepositoryMock.save(testword)).thenReturn(testword);
        wordService.create(testword);
        Mockito.when(wordRepositoryMock.findOne(1)).thenReturn(testword);
        Word word = wordService.read(1);
        Assert.assertEquals("WordServiceTest: insert failed", word.getWordId(), testword.getWordId());
    }

    @Test
    public void updateWordTest() {
        Word testword = new Word(1, "apple");
        Mockito.when(wordRepositoryMock.save(testword)).thenReturn(testword);
        wordService.create(testword);
        testword.setWord("air");
        Mockito.when(wordRepositoryMock.save(testword)).thenReturn(testword);
        wordService.update(testword);
        Mockito.when(wordRepositoryMock.findOne(1)).thenReturn(testword);
        Word word = wordService.read(1);
        Assert.assertEquals("WordServiceTest : update failes", "air", word.getWord());

    }

    @Test
    public void readWordTest() {
        Word testword = new Word(1, "apple");
        Mockito.when(wordRepositoryMock.save(testword)).thenReturn(testword);
        wordService.create(testword);
        Mockito.when(wordRepositoryMock.findOne(1)).thenReturn(testword);
        Word word = wordService.read(1);
        Assert.assertEquals("WordServiceTest: read failed", word.getWordId(), testword.getWordId());

    }

    @Test
    public void deleteGameTest() {
        Word testword = new Word(1, "apple");
        Mockito.when(wordRepositoryMock.save(testword)).thenReturn(testword);
        wordService.create(testword);
        wordRepositoryMock.delete(1);
        wordService.delete(1);
        Mockito.when(wordRepositoryMock.findOne(1)).thenThrow(NullPointerException.class);
        try {
            Word word = wordService.read(1);
            Assert.fail("Word Service: Delete failed");
        } catch (Exception e) {
        }

    }

}
