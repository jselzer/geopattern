package com.selzerj.geopattern.composers.structure;

import com.selzerj.geopattern.color.ColorUtils;
import com.selzerj.geopattern.composers.PatternPreset;
import com.selzerj.geopattern.pattern.Seed;
import com.selzerj.geopattern.svg.SvgImage;

import java.awt.Color;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class OctagonsComposer extends AbstractStructureComposer {

	private final double squareSize;

	public OctagonsComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		this.squareSize = map(seed.getInteger(0, 1), 0, 15, 10, 60);

		this.width = squareSize * 6.0;
		this.height = this.width;
	}

	@Override
	protected SvgImage generate() {
		SvgImage svgImage = new SvgImage();
		int i = 0;
		final String octagonPoints = getOctagonPoints();

		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 6; x++) {
				int val = seed.getInteger(i, 1);
				double opacity = opacity(val);
				Color fill = fillColor(val);

				Map<String, String> style = Map.of(
						"fill", ColorUtils.toRgbString(fill),
						"fill-opacity", Double.toString(opacity),
						"stroke", ColorUtils.toRgbString(patternPreset.getStrokeColor()),
						"stroke-opacity", Double.toString(patternPreset.getStrokeOpacity()),
						"transform", String.format("translate(%s,%s)", x * squareSize, y * squareSize)
				);

				svgImage.addPolyline(octagonPoints, style);
				i++;
			}
		}

		return svgImage;
	}

	private String getOctagonPoints() {
		double s = squareSize;
		double c = s * 0.33;

		return DoubleStream.of(
				c, 0,
				s - c, 0,
				s, c,
				s, s - c,
				s - c, s,
				c, s,
				0, s - c,
				0, c,
				c, 0)
				.mapToObj(Double::toString)
				.collect(Collectors.joining(","));
	}
}
