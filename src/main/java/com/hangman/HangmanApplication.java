package com.hangman;

import com.hangman.dao.WordRepository;
import com.hangman.entity.Word;
import com.hangman.utility.FileUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class HangmanApplication {
@Autowired 
        WordRepository wordRepository;
	public static void main(String[] args) {
            try {
                SpringApplication.run(HangmanApplication.class, args);
               
            } catch (Exception ex) {
                Logger.getLogger(HangmanApplication.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        
/*
 @PostConstruct
 void initDB() throws FileNotFoundException, IOException {
    File wordFile = new File("F:\\1_javascript\\engmix\\engmix.txt");
     FileInputStream fis = new FileInputStream(wordFile);
  int count = 1;
	//Construct BufferedReader from InputStreamReader
	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
 
	String line = null;
	while ((line = br.readLine()) != null) {
                Word newWord = new Word(count,line.trim());
                System.out.println(line +"word from file !!!!");
                wordRepository.save(newWord);
                count++;
		
	}
 
	br.close();
     }    
*/
}
