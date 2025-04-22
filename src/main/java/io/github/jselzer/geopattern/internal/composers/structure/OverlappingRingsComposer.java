package io.github.jselzer.geopattern.internal.composers.structure;

import io.github.jselzer.geopattern.internal.Seed;
import io.github.jselzer.geopattern.internal.composers.PatternPreset;
import io.github.jselzer.geopattern.internal.utils.ColorUtils;
import io.github.jselzer.geopattern.internal.utils.MathUtils;
import io.github.jselzer.geopattern.model.Svg;

import java.awt.Color;
import java.util.Map;

public final class OverlappingRingsComposer extends AbstractStructureComposer {

	private final double ringSize;
	private final double strokeWidth;

	public OverlappingRingsComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		int scale = seed.getInteger(0, 1);
		this.ringSize = MathUtils.map(scale, 0, 15, 10, 60);
		this.strokeWidth = ringSize / 4;
		this.width = ringSize * 6;
		this.height = ringSize * 6;
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

				Map<String, String> styles = Map.of(
						"fill", "none",
						"stroke", ColorUtils.toRgbString(fill),
						"style", String.format("opacity: %s; stroke-width: %spx", opacity, strokeWidth)
				);

				svg.addCircle(x * ringSize, y * ringSize, ringSize - strokeWidth / 2, styles);

				// Add an extra one at top-right, for tiling.
				if (x == 0) {
					svg.addCircle(6 * ringSize, y * ringSize, ringSize - strokeWidth / 2, styles);
				}

				if (y == 0) {
					svg.addCircle(x * ringSize, 6 * ringSize, ringSize - strokeWidth / 2, styles);
				}

				if (x == 0 && y == 0) {
					svg.addCircle(6 * ringSize, 6 * ringSize, ringSize - strokeWidth / 2, styles);
				}

				i++;
			}
		}

		return svg;
	}
}
