package com.selzerj.geopattern.composers.structure;

import com.selzerj.geopattern.color.ColorUtils;
import com.selzerj.geopattern.composers.PatternPreset;
import com.selzerj.geopattern.pattern.Seed;
import com.selzerj.geopattern.svg.SvgImage;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class XesComposer extends AbstractStructureComposer {

	private final SvgImage plusShape;
	private final double xSize;


	public XesComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		double squareSize = map(seed.getInteger(0, 1), 0, 15, 10, 25);
		this.plusShape = getPlusShape(squareSize);
		this.xSize = squareSize * 3 * 0.943;

		this.width = this.height = xSize * 3;
	}

	@Override
	protected SvgImage generate() {
		SvgImage svgImage = new SvgImage();
		int i = 0;

		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 6; x++) {
				int val = seed.getInteger(i, 1);
				double opacity = opacity(val);
				double dy = (x % 2 == 0) ?
						y * xSize - xSize * 0.5 :
						y * xSize - xSize * 0.5 + xSize / 4;
				Color fill = fillColor(val);

				Map<String, String> styles = new HashMap<>();
				styles.put("fill", ColorUtils.toRgbString(fill));
				styles.put("style", String.format("opacity: %s", opacity));

				styles.put("transform", String.format("translate(%s, %s) rotate(45, %s, %s)",
						x * xSize / 2 - xSize / 2, dy - y * xSize / 2, xSize / 2, xSize / 2));
				svgImage.addGroup(plusShape.getBody(), styles);

				// Add an extra column on the right for tiling.
				if (x == 0) {
					styles.put("transform", String.format("translate(%s, %s) rotate(45, %s, %s)",
							6 * xSize / 2 - xSize / 2, dy - y * xSize / 2, xSize / 2, xSize / 2));
					svgImage.addGroup(plusShape.getBody(), styles);
				}

				// Add an extra row on the bottom that matches the first row, for tiling
				if (y == 0) {
					dy = (x % 2 == 0) ?
							6 * xSize - xSize / 2 :
							6 * xSize - xSize / 2 + xSize / 4;
					styles.put("transform", String.format("translate(%s, %s) rotate(45, %s, %s)",
							x * xSize / 2 - xSize / 2, dy - 6 * xSize / 2, xSize / 2, xSize / 2));
					svgImage.addGroup(plusShape.getBody(), styles);
				}

				// These can hang off the bottom, so put a row at the top for tiling
				if (y == 5) {
					styles.put("transform", String.format("translate(%s, %s) rotate(45, %s, %s)",
							x * xSize / 2 - xSize / 2, dy - 11 * xSize / 2, xSize / 2, xSize / 2));
					svgImage.addGroup(plusShape.getBody(), styles);
				}

				// Add an extra one at top-right and bottom-right, for tiling.
				if (x == 0 && y == 0) {
					styles.put("transform", String.format("translate(%s, %s) rotate(45, %s, %s)",
							6 * xSize / 2 - xSize / 2, dy - 6 * xSize / 2, xSize / 2, xSize / 2));
					svgImage.addGroup(plusShape.getBody(), styles);
				}

				i++;
			}
		}


		return svgImage;
	}

	// FIXME, duplication with PlusSignsComposer
	private SvgImage getPlusShape(double squareSize) {
		return new SvgImage()
				.addRect(squareSize, 0, squareSize, squareSize * 3)
				.addRect(0, squareSize, squareSize * 3, squareSize);
	}
}
