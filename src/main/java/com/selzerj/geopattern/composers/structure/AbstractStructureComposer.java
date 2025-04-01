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

	protected double opacity(int value) {
		return map(value, 0.0, 15.0, patternPreset.getOpacityMin(), patternPreset.getOpacityMax());
	}

	// FIXME, code duplication with AdjustableColorGenerator map method
	protected double map(double value, double inputMin, double inputMax, double outputMin, double outputMax) {
		double inputRange = inputMax - inputMin;
		double outputRange = outputMax - outputMin;

		return ((value - inputMin) * outputRange / inputRange) + outputMin;
	}

	protected abstract SvgImage generate();
}
