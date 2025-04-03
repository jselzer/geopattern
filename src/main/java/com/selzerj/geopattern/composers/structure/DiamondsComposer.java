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

public class DiamondsComposer extends AbstractStructureComposer {

	private final double diamondHeight;
	private final double diamondWidth;

	public DiamondsComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		this.diamondWidth = map(seed.getInteger(0, 1), 0, 15, 10, 50);
		this.diamondHeight = map(seed.getInteger(1, 1), 0, 15, 10, 50);

		this.height = diamondHeight * 3;
		this.width = diamondWidth * 6;
	}

	@Override
	protected SvgImage generate() {
		SvgImage svgImage = new SvgImage();
		int i = 0;

		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 6; x++) {
				int value = seed.getInteger(i, 1);
				double opacity = opacity(value);
				Color fill = fillColor(value);

				Map<String, String> styles = new HashMap<>();
				styles.put("fill", ColorUtils.toRgbString(fill));
				styles.put("fill-opacity", Double.toString(opacity));
				styles.put("stroke", ColorUtils.toRgbString(patternPreset.getStrokeColor()));
				styles.put("stroke-opacity", Double.toString(patternPreset.getStrokeOpacity()));

				double dx = (y % 2 == 0) ? 0: diamondWidth / 2;

				String diamond = getDiamondPoints();

				styles.put("transform", String.format("translate(%s,%s)",
								(double)x * diamondWidth - diamondWidth / 2 + dx,
								diamondHeight / 2 * (double)y - diamondHeight / 2));
				svgImage.addPolyline(diamond, styles);

				// Add an extra one at top-left, for tiling.
				if (x == 0) {
					styles.put("transform", String.format("translate(%s,%s)",
							(6 * diamondWidth - diamondWidth / 2 + dx),
							diamondHeight / 2 * (double)y - diamondHeight / 2));
					svgImage.addPolyline(diamond, styles);
				}

				// Add an extra row at the end that matches the first row, for tiling.
				if (y == 0) {
					styles.put("transform", String.format("translate(%s,%s)",
							(double)x * diamondWidth - diamondWidth / 2 + dx,
							diamondHeight / 2 * 6 - diamondHeight / 2));
					svgImage.addPolyline(diamond, styles);
				}

				// Add an extra one at bottom-right, for tiling.
				if (x == 0 && y == 0) {
					styles.put("transform", String.format("translate(%s,%s)",
							(6 * diamondWidth - diamondWidth / 2 + dx),
							diamondHeight / 2 * 6 - diamondHeight / 2));
					svgImage.addPolyline(diamond, styles);
				}

				i++;
			}
		}

		return svgImage;
	}

	private String getDiamondPoints() {
		return DoubleStream.of(diamondWidth / 2, 0,
						diamondWidth, diamondHeight / 2,
						diamondWidth / 2, diamondHeight,
						0, diamondHeight / 2)
				.mapToObj(Double::toString)
				.collect(Collectors.joining(", "));
	}
}
