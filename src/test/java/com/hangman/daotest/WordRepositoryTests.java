/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman.daotest;

import com.hangman.DBInit;
import com.hangman.dao.WordRepository;
import com.hangman.entity.Word;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author roger
 */
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class WordRepositoryTests {
    @Autowired
    WordRepository wordRepository;

    
	@Test
	public void insertWordTest() {
          
            Word testword = new Word(1, "apple");
            wordRepository.save(testword);
            String word = wordRepository.findOne(1).getWord();
            System.out.println("word after insert "+word);
            Assert.assertEquals("insert failed",word,testword.getWord());
	}
        
        
        	@Test
	public void updateWordTest() {
     
            Word testword = new Word(1, "apple");
            wordRepository.save(testword);
            String word = wordRepository.findOne(1).getWord();
            System.out.println("word after insert "+word);
            Assert.assertEquals("insert failed",testword.getWord(),word);
            testword.setWord("bat");
            wordRepository.save(testword);
            word = wordRepository.findOne(1).getWord();
            Assert.assertEquals("update failed",word,testword.getWord());

	}
        
        @Test
        	public void deleteWordTest() {
       
            Word testword = new Word(1, "apple");
            wordRepository.save(testword);
            String word = wordRepository.findOne(1).getWord();
           
            Assert.assertEquals("insert failed",word,testword.getWord());
           
            wordRepository.delete(1);
            try {
                wordRepository.findOne(1).getWord();
                Assert.fail("Deletion of word failed!!");
            }catch(Exception e) {
                
            }
            

	}
}
