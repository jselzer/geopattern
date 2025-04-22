package io.github.jselzer.geopattern.internal.composers.structure;

import io.github.jselzer.geopattern.internal.Seed;
import io.github.jselzer.geopattern.internal.composers.PatternPreset;
import io.github.jselzer.geopattern.internal.utils.ColorUtils;
import io.github.jselzer.geopattern.internal.utils.MathUtils;
import io.github.jselzer.geopattern.model.Svg;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public final class TrianglesComposer  extends AbstractStructureComposer {

	private final double sideLength;
	private final double triangleHeight;

	public TrianglesComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		int scale = seed.getInteger(0, 1);
		this.sideLength = MathUtils.map(scale, 0, 15, 15, 80);
		this.triangleHeight = sideLength / 2 * Math.sqrt(3);

		this.width = sideLength * 3;
		this.height = triangleHeight * 6;
	}

	@Override
	protected Svg generate() {
		Svg svg = new Svg();
		int i = 0;
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 6; x++) {
				int val = seed.getInteger(i, 1);
				double opacity = opacity(val);
				Color fill = fillColor(val);

				Map<String, String> styles = new HashMap<>();
				styles.put("fill", ColorUtils.toRgbString(fill));
				styles.put("fill-opacity", Double.toString(opacity));
				styles.put("stroke", ColorUtils.toRgbString(patternPreset.getStrokeColor()));
				styles.put("stroke-opacity", Double.toString(patternPreset.getStrokeOpacity()));

				double rotation = (y % 2 == 0) ?
						((x % 2 == 0) ? 180 : 0) :
						((x % 2 != 0) ? 180 : 0);
				String trianglePoints = getTrianglePoints(sideLength, triangleHeight);

				styles.put("transform", String.format("translate(%s, %s) rotate(%s, %s, %s)",
						x * sideLength * 0.5 - sideLength / 2, triangleHeight * y,
						rotation, sideLength / 2, triangleHeight / 2));
				svg.addPolyline(trianglePoints, styles);

				// Add an extra one at the top-right, for tiling
				if (x == 0) {
					styles.put("transform", String.format("translate(%s, %s) rotate(%s, %s, %s)",
							6 * sideLength * 0.5 - sideLength / 2, triangleHeight * y,
							rotation, sideLength / 2, triangleHeight / 2));
					svg.addPolyline(trianglePoints, styles);
				}

				i++;
			}
		}

		return svg;
	}

	private String getTrianglePoints(double sideLength, double triangleHeight) {
		return DoubleStream.of(
				sideLength / 2, 0,
				sideLength, triangleHeight,
				0, triangleHeight,
				sideLength / 2, 0
				).mapToObj(Double::toString)
				.collect(Collectors.joining(", "));
	}
}
