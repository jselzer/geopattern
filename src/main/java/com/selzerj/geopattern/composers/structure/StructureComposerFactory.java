package com.selzerj.geopattern.composers.structure;

import com.selzerj.geopattern.composers.PatternPreset;
import com.selzerj.geopattern.pattern.Seed;

public class StructureComposerFactory {

	public static <T extends AbstractStructureComposer> T createStructureComposer(Class<T> clazz, Seed seed, PatternPreset patternPreset) {
		try {
			return clazz.getConstructor(Seed.class, PatternPreset.class).newInstance(seed, patternPreset);
		} catch (Exception e) {
			throw new RuntimeException("Error creating structure composer", e);
		}
	}
}
