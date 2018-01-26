/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman.service;

import com.hangman.entity.Word;
import org.springframework.stereotype.Service;

/**
 *
 * @author roger
 */
@Service
public interface WordService {
 public void create(Word game );
 public Word read(int id);
  public void update(Word game);
  public void delete(int id);
    
}
