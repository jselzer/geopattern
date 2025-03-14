package com.selzerj.geopattern.colorgenerators;

import com.selzerj.geopattern.color.ColorUtil;
import lombok.AllArgsConstructor;

import java.awt.Color;

@AllArgsConstructor
public class SimpleColorGenerator implements ColorGenerator {

	private String htmlColor;

	@Override
	public Color generate() {
		return ColorUtil.fromHtmlColor(htmlColor);
	}
}
