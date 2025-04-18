package com.selzerj.geopattern.internal.composers.structure;

import com.selzerj.geopattern.internal.Seed;
import com.selzerj.geopattern.internal.composers.PatternPreset;
import com.selzerj.geopattern.internal.utils.ColorUtils;
import com.selzerj.geopattern.internal.utils.MathUtils;
import com.selzerj.geopattern.model.svg.Svg;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public final class PlusSignsComposer extends AbstractStructureComposer {

	private final double squareSize;
	private final double plusSize;

	public PlusSignsComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		this.squareSize = MathUtils.map(seed.getInteger(0, 1), 0, 15, 10, 25);
		this.plusSize = squareSize * 3;

		this.width = this.height = squareSize * 12;
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
				int dx = (y % 2 == 0) ? 0 : 1;

				Map<String, String> styles = new HashMap<>();
				styles.put("fill", ColorUtils.toRgbString(fill));
				styles.put("stroke", ColorUtils.toRgbString(patternPreset.getStrokeColor()));
				styles.put("stroke-opacity", Double.toString(patternPreset.getStrokeOpacity()));
				styles.put("style", String.format("fill-opacity: %s", opacity));

				final Svg plusShape = getPlusShape(squareSize);
				styles.put("transform", String.format("translate(%s,%s)",
						x * plusSize - x * squareSize + dx * squareSize - squareSize,
						y * plusSize - y * squareSize - plusSize / 2));
				svg.addGroup(plusShape.getBody(), styles);

            	// Add an extra column on the right for tiling.
				if (x == 0) {
					styles.put("transform", String.format("translate(%s,%s)",
							4 * plusSize - x * squareSize + dx * squareSize - squareSize,
							y * plusSize - y * squareSize - plusSize / 2));
					svg.addGroup(plusShape.getBody(), styles);
				}

				// Add an extra row on the bottom that matches the first row, for tiling.
				if (y == 0) {
					styles.put("transform", String.format("translate(%s,%s)",
							x * plusSize - x * squareSize + dx * squareSize - squareSize,
							4 * plusSize - y * squareSize - plusSize / 2));
					svg.addGroup(plusShape.getBody(), styles);
				}

				// Add an extra one at top-right and bottom-right, for tiling.
				if (x == 0 && y == 0) {
					styles.put("transform", String.format("translate(%s,%s)",
							4 * plusSize - x * squareSize + dx * squareSize - squareSize,
							4 * plusSize - y * squareSize - plusSize / 2));
					svg.addGroup(plusShape.getBody(), styles);
				}
				i++;
			}
		}

		return svg;
	}

	private Svg getPlusShape(double squareSize) {
		return new Svg()
				.addRect(squareSize, 0, squareSize, squareSize * 3)
				.addRect(0, squareSize, squareSize * 3, squareSize);
	}
}
