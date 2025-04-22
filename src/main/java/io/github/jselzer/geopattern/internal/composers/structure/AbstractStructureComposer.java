package io.github.jselzer.geopattern.internal.composers.structure;

import io.github.jselzer.geopattern.internal.Seed;
import io.github.jselzer.geopattern.internal.composers.PatternComposer;
import io.github.jselzer.geopattern.internal.composers.PatternPreset;
import io.github.jselzer.geopattern.internal.utils.MathUtils;
import io.github.jselzer.geopattern.model.Pattern;
import io.github.jselzer.geopattern.model.Svg;

import java.awt.Color;

public abstract class AbstractStructureComposer implements PatternComposer {

	final protected Seed seed;
	final protected PatternPreset patternPreset;

	protected double width;
	protected double height;

	AbstractStructureComposer(Seed seed, PatternPreset patternPreset) {
		this.seed = seed;
		this.patternPreset = patternPreset;
		this.width = 100.0f;
		this.height = 100.0f;
	}

	@Override
	public void compose(Pattern pattern) {
		pattern.setStructure(generate());
		pattern.setHeight((int)height);
		pattern.setWidth((int)width);
	}

	protected Color fillColor(int value) {
		return value % 2 == 0 ?
				this.patternPreset.getFillColorLight() :
				this.patternPreset.getFillColorDark();
	}

	protected double opacity(int value) {
		return MathUtils.map(value, 0.0, 15.0, patternPreset.getOpacityMin(), patternPreset.getOpacityMax());
	}

	protected abstract Svg generate();
}
