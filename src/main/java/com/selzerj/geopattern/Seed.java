package com.selzerj.geopattern;

import lombok.Value;

// FIXME, package
@Value
public class Seed {
	String value;

	public int getInteger(int offset, int length) {
		return Integer.parseInt(value.substring(offset, offset + length), 16);
	}
}
