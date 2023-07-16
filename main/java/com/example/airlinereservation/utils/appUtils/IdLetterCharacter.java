package com.example.airlinereservation.utils.appUtils;

import java.util.Random;

public enum IdLetterCharacter {
	A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z;
	
	private static final Random randomLetter = new Random();
	
	public static String getCharacter(){
		String letters = "";
		IdLetterCharacter letterCharacter1 = IdLetterCharacter.values()[randomLetter.nextInt(IdLetterCharacter.values().length)];
		IdLetterCharacter letterCharacter2 = IdLetterCharacter.values()[randomLetter.nextInt(IdLetterCharacter.values().length)];
		letters+=letterCharacter1;
		letters+=letterCharacter2;
		return letters;
	}
}
