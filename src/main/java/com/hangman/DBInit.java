/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman;

import com.hangman.dao.WordRepository;
import com.hangman.entity.Word;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * @author roger
 */
@Component
public class DBInit implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    WordRepository wordRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        try {
            ClassPathResource classPathResource = new ClassPathResource("dictionaryWords.txt");

            InputStream inputStream = classPathResource.getInputStream();
            File wordFile = File.createTempFile("test", ".txt");
            try {
                FileUtils.copyInputStreamToFile(inputStream, wordFile);
            } finally {
                IOUtils.closeQuietly(inputStream);
            }
            FileInputStream fis = new FileInputStream(wordFile);
            int count = 1;
            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
           System.out.println("DbInt start.... inserting dictionary words...");
            String line = null;
            while ((line = br.readLine()) != null) {
                Word newWord = new Word(count, line.trim());
                //System.out.println(line);
                wordRepository.save(newWord);
                count++;

            }

            br.close();
              System.out.println("DbInt complete.... ");
        } catch (IOException ex) {
            Logger.getLogger(DBInit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
