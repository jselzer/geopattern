package com.selzerj.geopattern;

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

	public Pattern() {

	}

	public String toSvg() {
		return new SvgImage()
				.addBody(background.getSvgImage().getBody())
				.addBody(structure.getSvgImage().getBody())
				.toString();
	}
}
