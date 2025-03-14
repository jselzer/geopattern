package com.selzerj.geopattern.color;

import lombok.experimental.UtilityClass;

import java.awt.Color;

@UtilityClass
public class ColorUtil {

	public static Color fromHtmlColor(String htmlColor) {
		return Color.decode(htmlColor);
	}

	// FIXME, clean up and add tests
	public float[] hsbToHsl(float[] hsb) {
		float[] hsl = new float[3];
		hsl[2] = hsb[2] * (1.0f - hsb[1] / 2.0f);
		hsl[1] = hsl[2] == 0 || hsl[2] == 1 ?
				0 : ((hsb[2] - hsl[2]) / Math.min(hsl[2], 1.0f - hsl[2]));
		hsl[0] = hsb[0];
		return hsl;
	}

	public float[] hslToHsb(float[] hsl) {
		float[] hsb = new float[3];
		hsb[2] = (hsl[2] + hsl[1] * Math.min(hsl[2], 1 - hsl[2]));
		hsb[1] = hsb[2] == 0 ?
				0 : (2 * (1.0f - hsl[2] / hsb[2]));
		hsb[0] = hsl[0];
		return hsb;
	}
}
