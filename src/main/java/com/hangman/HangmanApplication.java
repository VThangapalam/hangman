package com.hangman;

import com.hangman.dao.WordRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
}
