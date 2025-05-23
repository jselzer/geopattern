package io.github.jselzer.geopattern.internal.composers.structure;

import io.github.jselzer.geopattern.internal.Seed;
import io.github.jselzer.geopattern.internal.composers.PatternPreset;
import io.github.jselzer.geopattern.internal.utils.ColorUtils;
import io.github.jselzer.geopattern.internal.utils.MathUtils;
import io.github.jselzer.geopattern.model.Svg;

import java.awt.Color;
import java.util.Map;

public final class SquaresComposer extends AbstractStructureComposer {

	private final double squareSize;

	public SquaresComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		this.squareSize = MathUtils.map(seed.getInteger(0, 1), 0, 15, 10, 60);
		this.width = squareSize * 6f;
		this.height = squareSize * 6f;
	}

	@Override
	protected Svg generate() {
		Svg svg = new Svg();

		int i = 0;
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 6; x++) {
				final int seedValue = seed.getInteger(i++, 1);
				createSquareAtCoords(svg, seedValue, x, y);
			}
		}

		return svg;
	}

	private void createSquareAtCoords(Svg svg, int seedValue, int x, int y) {
		final double opacity = opacity(seedValue);
		final Color fillColor = fillColor(seedValue);

		svg.addRect(x * squareSize, y * squareSize, Double.toString(squareSize), Double.toString(squareSize),
				Map.of(
						"fill", ColorUtils.toRgbString(fillColor),
						"fill-opacity", Double.toString(opacity),
						"stroke", ColorUtils.toRgbString(patternPreset.getStrokeColor()),
						"stroke-opacity", Double.toString(patternPreset.getStrokeOpacity()))
		);
	}
}
