package com.selzerj.geopattern.internal.composers.structure;

import com.selzerj.geopattern.internal.Seed;
import com.selzerj.geopattern.internal.composers.PatternPreset;
import com.selzerj.geopattern.internal.utils.ColorUtils;
import com.selzerj.geopattern.internal.utils.MathUtils;
import com.selzerj.geopattern.model.svg.Svg;

import java.awt.Color;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public final class OctagonsComposer extends AbstractStructureComposer {

	private final double squareSize;

	public OctagonsComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		this.squareSize = MathUtils.map(seed.getInteger(0, 1), 0, 15, 10, 60);

		this.width = this.height = squareSize * 6.0;
	}

	@Override
	protected Svg generate() {
		Svg svg = new Svg();
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

				svg.addPolyline(octagonPoints, style);
				i++;
			}
		}

		return svg;
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
