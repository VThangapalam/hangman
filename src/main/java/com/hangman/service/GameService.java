/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman.service;

import com.hangman.entity.Game;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author roger
 */
@Service
public interface GameService {
 public void create(Game game );
 public Game read(String id);
  public void update(Game game);
  public void delete(String id);
    
}
