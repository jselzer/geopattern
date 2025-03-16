package com.selzerj.geopattern.composers.structure;

import com.selzerj.geopattern.Seed;
import com.selzerj.geopattern.color.ColorUtils;
import com.selzerj.geopattern.composers.PatternPreset;
import com.selzerj.geopattern.svg.SvgImage;

import java.awt.Color;
import java.util.Map;

public class SquaresComposer extends AbstractStructureComposer {

	private final float squareSize;

	public SquaresComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		this.squareSize = map(seed.getInteger(0, 1), 0f, 15f, 10f, 60f);
		this.width = squareSize * 6f;
		this.height = squareSize * 6f;
	}

	@Override
	protected SvgImage generate() {
		SvgImage svgImage = new SvgImage();

		int i = 0;
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 6; x++) {
				final int seedValue = seed.getInteger(i++, 1);
				createSquareAtCoords(svgImage, seedValue, x, y);
			}
		}

		return svgImage;
	}

	private void createSquareAtCoords(SvgImage svgImage, int seedValue, int x, int y) {
		final float opacity = opacity(seedValue);
		final Color fillColor = fillColor(seedValue);

		svgImage.addRect(x * squareSize, y * squareSize, Float.toString(squareSize), Float.toString(squareSize),
				Map.of(
						"fill", ColorUtils.toRgbString(fillColor),
						"fill-opacity", Float.toString(opacity),
						"stroke", ColorUtils.toRgbString(patternPreset.getStrokeColor()),
						"stroke-opacity", Float.toString(patternPreset.getStrokeOpacity()))
		);
	}
}
