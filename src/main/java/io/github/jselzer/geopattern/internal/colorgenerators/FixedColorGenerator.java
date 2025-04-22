package io.github.jselzer.geopattern.internal.colorgenerators;

import lombok.AllArgsConstructor;

import java.awt.Color;

@AllArgsConstructor
public final class FixedColorGenerator implements ColorGenerator {

	private Color color;

	@Override
	public Color generate() {
		return color;
	}
}
