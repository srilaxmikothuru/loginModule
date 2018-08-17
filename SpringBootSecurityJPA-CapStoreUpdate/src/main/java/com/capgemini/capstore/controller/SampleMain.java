package com.capgemini.capstore.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class SampleMain {

	public static void main(String[] args) {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		System.out.println(enc.encode("newlook"));
		
		System.out.println(Base64Coder.decodeString("$2a$10$fYowAatlWMArekZQcyENdOYZ32AoaP2naDpK2MRxoyKVR/FTKe3JS"));

	}

}
