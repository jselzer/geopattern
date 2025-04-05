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

public class TessellationComposer extends AbstractStructureComposer {

	private final double sideLength;
	private final double hexHeight;
	private final double hexWidth;
	private final double triangleHeight;
	private final String trianglePoints;
	private final double tileWidth;
	private final double tileHeight;

	public TessellationComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		// 3.4.6.4 semi-regular tessellation
		this.sideLength = map(seed.getInteger(0, 1), 0, 15, 5, 40);
		this.hexHeight = sideLength * Math.sqrt(3);
		this.hexWidth = sideLength * 2;
		this.triangleHeight = sideLength / 2 * Math.sqrt(3);
		this.trianglePoints = getRotatedTrianglePoints(sideLength, triangleHeight);
		this.tileWidth = sideLength * 3 + triangleHeight * 2;
		this.tileHeight = hexHeight * 2 + sideLength * 2;

		this.width = tileWidth;
		this.height = tileHeight;
	}

	@Override
	protected SvgImage generate() {
		SvgImage svgImage = new SvgImage();

		for (int i = 0; i < 20; i++) {
			int val = seed.getInteger(i, 1);
			double opacity = opacity(val);
			Color fill = fillColor(val);

			Map<String, String> styles = new HashMap<>();
			styles.put("stroke", ColorUtils.toRgbString(patternPreset.getStrokeColor()));
			styles.put("stroke-opacity", Double.toString(patternPreset.getStrokeOpacity()));
			styles.put("fill", ColorUtils.toRgbString(fill));
			styles.put("fill-opacity", Double.toString(opacity));
			styles.put("stroke-width", "1");

			switch (i) {
				case 0 -> { // all 4 corners
					svgImage.addRect(-sideLength / 2, -sideLength / 2, sideLength, sideLength, styles);
					svgImage.addRect(tileWidth - sideLength / 2, -sideLength / 2, sideLength, sideLength, styles);
					svgImage.addRect(-sideLength / 2, tileHeight - sideLength / 2, sideLength, sideLength, styles);
					svgImage.addRect(tileWidth - sideLength / 2, tileHeight - sideLength / 2, sideLength, sideLength, styles);
				}
				case 1 -> {
					// center / top square
					svgImage.addRect(hexWidth / 2 + triangleHeight, hexHeight / 2, sideLength, sideLength, styles);
				}
				case 2 -> {
					// side squares
					svgImage.addRect(-sideLength / 2, tileHeight / 2 - sideLength / 2, sideLength, sideLength, styles);
					svgImage.addRect(tileWidth - sideLength / 2, tileHeight / 2 - sideLength / 2, sideLength, sideLength, styles);
				}
				case 3 -> {
					// center / bottom square
					svgImage.addRect(hexWidth / 2 + triangleHeight, hexHeight * 1.5 + sideLength, sideLength, sideLength, styles);
				}
				case 4 -> {
					// left top / bottom triangle
					styles.put("transform", String.format("translate(%s,%s) rotate(0, %s, %s)",
							sideLength / 2, -sideLength / 2, sideLength / 2, triangleHeight / 2));
					svgImage.addPolyline(trianglePoints, styles);

					styles.put("transform", String.format("translate(%s,%s) rotate(0, %s, %s) scale(1, -1)",
							sideLength / 2, tileHeight + sideLength / 2, sideLength / 2, triangleHeight / 2));
					svgImage.addPolyline(trianglePoints, styles);
				}
				case 5 -> {
					// right top / bottom triangle
					styles.put("transform", String.format("translate(%s,%s) rotate(0, %s, %s) scale(-1, 1)",
							tileWidth - sideLength / 2, -sideLength / 2, sideLength / 2, triangleHeight / 2));
					svgImage.addPolyline(trianglePoints, styles);

					styles.put("transform", String.format("translate(%s,%s) rotate(0, %s, %s) scale(-1, -1)",
							tileWidth - sideLength / 2, tileHeight + sideLength / 2, sideLength / 2, triangleHeight / 2));
					svgImage.addPolyline(trianglePoints, styles);
				}
				case 6 -> {
					// center / top / right triangle
					styles.put("transform", String.format("translate(%s,%s)",
							tileWidth / 2 + sideLength / 2, hexHeight / 2));
					svgImage.addPolyline(trianglePoints, styles);
				}
				case 7 -> {
					// center / top / left triangle
					styles.put("transform", String.format("translate(%s,%s) scale(-1, 1)",
							tileWidth - tileWidth / 2 - sideLength / 2, hexHeight / 2));
					svgImage.addPolyline(trianglePoints, styles);
				}
				case 8 -> {
					// center / bottom / right triangle
					styles.put("transform", String.format("translate(%s,%s) scale(1, -1)",
							tileWidth / 2 + sideLength / 2, tileHeight - hexHeight / 2));
					svgImage.addPolyline(trianglePoints, styles);
				}
				case 9 -> {
					// center / bottom / left triangle
					styles.put("transform", String.format("translate(%s,%s) scale(-1, -1)",
							tileWidth - tileWidth / 2 - sideLength / 2, tileHeight - hexHeight / 2));
					svgImage.addPolyline(trianglePoints, styles);
				}
				case 10 -> {
					// left / middle triangle
					styles.put("transform", String.format("translate(%s,%s)", sideLength / 2, tileHeight / 2 - sideLength / 2));
					svgImage.addPolyline(trianglePoints, styles);
				}
				case 11 -> {
					// right / middle triangle
					styles.put("transform", String.format("translate(%s,%s) scale(-1,1)",
							tileWidth - sideLength / 2, tileHeight / 2 - sideLength / 2));
					svgImage.addPolyline(trianglePoints, styles);
				}
				case 12 -> {
					// left / top square
					styles.put("transform", String.format("translate(%s,%s) rotate(-30, 0, 0)",
							sideLength / 2, sideLength / 2));
					svgImage.addRect(0, 0, sideLength, sideLength, styles);
				}
				case 13 -> {
					// right / top square
					styles.put("transform", String.format("scale(-1, 1) translate(%s, %s) rotate(-30, 0, 0)",
							-tileWidth + sideLength / 2, sideLength / 2));
					svgImage.addRect(0, 0, sideLength, sideLength, styles);
				}
				case 14 -> {
					// left / center-top square
					styles.put("transform", String.format("translate(%s,%s) rotate(30, 0, %s)",
							sideLength / 2, tileHeight / 2 - sideLength / 2 - sideLength, sideLength));
					svgImage.addRect(0, 0, sideLength, sideLength, styles);
				}
				case 15 -> {
					// right / center-top square
					styles.put("transform", String.format("scale(-1, 1) translate(%s, %s) rotate(30, 0, %s)",
							-tileWidth + sideLength / 2, tileHeight / 2 - sideLength / 2 - sideLength, sideLength));
					svgImage.addRect(0, 0, sideLength, sideLength, styles);
				}
				case 16 -> {
					// left / center-top square
					styles.put("transform", String.format("scale(1, -1) translate(%s, %s) rotate(30, 0, %s)",
							sideLength / 2, -tileHeight + tileHeight / 2 - sideLength / 2 - sideLength, sideLength));
					svgImage.addRect(0, 0, sideLength, sideLength, styles);
				}
				case 17 -> {
					// right / center-bottom square
					styles.put("transform", String.format("scale(-1, -1) translate(%s, %s) rotate(30, 0, %s)",
							-tileWidth + sideLength / 2, -tileHeight + tileHeight / 2 - sideLength / 2 - sideLength, sideLength));
					svgImage.addRect(0, 0, sideLength, sideLength, styles);
				}
				case 18 -> {
					// left / bottom square
					styles.put("transform", String.format("scale(1, -1) translate(%s, %s) rotate(-30, 0, 0)",
							sideLength / 2, -tileHeight + sideLength / 2));
					svgImage.addRect(0, 0, sideLength, sideLength, styles);
				}
				case 19 -> {
					// right / bottom square
					styles.put("transform", String.format("scale(-1, -1) translate(%s, %s) rotate(-30, 0, 0)",
							-tileWidth + sideLength / 2, -tileHeight + sideLength / 2));
					svgImage.addRect(0, 0, sideLength, sideLength, styles);
				}
			}
		}

		return svgImage;
	}

	private String getRotatedTrianglePoints(double sideLength, double triangleHeight) {
		return DoubleStream.of(
				0, 0,
				triangleHeight, sideLength / 2,
				0, sideLength,
				0, 0)
				.mapToObj(Double::toString)
				.collect(Collectors.joining(", "));
	}
}