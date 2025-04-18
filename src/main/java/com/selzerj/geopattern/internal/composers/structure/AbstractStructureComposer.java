package com.selzerj.geopattern.internal.composers.structure;

import com.selzerj.geopattern.internal.composers.PatternComposer;
import com.selzerj.geopattern.internal.composers.PatternPreset;
import com.selzerj.geopattern.internal.pattern.Seed;
import com.selzerj.geopattern.internal.utils.MathUtils;
import com.selzerj.geopattern.model.pattern.Pattern;
import com.selzerj.geopattern.model.svg.SvgImage;

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

	protected abstract SvgImage generate();
}
