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
	private float height;

	@Setter
	@Getter
	private float width;

	public Pattern() {

	}

	public String toSvg() {
		return new SvgImage((int)width, (int)height)
				.addBody(background.getSvgImage().getBody())
				.addBody(structure.getSvgImage().getBody())
				.toString();
	}

	// TODO, Base64 encoding
}
