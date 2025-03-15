package com.selzerj.geopattern.composers.background;

import com.selzerj.geopattern.Background;
import com.selzerj.geopattern.Pattern;
import com.selzerj.geopattern.Seed;
import com.selzerj.geopattern.color.ColorPreset;
import com.selzerj.geopattern.color.ColorPresetMode;
import com.selzerj.geopattern.color.ColorUtils;
import com.selzerj.geopattern.colorgenerators.AdjustableColorGenerator;
import com.selzerj.geopattern.colorgenerators.ColorGenerator;
import com.selzerj.geopattern.colorgenerators.FixedColorGenerator;
import com.selzerj.geopattern.composers.PatternComposer;
import com.selzerj.geopattern.svg.SvgImage;

import java.awt.Color;
import java.util.Map;

public class SolidBackgroundComposer implements PatternComposer {

	private final ColorPreset colorPreset;
	private final Color backgroundColor;

	public SolidBackgroundComposer(ColorPreset colorPreset, Seed seed) {
		this.colorPreset = colorPreset;
		this.backgroundColor = initBackgroundColor(colorPreset, seed);
	}

	@Override
	public void compose(Pattern pattern) {
		SvgImage backgroundImage = new SvgImage();
		backgroundImage.addRect(0, 0, "100%", "100%",
				Map.of("fill", ColorUtils.toRgbString(this.backgroundColor)));

		// TODO, consider replacing Pattern with a PatternBuilder?
		pattern.setBackground(new Background(backgroundImage));
	}

	private Color initBackgroundColor(ColorPreset colorPreset, Seed seed) {
		final ColorGenerator colorGenerator = colorPreset.getMode() == ColorPresetMode.FIXED ?
				new FixedColorGenerator(colorPreset.getColor()) :
				new AdjustableColorGenerator(colorPreset.getColor(), seed);

		return colorGenerator.generate();
	}
}
