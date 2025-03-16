package com.selzerj.geopattern.pattern;

import lombok.Value;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// FIXME, package
@Value
public class Seed {
	String value;

	public Seed(String inputString) {
		this.value = sha1Hex(inputString);
	}

	public int getInteger(int offset, int length) {
		return Integer.parseInt(value.substring(offset, offset + length), 16);
	}

	private String sha1Hex(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			byte[] hash = digest.digest(input.getBytes());
			return String.format("%0" + (hash.length << 1) + "X", new BigInteger(1, hash));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-1 algorithm not available", e);
		}
	}
}
