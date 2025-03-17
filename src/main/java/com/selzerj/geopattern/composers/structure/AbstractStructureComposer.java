package com.selzerj.geopattern.composers.structure;

import com.selzerj.geopattern.composers.PatternComposer;
import com.selzerj.geopattern.composers.PatternPreset;
import com.selzerj.geopattern.pattern.Pattern;
import com.selzerj.geopattern.pattern.Seed;
import com.selzerj.geopattern.pattern.Structure;
import com.selzerj.geopattern.svg.SvgImage;

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
		pattern.setStructure(new Structure(generate()));
		pattern.setHeight((int)height);
		pattern.setWidth((int)width);
	}

	protected Color fillColor(int value) {
		return value % 2 == 0 ?
				this.patternPreset.getFillColorLight() :
				this.patternPreset.getFillColorDark();
	}

	protected float opacity(int value) {
		return map((float)value, 0.0f, 15.0f, patternPreset.getOpacityMin(), patternPreset.getOpacityMax());
	}

	// FIXME, code duplication with AdjustableColorGenerator map method
	protected float map(float value, float inputMin, float inputMax, float outputMin, float outputMax) {
		float inputRange = inputMax - inputMin;
		float outputRange = outputMax - outputMin;

		return ((value - inputMin) * outputRange / inputRange) + outputMin;
	}

	protected abstract SvgImage generate();
}
