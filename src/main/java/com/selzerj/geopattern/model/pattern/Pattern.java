package com.selzerj.geopattern.model.pattern;

import com.selzerj.geopattern.model.svg.Svg;
import lombok.Data;

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

	// TODO, add Base64 encoding
}
