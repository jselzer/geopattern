package com.selzerj.geopattern.model;

import lombok.Data;

import java.util.Base64;

/**
 * The Pattern class represents a geopattern composed of a background color and structure.
 */
@Data
public final class Pattern {

	private Svg background;
	private Svg structure;

	private int height;
	private int width;

	/**
	 * Generates an SVG representation of the pattern.
	 * @return SVG representation of the pattern
	 */
	public String toSvg() {
		return new Svg(width, height)
				.addBody(background != null ? background.getBody() : "")
				.addBody(structure != null ? structure.getBody() : "")
				.getImageString();
	}

	/**
	 * Generates a base64 representation of the SVG String.
	 * @return base64 representation of the SVG String
	 */
	public String toBase64() {
		return Base64.getEncoder().encodeToString(toSvg().getBytes());
	}
}
