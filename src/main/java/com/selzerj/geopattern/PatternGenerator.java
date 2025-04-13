package com.selzerj.geopattern;

import com.selzerj.geopattern.color.ColorPreset;
import com.selzerj.geopattern.color.ColorPresetMode;
import com.selzerj.geopattern.composers.PatternComposer;
import com.selzerj.geopattern.composers.PatternPreset;
import com.selzerj.geopattern.composers.background.SolidBackgroundComposer;
import com.selzerj.geopattern.composers.structure.StructureComposerFactory;
import com.selzerj.geopattern.pattern.Pattern;
import com.selzerj.geopattern.pattern.PatternSelector;
import com.selzerj.geopattern.pattern.PatternType;
import com.selzerj.geopattern.pattern.Seed;
import lombok.Builder;
import lombok.NonNull;

import java.awt.Color;
import java.util.List;

public class PatternGenerator {

	final private Seed seed;


	// FIXME, should I have different type for background and structure generators?
	private PatternComposer backgroundComposer;
	private PatternComposer structureComposer;
	
	@Builder
	public PatternGenerator(@NonNull String seedString, ColorPreset colorPreset, List<PatternType> desiredPatterns) {
		// FIXME, need to finish porting all the logic in this method
		this.seed = new Seed(seedString);

		if (colorPreset == null) {
			colorPreset = new ColorPreset(new Color(147, 60, 60), ColorPresetMode.ADJUSTABLE);
		}
		backgroundComposer = new SolidBackgroundComposer(seed, colorPreset);


		PatternPreset patternPreset = new PatternPreset()
				.setFillColorDark(new Color(34, 34, 34))
				.setFillColorLight(new Color(221, 221, 221))
				.setStrokeColor(Color.BLACK)
				.setStrokeOpacity(0.02f)
				.setOpacityMin(0.02f)
				.setOpacityMax(0.15f);
		PatternType patternType = new PatternSelector(seed, desiredPatterns).selectPattern();
		structureComposer = StructureComposerFactory.createStructureComposer(patternType.getStructureComposerClass(), seed, patternPreset);
	}

	public Pattern generate() {
		final Pattern pattern = new Pattern();

		backgroundComposer.compose(pattern);
		structureComposer.compose(pattern);

		return pattern;
	}
}
