package com.selzerj.geopattern.internal.composers.structure;

import com.selzerj.geopattern.internal.color.ColorUtils;
import com.selzerj.geopattern.internal.composers.PatternPreset;
import com.selzerj.geopattern.internal.pattern.Seed;
import com.selzerj.geopattern.model.svg.SvgImage;

import java.awt.Color;
import java.util.Map;

public final class OverlappingCirclesComposer extends AbstractStructureComposer {

	private final double radius;

	public OverlappingCirclesComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		this.radius = map(seed.getInteger(0, 1), 0, 15, 25, 200) / 2.0;

		this.width = this.height = this.radius * 6.0;
	}

	@Override
	protected SvgImage generate() {
		SvgImage svgImage = new SvgImage();
		int i = 0;
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 6; x++) {
				int val = seed.getInteger(i, 1);
				double opacity = opacity(val);
				Color fill = fillColor(val);

				Map<String, String> styles = Map.of(
						"fill", ColorUtils.toRgbString(fill),
						"style", String.format("opacity: %s", opacity)
				);

				svgImage.addCircle(x * radius, y * radius, radius, styles);

				// Add an extra one at the top-right, for tiling.
				if (x == 0) {
					svgImage.addCircle(6 * radius, y * radius, radius, styles);
				}

            	// Add an extra row at the end that matches the first row, for tiling.
				if (y == 0) {
					svgImage.addCircle(x * radius, 6 * radius, radius, styles);
				}

				// Add an extra one at bottom-right, for tiling.
				if (x == 0 && y == 0) {
					svgImage.addCircle(6 * radius, 6 * radius, radius, styles);
				}

				i++;
			}
		}

		return svgImage;
	}
}
