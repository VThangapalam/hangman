/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman.controller;

import com.hangman.entity.Word;
import com.hangman.service.WordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author roger
 */
@RestController
@RequestMapping("/word")
public class WordController {

    static int cnt = 0;

    @Autowired
    WordService wordService;

    public WordController(WordService wordServ) {
        this.wordService = wordServ;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public long create(@RequestBody Word word) {
        //needs better appproach as auto inc unavailable in mongo
        //long cnt = wordRepository.count();
        //Word newWord = new Word(cnt+1,word);
        wordService.create(word);
        return word.getWordId();
    }

    @RequestMapping("/{id}")
    public Word read(@PathVariable int id) {
        return wordService.read(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(Word word) {
        wordService.update(word);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        wordService.delete(id);
    }

}
