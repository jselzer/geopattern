package com.selzerj.geopattern.colorgenerators;

import com.selzerj.geopattern.color.RgbColor;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimpleColorGenerator implements ColorGenerator {

	private String htmlColor;

	@Override
	public RgbColor generate() {
		return RgbColor.fromHtmlColor(htmlColor);
	}
}
