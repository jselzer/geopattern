package com.selzerj.geopattern.color;

import lombok.Value;

@Value
public class HslColor {

	// Maximum resolution for colour math - any value <= this is treated as zero
	private static final double ZERO_EPSILON = 1e-5;
	// Maximum resolution for colour comparison - any difference <= this is treated as equal
	private static final double COMPARISON_EPSILON = 1e-4;

	double hue;
	double saturation;
	double lightness;

	public static HslColor fromRgbColor(RgbColor rgbColor) {
		double red = rgbColor.getRed() / 255.0;
		double green = rgbColor.getGreen() / 255.0;
		double blue = rgbColor.getBlue() / 255.0;

		double max = Math.max(red, Math.max(green, blue));
		double min = Math.min(red, Math.min(green, blue));
		double delta = max - min;

		double lightness = (max + min) / 2.0;
		if (Math.abs(delta) < ZERO_EPSILON) {
			return new HslColor(0.0, 0.0, lightness * 100.0);
		}

		double saturation;
		if ((lightness - 0.5) <= ZERO_EPSILON) {
			saturation = delta / (max + min);
		} else {
			saturation = delta / (2.0 - max - min);
		}

		double sixth = 1.0 / 6.0;
		double hue = 0.0;
		if (red == max) {
			hue = sixth * ((green - blue) / delta);
			if (green < blue) {
				hue += 1.0;
			}
		} else if (green == max) {
			hue = (sixth * ((blue - red) / delta)) + 1.0 / 3.0;
		} else if (blue == max) {
			hue = (sixth * ((red - green) / delta)) + (2.0 / 3.0);
		}

		if (hue < 0.0) {
			hue += 1.0;
		} else if (hue > 1.0) {
			hue -= 1.0;
		}

		return new HslColor(hue * 360.0, saturation * 100.0, lightness * 100.0);
	}
}
