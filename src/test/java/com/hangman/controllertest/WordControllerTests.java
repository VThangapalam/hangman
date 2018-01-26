/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman.controllertest;

import com.hangman.controller.GameController;
import com.hangman.controller.WordController;
import com.hangman.entity.Word;
import com.hangman.service.GameService;
import com.hangman.service.GameServiceImpl;
import com.hangman.service.WordService;
import com.hangman.service.WordServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 *
 * @author roger
 */
@ActiveProfiles("test")
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@TestPropertySource("classpath:application-test.properties")
public class WordControllerTests {
    
    MockMvc mockMvc;
    
  
    
    WordService wordServiceMock;
    
     @Before
    public void setUp() {
       wordServiceMock = Mockito.mock(WordServiceImpl.class);
       WordController  wordController = new WordController(wordServiceMock);
       mockMvc = MockMvcBuilders.standaloneSetup(wordController).build();
    }
    

    @Test 
    public void createWordTest()  {
        Word testword = new Word(1,"apple");
         try {
            
            wordServiceMock.create(testword);
            
            RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/word")
                     .param("wordId", "1")
            .param("word", "apple"); 
            
            MvcResult result = mockMvc.perform(requestBuilder)
                    .andReturn();
                      
           
           
            String content = result.getResponse().getContentAsString();
             System.out.println(content + " create res!!!!");
             int expVal = 1;
              Assert.assertEquals("create word api failed :",expVal,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Test 
    public void readWordTest()  {
        Word testword = new Word(1,"apple");
         try {
            
            
            Mockito.when(wordServiceMock.read(1)).thenReturn(testword);

            RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                    "/word/1").accept(
                    MediaType.APPLICATION_JSON);

            MvcResult result = mockMvc.perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
           
            String content = result.getResponse().getContentAsString();
             System.out.println(content + "res!!!!");
             String expectedString = "{\"wordId\":1,\"word\":\"apple\"}";
              Assert.assertEquals("read word api failed :",expectedString,content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
}