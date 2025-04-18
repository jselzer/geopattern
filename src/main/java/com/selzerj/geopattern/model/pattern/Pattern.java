package com.selzerj.geopattern.model.pattern;

import com.selzerj.geopattern.model.svg.SvgImage;
import lombok.Data;

@Data
public final class Pattern {

	private SvgImage background;
	private SvgImage structure;

	private int height;

	private int width;

	public Pattern() {

	}

	public String toSvg() {
		return new SvgImage(width, height)
				// FIXME, null handling
				.addBody(background.getBody())
				.addBody(structure.getBody())
				.toString();
	}

	// TODO, add Base64 encoding
}
