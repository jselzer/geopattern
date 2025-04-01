package com.selzerj.geopattern.pattern;

import com.selzerj.geopattern.svg.SvgImage;
import lombok.Getter;
import lombok.Setter;

public class Pattern {

	@Setter
	@Getter
	private Background background;

	@Setter
	@Getter
	private Structure structure;

	@Setter
	@Getter
	private int height;

	@Setter
	@Getter
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
