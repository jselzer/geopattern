package com.selzerj.geopattern.colorgenerators;

import lombok.AllArgsConstructor;

import java.awt.Color;

@AllArgsConstructor
public class FixedColorGenerator implements ColorGenerator {

	private Color color;

	@Override
	public Color generate() {
		return color;
	}
}
