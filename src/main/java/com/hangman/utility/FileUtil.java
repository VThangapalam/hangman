/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hangman.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author roger
 */
public class FileUtil {
    public static void readFile1(File fin) throws IOException {
        
        
	FileInputStream fis = new FileInputStream(fin);
 
	//Construct BufferedReader from InputStreamReader
	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
 
	String line = null;
	while ((line = br.readLine()) != null) {
		System.out.println(line);
	}
 
	br.close();
}
}
