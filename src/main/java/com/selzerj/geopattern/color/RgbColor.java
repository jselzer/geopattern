package com.selzerj.geopattern.color;

import lombok.Value;

// FIXME, package
@Value
public class RgbColor {
	int red;
	int green;
	int blue;

	public static RgbColor fromHtmlColor(String htmlColor) {
		return new RgbColor(
				Integer.parseInt(htmlColor.substring(1, 3), 16),
				Integer.parseInt(htmlColor.substring(3, 5), 16),
				Integer.parseInt(htmlColor.substring(5, 7), 16)
		);
	}

	public String toHtmlColor() {
		return String.format("#%02x%02x%02x", red, green, blue);
	}
}
