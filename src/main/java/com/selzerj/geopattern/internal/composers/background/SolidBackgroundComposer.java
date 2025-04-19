package com.selzerj.geopattern.internal.composers.background;

import com.selzerj.geopattern.internal.Seed;
import com.selzerj.geopattern.internal.colorgenerators.AdjustableColorGenerator;
import com.selzerj.geopattern.internal.colorgenerators.ColorGenerator;
import com.selzerj.geopattern.internal.colorgenerators.FixedColorGenerator;
import com.selzerj.geopattern.internal.composers.PatternComposer;
import com.selzerj.geopattern.internal.utils.ColorUtils;
import com.selzerj.geopattern.model.ColorPreset;
import com.selzerj.geopattern.model.ColorPresetMode;
import com.selzerj.geopattern.model.pattern.Pattern;
import com.selzerj.geopattern.model.svg.Svg;

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
		final ColorGenerator colorGenerator = colorPreset.getMode() == ColorPresetMode.FIXED ?
				new FixedColorGenerator(colorPreset.getColor()) :
				new AdjustableColorGenerator(colorPreset.getColor(), seed);

		return colorGenerator.generate();
	}
}
