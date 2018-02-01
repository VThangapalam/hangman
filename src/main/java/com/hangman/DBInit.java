/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman;

import com.hangman.dao.WordRepository;
import com.hangman.entity.Word;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * @author roger
 */
@Component
public class DBInit implements ApplicationListener<ContextRefreshedEvent> {

    public static int wordCount = 0;
    @Autowired
    WordRepository wordRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        try {
            ClassPathResource classPathResource = new ClassPathResource("dictionaryWords.txt");
            InputStream inputStream = classPathResource.getInputStream();
            int count = 1;
            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println("DbInit start.... inserting dictionary words...");
            String line = null;
            while ((line = br.readLine()) != null) {
                String word = line.trim();
                if (word.length() >= 4 && word.matches("[a-zA-Z]+")) {
                    Word newWord = new Word(count, word);
                    //System.out.println(line);
                    wordRepository.save(newWord);
                    count++;
                }
            }

            br.close();
            System.out.println("DbInit complete...");
            wordCount = count;
            System.out.println("DbInt complete.... ");
        } catch (IOException ex) {
            Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
