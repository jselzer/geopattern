package com.selzerj.geopattern.colorgenerators;

import com.selzerj.geopattern.color.RgbColor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseColorGenerator implements ColorGenerator {

	private final String htmlColor;
	private final String seed;


	@Override
	public RgbColor generate() {
		return transform(htmlColor, seed);
	}

	private RgbColor transform(String htmlColor, String seed) {
		final int hueOffset = 0;
		final int satOffset = 0;



		// FIXME, implement
		return RgbColor.fromHtmlColor(htmlColor);
	}
}
