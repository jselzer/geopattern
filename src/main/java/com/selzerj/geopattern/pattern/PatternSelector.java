package com.selzerj.geopattern.pattern;

import java.util.Arrays;
import java.util.List;

public class PatternSelector {

	private final Seed seed;
	private final List<PatternType> availablePatterns;

	public PatternSelector(Seed seed, List<PatternType> desiredPatterns) {
		this.seed = seed;
		this.availablePatterns = desiredPatterns == null || desiredPatterns.isEmpty() ?
				Arrays.stream(PatternType.values()).toList() : desiredPatterns;
	}

	public PatternType selectPattern() {
		// FIXME, seems like a bug in the original library - shouldn't this be modulus instead of min?  Test with a low # of patterns
		return availablePatterns.get(seed.getInteger(20, 1) % availablePatterns.size());
	}
}
