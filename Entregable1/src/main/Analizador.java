package main;

import java.util.Scanner;

import lexico.Lexico;

public class Analizador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Lexico lex = new Lexico();
		System.out.println("Introduce texto: ");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();	
		System.out.println(lex.getTokentypes(lex.getTokens(input)));
		
		
		
	}

}
