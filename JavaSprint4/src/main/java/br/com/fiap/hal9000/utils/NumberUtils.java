package br.com.fiap.hal9000.utils;

import java.util.Random;

public class NumberUtils {

	public static boolean isNullOrLessThan0(Number valor) {
	    if (valor == null) 
	        return true; 
	    
	    if (valor instanceof Integer && ((Integer) valor) < 0) 
	        return true; 
	    
	    if (valor instanceof Double && ((Double) valor) < 0.0) 
	        return true; 
	    
	    return false;
	}
	
	public static int gerarId(int min, int max) {
	    Random random = new Random();
	    return random.nextInt((max - min) + 1) + min;
	}
	
}
