package io.github.jselzer.geopattern.internal.colorgenerators;

import io.github.jselzer.geopattern.internal.Seed;
import io.github.jselzer.geopattern.internal.utils.ColorUtils;
import io.github.jselzer.geopattern.internal.utils.MathUtils;
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
		final float hueOffset = (float)MathUtils.map(seed.getInteger(14, 3), 0, 4095, 0, 359);
		final int satOffset = seed.getInteger(17, 1);

		float[] hsl = ColorUtils.hsbToHsl(Color.RGBtoHSB(baseColor.getRed(), baseColor.getGreen(), baseColor.getBlue(), null));
		hsl[0] = hsl[0] - (hueOffset / 360.0f);
		hsl[1] = Math.min(1.0f, Math.max(0.0f, (satOffset % 2 == 0) ?
				hsl[1] + ((float)satOffset / 100.0f) :
				hsl[1] - ((float)satOffset / 100.0f)));

		float[] hsb = ColorUtils.hslToHsb(hsl);
		return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
	}
}
