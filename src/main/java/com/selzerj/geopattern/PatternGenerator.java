package com.selzerj.geopattern;

import com.selzerj.geopattern.internal.composers.PatternComposer;
import com.selzerj.geopattern.internal.composers.PatternPreset;
import com.selzerj.geopattern.internal.composers.background.SolidBackgroundComposer;
import com.selzerj.geopattern.internal.composers.structure.StructureComposerFactory;
import com.selzerj.geopattern.internal.pattern.PatternSelector;
import com.selzerj.geopattern.internal.pattern.Seed;
import com.selzerj.geopattern.model.ColorPreset;
import com.selzerj.geopattern.model.ColorPresetMode;
import com.selzerj.geopattern.model.PatternType;
import com.selzerj.geopattern.model.pattern.Pattern;
import lombok.Builder;
import lombok.NonNull;

import java.awt.Color;
import java.util.List;

public final class PatternGenerator {

	// FIXME, should I have different type for background and structure generators?
	private final PatternComposer backgroundComposer;
	private final PatternComposer structureComposer;

	@Builder
	public PatternGenerator(@NonNull String seedString, ColorPreset colorPreset, List<PatternType> desiredPatterns) {
		Seed seed = new Seed(seedString);

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
