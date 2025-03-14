package com.selzerj.geopattern.colorgenerators;

import com.selzerj.geopattern.Seed;
import com.selzerj.geopattern.color.ColorUtil;
import lombok.AllArgsConstructor;

import java.awt.Color;

@AllArgsConstructor
public class BaseColorGenerator implements ColorGenerator {

	private final String htmlColor;
	private final Seed seed;


	@Override
	public Color generate() {
		return transform(htmlColor, seed);
	}

	private Color transform(String htmlColor, Seed seed) {
		final float hueOffset = map(seed.getInteger(14, 3), 0, 4095, 0, 359);
		final int satOffset = seed.getInteger(17, 1);

		Color rgbColor = ColorUtil.fromHtmlColor(htmlColor);
		float[] hsl = ColorUtil.hsbToHsl(Color.RGBtoHSB(rgbColor.getRed(), rgbColor.getGreen(), rgbColor.getBlue(), null));
		hsl[0] = hsl[0] - (hueOffset / 360.0f);
		hsl[1] = Math.clamp((satOffset % 2 == 0) ?
				hsl[1] + ((float)satOffset / 100.0f) :
				hsl[1] - ((float)satOffset / 100.0f), 0.0f, 1.0f);

		float[] hsb = ColorUtil.hslToHsb(hsl);
		return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
	}

	private float map(int value, int inputMin, int inputMax, int outputMin, int outputMax) {
		float inputRange = inputMax - inputMin;
		float outputRange = outputMax - outputMin;

		return (((float)value - inputMin) * outputRange / inputRange) + outputMin;
	}
}
