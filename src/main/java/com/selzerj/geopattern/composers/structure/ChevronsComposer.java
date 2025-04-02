package com.selzerj.geopattern.composers.structure;

import com.selzerj.geopattern.color.ColorUtils;
import com.selzerj.geopattern.composers.PatternPreset;
import com.selzerj.geopattern.pattern.Seed;
import com.selzerj.geopattern.svg.SvgImage;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class ChevronsComposer extends AbstractStructureComposer {

	private final double chevronWidth;
	private final double chevronHeight;
	private final SvgImage chevronShape;

	public ChevronsComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		this.chevronWidth = this.map(this.seed.getInteger(0, 1), 0, 15, 30, 80);
		this.chevronHeight = this.map(this.seed.getInteger(0, 1), 0, 15, 30, 80);
		this.chevronShape = initChevronShape(chevronWidth, chevronHeight);


		this.width  = chevronWidth * 6.0;
		this.height = chevronWidth * 6.0 * 0.66;
	}

	private SvgImage initChevronShape(double chevronWidth, double chevronHeight) {
		final double e = chevronHeight * 0.66;

		// FIXME change how we handle SvgImage - image should not be used to carry fragments of SVG
		// FIXME, is there a better way to build a string with a bunch of doubles?
		SvgImage chevronShape = new SvgImage();
		chevronShape.addPolyline(String.format("0,0,%s,%s,%s,%s,0,%s,0,0",
				chevronWidth / 2.0, chevronHeight - e, chevronWidth / 2.0, chevronHeight, e));
		chevronShape.addPolyline(String.format("%s,%s,%s,0,%s,%s,%s,%s,%s,%s",
				chevronWidth / 2.0, chevronHeight - e, chevronWidth, chevronWidth, e, chevronWidth / 2.0, chevronHeight, chevronWidth / 2.0, chevronHeight - e));
		return chevronShape;
	}

	@Override
	protected SvgImage generate() {
		SvgImage svgImage = new SvgImage();
		int i = 0;
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 6; x++) {
				int val = seed.getInteger(i, 1);
				double opacity = opacity(val);
				Color fillColor = fillColor(val);

				Map<String, String> styles = new HashMap<>();
				styles.put("stroke", ColorUtils.toRgbString(patternPreset.getStrokeColor()));
				styles.put("stroke-opacity", Double.toString(patternPreset.getStrokeOpacity()));
				styles.put("fill", ColorUtils.toRgbString(fillColor));
				styles.put("fill-opacity", Double.toString(opacity));
				styles.put("stroke-width", "1");

				styles.put("transform", String.format("translate(%s,%s)", (double)x * chevronWidth, (double)y * chevronHeight * 0.66 - chevronHeight / 2.0));
				svgImage.addGroup(chevronShape.getBody(), styles);

				// Add an extra row at the end that matches the first row, for tiling.
				if (y == 0) {
					styles.put("transform", String.format("translate(%s,%s)", (double)x * chevronWidth, 6.0 * chevronHeight * 0.66 - chevronHeight / 2.0));
					svgImage.addGroup(chevronShape.getBody(), styles);
				}
				i++;
			}
		}

		return svgImage;
	}
}
