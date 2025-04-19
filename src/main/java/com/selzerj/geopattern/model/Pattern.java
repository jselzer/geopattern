package com.selzerj.geopattern.model;

import lombok.Data;

import java.util.Base64;

@Data
public final class Pattern {

	private Svg background;
	private Svg structure;

	private int height;
	private int width;

	public String toSvg() {
		return new Svg(width, height)
				.addBody(background != null ? background.getBody() : "")
				.addBody(structure != null ? structure.getBody() : "")
				.getImageString();
	}

	public String toBase64() {
		return Base64.getEncoder().encodeToString(toSvg().getBytes());
	}
}
