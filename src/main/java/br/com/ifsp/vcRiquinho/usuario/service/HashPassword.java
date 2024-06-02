package br.com.ifsp.vcRiquinho.usuario.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HashPassword {
	public static String generate(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest(password.getBytes());
		return Base64.getEncoder().encodeToString(hash);
	}
	
	 public static void main(String[] args) {
	        String[] passwords = {
	            "password1",
	            "password2",
	            "password3",
	            "password4",
	            "password5"
	        };

	        for (String password : passwords) {
	            try {
	                String hashedPassword = generate(password);
	                System.out.println("-- " + password + " -> " + hashedPassword);
	            } catch (NoSuchAlgorithmException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
