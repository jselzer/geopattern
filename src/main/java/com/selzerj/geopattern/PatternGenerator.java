package com.selzerj.geopattern;

import com.selzerj.geopattern.composers.PatternComposer;

public class PatternGenerator {

	private String seed;


	// FIXME, should I have different type for background and structure generators?
	private PatternComposer backgroundGenerator;
	private PatternComposer structureGenerator;

	public Pattern generate() {
		return new Pattern()
				.generateMe(backgroundGenerator)
				.generateMe(structureGenerator);
	}
}
