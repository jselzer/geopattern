package com.selzerj.geopattern;

import com.selzerj.geopattern.composers.PatternComposer;
import lombok.Getter;
import lombok.Setter;

public class Pattern {

	@Setter
	@Getter
	private Background background;

	public Pattern() {

	}

	public Pattern generateMe(PatternComposer generator) {
		return this;
	}
}
