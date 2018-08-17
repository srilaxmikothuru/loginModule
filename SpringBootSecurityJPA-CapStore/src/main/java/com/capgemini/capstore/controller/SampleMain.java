package com.capgemini.capstore.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SampleMain {

	public static void main(String[] args) {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		System.out.println(enc.encode("admin"));

	}

}
