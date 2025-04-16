package com.selzerj.geopattern.pattern;

import com.selzerj.geopattern.svg.SvgImage;
import lombok.Data;

@Data
public class Pattern {

	private Background background;

	private Structure structure;

	private int height;

	private int width;

	public Pattern() {

	}

	public String toSvg() {
		return new SvgImage(width, height)
				// FIXME, null handling
				.addBody(background.getSvgImage().getBody())
				.addBody(structure.getSvgImage().getBody())
				.toString();
	}

	// TODO, add Base64 encoding
}
