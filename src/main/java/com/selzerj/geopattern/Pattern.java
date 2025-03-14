package com.selzerj.geopattern;

import com.selzerj.geopattern.composers.PatternComposer;
import com.selzerj.geopattern.svg.SvgImage;

public class Pattern {

	private SvgImage svgImage;

	public Pattern() {
		this(new SvgImage());
	}

	public Pattern(SvgImage svgImage) {
		this.svgImage = svgImage;
	}

	public Pattern generateMe(PatternComposer generator) {
		return this;
	}
}
