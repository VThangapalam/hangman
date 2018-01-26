/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman.service;

import com.hangman.dao.GameRepository;
import com.hangman.dao.WordRepository;
import com.hangman.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author roger
 */
@Service
public class GameServiceImpl implements GameService{
@Autowired
GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepo) {
        this.gameRepository = gameRepo;
                }

 

                public void create(Game game ) {
                   gameRepository.save(game);      
                }

                public Game read(String id) {
                    return gameRepository.findOne(id);
                }

                    public void update(Game game) {
                    gameRepository.save(game); 
                }

                        public void delete(String id) {
                     gameRepository.delete(id);
                }
    
}
