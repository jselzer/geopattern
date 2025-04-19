package com.selzerj.geopattern.internal.composers.structure;

import com.selzerj.geopattern.internal.Seed;
import com.selzerj.geopattern.internal.composers.PatternPreset;
import com.selzerj.geopattern.internal.utils.ColorUtils;
import com.selzerj.geopattern.internal.utils.MathUtils;
import com.selzerj.geopattern.model.Svg;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public final class HexagonsComposer extends AbstractStructureComposer {

	private final double sideLength;
	private final double hexHeight;
	private final double hexWidth;

	public HexagonsComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		double scale = seed.getInteger(0, 1);
		this.sideLength = MathUtils.map(scale, 0, 15, 8, 60);
		this.hexHeight = sideLength * Math.sqrt(3);
		this.hexWidth = sideLength * 2;

		this.width = hexWidth * 3 + sideLength * 3;
		this.height = hexHeight * 6;
	}

	@Override
	protected Svg generate() {
		Svg svg = new Svg();
		int i = 0;

		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 6; x++) {
				int val = seed.getInteger(i, 1);
				double dy = (x % 2 == 0) ? y * hexHeight : y * hexHeight + hexHeight / 2.0;
				double opacity = opacity(val);
				Color fill = fillColor(val);
				final String hexPoints = getHexagonPoints(sideLength);

				Map<String, String> styles = new HashMap<>();
				styles.put("fill", ColorUtils.toRgbString(fill));
				styles.put("fill-opacity", Double.toString(opacity));
				styles.put("stroke", ColorUtils.toRgbString(patternPreset.getStrokeColor()));
				styles.put("stroke-opacity", Double.toString(patternPreset.getStrokeOpacity()));

				styles.put("transform", String.format("translate(%s, %s)",
						(double)x * sideLength * 1.5 - hexWidth / 2,
						dy - hexHeight / 2));
				svg.addPolyline(hexPoints, styles);

				// Add an extra one at top-right, for tiling.
				if (x == 0) {
					styles.put("transform", String.format("translate(%s, %s)",
							(6 * sideLength * 1.5 - hexWidth / 2),
							dy - hexHeight / 2));
					svg.addPolyline(hexPoints, styles);
				}

				// Add an extra row at the end that matches the first row, for tiling.
				if (y == 0) {
					dy = (x % 2 == 0) ? 6 * hexHeight : 6 * hexHeight + hexHeight / 2;
					styles.put("transform", String.format("translate(%s, %s)",
							(double)x * sideLength * 1.5 - hexWidth / 2,
							dy - hexHeight / 2));
					svg.addPolyline(hexPoints, styles);
				}

            	// Add an extra one at bottom-right, for tiling.
				if (x == 0 && y == 0) {
					styles.put("transform", String.format("translate(%s, %s)",
							(6 * sideLength * 1.5 - hexWidth / 2),
							(5 * hexHeight + hexHeight / 2)));
					svg.addPolyline(hexPoints, styles);
				}

				i++;
			}
		}

		return svg;
	}

	private String getHexagonPoints(double sideLength) {
		double c = sideLength;
		double a = c / 2;
		double b = Math.sin(60.0 * Math.PI / 180.0) * c;

		return DoubleStream.of(
				0, b,
				a, 0,
				a + c, 0,
				2 * c, b,
				a + c, 2 * b,
				a, 2 * b,
				0, b)
				.mapToObj(Double::toString)
				.collect(Collectors.joining(", "));
	}
}
