package com.selzerj.geopattern;

import com.selzerj.geopattern.color.ColorPreset;
import com.selzerj.geopattern.color.ColorPresetMode;
import com.selzerj.geopattern.composers.PatternComposer;
import com.selzerj.geopattern.composers.PatternPreset;
import com.selzerj.geopattern.composers.background.SolidBackgroundComposer;
import com.selzerj.geopattern.composers.structure.SquaresComposer;

import java.awt.Color;

public class PatternGenerator {

	final private Seed seed;


	// FIXME, should I have different type for background and structure generators?
	private PatternComposer backgroundComposer;
	private PatternComposer structureComposer;

	public PatternGenerator(String seedString) {
		// FIXME, need to finish porting all the logic in this method
		this.seed = new Seed(seedString);

		PatternPreset patternPreset = new PatternPreset()
				.setFillColorDark(new Color(34, 34, 34))
				.setFillColorLight(new Color(221, 221, 221))
				.setStrokeColor(Color.BLACK)
				.setStrokeOpacity(0.02f)
				.setOpacityMin(0.02f)
				.setOpacityMax(0.15f);
		ColorPreset colorPreset = new ColorPreset(new Color(147, 60, 60), ColorPresetMode.FIXED);

		backgroundComposer = new SolidBackgroundComposer(seed, colorPreset);
		structureComposer = new SquaresComposer(seed, patternPreset);
	}

	public Pattern generate() {
		final Pattern pattern = new Pattern();

		backgroundComposer.compose(pattern);
		structureComposer.compose(pattern);

		return pattern;
	}
}
