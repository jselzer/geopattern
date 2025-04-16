package com.selzerj.geopattern.composers.structure;

import com.selzerj.geopattern.color.ColorUtils;
import com.selzerj.geopattern.composers.PatternPreset;
import com.selzerj.geopattern.pattern.Seed;
import com.selzerj.geopattern.svg.SvgImage;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

public class MosaicSquaresComposer extends AbstractStructureComposer {

	private final double triangleSize;

	public MosaicSquaresComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		this.triangleSize = map(seed.getInteger(0, 1), 0, 15, 15, 50);

		this.width = this.height = triangleSize * 8;
	}


	@Override
	protected SvgImage generate() {
		SvgImage svgImage = new SvgImage();
		int i = 0;

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (x % 2 == 0) {
					if (y % 2 == 0) {
						drawOuterMosaicTile(svgImage, x * triangleSize * 2, y * triangleSize * 2,
								triangleSize, seed.getInteger(i, 1));
					} else {
						drawInnerMosaicTile(svgImage, x * triangleSize * 2, y * triangleSize * 2,
								triangleSize, seed.getInteger(i, 1), seed.getInteger(i + 1, 1));
					}
				} else if (y % 2 == 0) {
					drawInnerMosaicTile(svgImage, x * triangleSize * 2, y * triangleSize * 2, triangleSize,
							seed.getInteger(i, 1), seed.getInteger(i + 1, 1));
				} else {
					drawOuterMosaicTile(svgImage, x * triangleSize * 2, y * triangleSize * 2, triangleSize,
							seed.getInteger(i, 1));
				}

				i++;
			}
		}

		return svgImage;
	}

	private void drawInnerMosaicTile(SvgImage svgImage, double x, double y, double triangleSize, int val1, int val2) {
		String triangle = getTrianglePoints(triangleSize);
		double opacity = opacity(val1);
		Color fill = fillColor(val1);
		Map<String, String> styles = new HashMap<>();
		styles.put("stroke", ColorUtils.toRgbString(patternPreset.getStrokeColor()));
		styles.put("stroke-opacity", Double.toString(patternPreset.getStrokeOpacity()));
		styles.put("fill-opacity", Double.toString(opacity));
		styles.put("fill", ColorUtils.toRgbString(fill));

		styles.put("transform", String.format("translate(%s,%s) scale(-1, 1)", x + triangleSize, y));
		svgImage.addPolyline(triangle, styles);

		styles.put("transform", String.format("translate(%s,%s) scale(1, -1)", x + triangleSize, y + triangleSize * 2));
		svgImage.addPolyline(triangle, styles);

		opacity = opacity(val2);
		fill = fillColor(val2);
		styles.put("fill", ColorUtils.toRgbString(fill));
		styles.put("fill-opacity", Double.toString(opacity));

		styles.put("transform", String.format("translate(%s,%s) scale(-1, -1)", x + triangleSize, y + triangleSize * 2));
		svgImage.addPolyline(triangle, styles);

		styles.put("transform", String.format("translate(%s,%s) scale(1, 1)", x + triangleSize, y));
		svgImage.addPolyline(triangle, styles);
	}

	private void drawOuterMosaicTile(SvgImage svgImage, double x, double y, double triangleSize, int val) {
		final double opacity = opacity(val);
		final Color fill = fillColor(val);
		final String triangle = getTrianglePoints(triangleSize);

		Map<String, String> styles = new HashMap<>();
		styles.put("stroke", ColorUtils.toRgbString(patternPreset.getStrokeColor()));
		styles.put("stroke-opacity", Double.toString(patternPreset.getStrokeOpacity()));
		styles.put("fill-opacity", Double.toString(opacity));
		styles.put("fill", ColorUtils.toRgbString(fill));

		styles.put("transform", String.format("translate(%s,%s) scale(1, -1)", x, y + triangleSize));
		svgImage.addPolyline(triangle, styles);

		styles.put("transform", String.format("translate(%s,%s) scale(-1, -1)", x + triangleSize * 2, y + triangleSize));
		svgImage.addPolyline(triangle, styles);

		styles.put("transform", String.format("translate(%s,%s) scale(1, 1)", x, y + triangleSize));
		svgImage.addPolyline(triangle, styles);

		styles.put("transform", String.format("translate(%s,%s) scale(-1, 1)", x + triangleSize * 2, y + triangleSize));
		svgImage.addPolyline(triangle, styles);
	}

	private String getTrianglePoints(double sideLength) {
		return DoubleStream.of(
				0, 0,
				sideLength, sideLength,
				0, sideLength,
				0, 0)
				.mapToObj(Double::toString)
				.collect(Collectors.joining(", "));
	}
}
