package com.selzerj.geopattern;

import com.selzerj.geopattern.internal.Seed;
import com.selzerj.geopattern.internal.composers.PatternComposer;
import com.selzerj.geopattern.internal.composers.PatternPreset;
import com.selzerj.geopattern.internal.composers.background.SolidBackgroundComposer;
import com.selzerj.geopattern.internal.composers.structure.StructureComposerFactory;
import com.selzerj.geopattern.internal.pattern.PatternSelector;
import com.selzerj.geopattern.model.AdjustableColorPreset;
import com.selzerj.geopattern.model.ColorPreset;
import com.selzerj.geopattern.model.Pattern;
import com.selzerj.geopattern.model.PatternType;
import lombok.Builder;
import lombok.NonNull;

import java.awt.Color;
import java.util.List;

/**
 * PatternGenerator is responsible for creating patterns based on a seed string, optional color presets,
 * and optional desired patterns.
 */
public final class PatternGenerator {

	private final PatternComposer backgroundComposer;
	private final PatternComposer structureComposer;

	/**
	 * Creates a new instance of the PatternGenerator class using the provided seed string.
	 * @param seedString seed string to use for generating the pattern
	 */
	public PatternGenerator(@NonNull String seedString) {
		this(seedString, null, null);
	}

	/**
	 * Creates a new instance of the PatternGenerator class using the provided seed string and color preset.
	 * @param seedString seed string to use for generating the pattern
	 * @param colorPreset color preset to use for generating the pattern (fixed or adjusted by the seed string)
	 */
	public PatternGenerator(@NonNull String seedString, ColorPreset colorPreset) {
		this(seedString, colorPreset, null);
	}

	/**
	 * Creates a new instance of the PatternGenerator class using the provided seed string and desired patterns.
	 * @param seedString seed string to use for generating the pattern
	 * @param desiredPatterns list of patterns to select from when generating the pattern
	 */
	public PatternGenerator(@NonNull String seedString, List<PatternType> desiredPatterns) {
		this(seedString, null, desiredPatterns);
	}

	/**
	 * Creates a new instance of the PatternGenerator class using the provided seed string, color preset, and desired patterns.
	 * @param seedString seed string to use for generating the pattern
	 * @param colorPreset color preset to use for generating the pattern (fixed or adjusted by the seed string)
	 * @param desiredPatterns list of patterns to select from when generating the pattern
	 */
	@Builder
	public PatternGenerator(@NonNull String seedString, ColorPreset colorPreset, List<PatternType> desiredPatterns) {
		Seed seed = new Seed(seedString);

		if (colorPreset == null) {
			colorPreset = new AdjustableColorPreset(new Color(147, 60, 60));
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

	/**
	 * Generates a geopattern based on the parameters provided to the constructor.
	 * @return a geopattern object that may be converted to an SVG image
	 */
	public Pattern generate() {
		final Pattern pattern = new Pattern();

		backgroundComposer.compose(pattern);
		structureComposer.compose(pattern);

		return pattern;
	}
}
