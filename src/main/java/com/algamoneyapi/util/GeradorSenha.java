package com.algamoneyapi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenha {
	
	public static void main(String[] args) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

       System.err.println(encoder.encode("admin"));		
	}
	
	
	
}
