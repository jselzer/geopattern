package com.selzerj.geopattern.internal.composers.structure;

import com.selzerj.geopattern.internal.Seed;
import com.selzerj.geopattern.internal.composers.PatternPreset;

public final class StructureComposerFactory {

	public static <T extends AbstractStructureComposer> T createStructureComposer(Class<T> clazz, Seed seed, PatternPreset patternPreset) {
		try {
			return clazz.getConstructor(Seed.class, PatternPreset.class).newInstance(seed, patternPreset);
		} catch (Exception e) {
			throw new RuntimeException("Error creating structure composer", e);
		}
	}
}
