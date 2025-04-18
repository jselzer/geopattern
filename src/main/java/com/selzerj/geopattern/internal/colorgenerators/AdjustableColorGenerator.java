package com.selzerj.geopattern.internal.colorgenerators;

import com.selzerj.geopattern.internal.color.ColorUtils;
import com.selzerj.geopattern.internal.pattern.Seed;
import lombok.AllArgsConstructor;

import java.awt.Color;

@AllArgsConstructor
public final class AdjustableColorGenerator implements ColorGenerator {

	private final Color baseColor;
	private final Seed seed;

	@Override
	public Color generate() {
		return transform(baseColor, seed);
	}

	private Color transform(Color baseColor, Seed seed) {
		final float hueOffset = map(seed.getInteger(14, 3), 0, 4095, 0, 359);
		final int satOffset = seed.getInteger(17, 1);

		float[] hsl = ColorUtils.hsbToHsl(Color.RGBtoHSB(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), null));
		hsl[0] = hsl[0] - (hueOffset / 360.0f);
		hsl[1] = Math.min(1.0f, Math.max(0.0f, (satOffset % 2 == 0) ?
				hsl[1] + ((float)satOffset / 100.0f) :
				hsl[1] - ((float)satOffset / 100.0f)));

		float[] hsb = ColorUtils.hslToHsb(hsl);
		return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
	}

	private float map(int value, int inputMin, int inputMax, int outputMin, int outputMax) {
		float inputRange = inputMax - inputMin;
		float outputRange = outputMax - outputMin;

		return (((float)value - inputMin) * outputRange / inputRange) + outputMin;
	}
}
