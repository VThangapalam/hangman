/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman.service;

import com.hangman.dao.WordRepository;
import com.hangman.entity.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author roger
 */
@Service
public class WordServiceImpl implements WordService {

    @Autowired
    WordRepository wordRepository;

    public WordServiceImpl(WordRepository wordRepo) {
        this.wordRepository = wordRepo;
    }

    @Override
    public void create(Word word) {
        wordRepository.save(word);
    }

    @Override
    public Word read(int id) {
        return wordRepository.findOne(id);
    }

    @Override
    public void update(Word word) {
        wordRepository.save(word);
    }

    @Override
    public void delete(int id) {
        wordRepository.delete(id);
    }

}
