package io.github.jselzer.geopattern.internal.composers.background;

import io.github.jselzer.geopattern.internal.Seed;
import io.github.jselzer.geopattern.internal.colorgenerators.AdjustableColorGenerator;
import io.github.jselzer.geopattern.internal.colorgenerators.ColorGenerator;
import io.github.jselzer.geopattern.internal.colorgenerators.FixedColorGenerator;
import io.github.jselzer.geopattern.internal.composers.PatternComposer;
import io.github.jselzer.geopattern.internal.utils.ColorUtils;
import io.github.jselzer.geopattern.model.ColorPreset;
import io.github.jselzer.geopattern.model.FixedColorPreset;
import io.github.jselzer.geopattern.model.Pattern;
import io.github.jselzer.geopattern.model.Svg;

import java.awt.Color;
import java.util.Map;

public final class SolidBackgroundComposer implements PatternComposer {

	private final Color backgroundColor;

	public SolidBackgroundComposer(Seed seed, ColorPreset colorPreset) {
		this.backgroundColor = initBackgroundColor(colorPreset, seed);
	}

	@Override
	public void compose(Pattern pattern) {
		pattern.setBackground(new Svg()
			.addRect(0.0, 0.0, "100%", "100%",
				Map.of("fill", ColorUtils.toRgbString(this.backgroundColor))));
	}

	private Color initBackgroundColor(ColorPreset colorPreset, Seed seed) {
		final ColorGenerator colorGenerator = colorPreset instanceof FixedColorPreset ?
				new FixedColorGenerator(colorPreset.getColor()) :
				new AdjustableColorGenerator(colorPreset.getColor(), seed);

		return colorGenerator.generate();
	}
}
