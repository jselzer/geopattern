package com.selzerj.geopattern.internal.pattern;

import com.selzerj.geopattern.model.PatternType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class PatternSelector {

	private final Seed seed;
	private final List<PatternType> availablePatterns;

	public PatternSelector(Seed seed, List<PatternType> desiredPatterns) {
		this.seed = seed;
		this.availablePatterns = desiredPatterns == null || desiredPatterns.isEmpty() ?
				Arrays.stream(PatternType.values()).collect(Collectors.toList()) : desiredPatterns;
	}

	public PatternType selectPattern() {
		return availablePatterns.get(seed.getInteger(20, 1) % availablePatterns.size());
	}
}
