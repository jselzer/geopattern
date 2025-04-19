package com.selzerj.geopattern.internal.composers.structure;

import com.selzerj.geopattern.internal.Seed;
import com.selzerj.geopattern.internal.composers.PatternPreset;
import com.selzerj.geopattern.internal.utils.ColorUtils;
import com.selzerj.geopattern.internal.utils.MathUtils;
import com.selzerj.geopattern.model.Svg;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public final class NestedSquaresComposer extends AbstractStructureComposer {

	private final double blockSize;
	private final double squareSize;

	public NestedSquaresComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		this.blockSize = MathUtils.map(seed.getInteger(0, 1), 0, 15, 4, 12);
		this.squareSize = blockSize * 7.0;

		this.width = this.height = (squareSize + blockSize) * 6.0 + blockSize * 6;
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

				Map<String, String> styles = new HashMap<>();
				styles.put("fill", "none");
				styles.put("stroke", ColorUtils.toRgbString(fill));
				styles.put("style", String.format("opacity: %s; stroke-width: %spx", opacity, blockSize));

				svg.addRect(x * squareSize + x * blockSize * 2 + blockSize / 2,
						y * squareSize + y * blockSize * 2 + blockSize / 2,
						squareSize, squareSize, styles);

				val = seed.getInteger(39 - i, 1);
				opacity = opacity(val);
				fill = fillColor(val);

				styles.put("fill", "none");
				styles.put("stroke", ColorUtils.toRgbString(fill));
				styles.put("style", String.format("opacity: %s; stroke-width: %spx", opacity, blockSize));

				svg.addRect(x * squareSize + x * blockSize * 2 + blockSize / 2 + blockSize * 2,
						y * squareSize + y * blockSize * 2 + blockSize / 2 + blockSize * 2,
						blockSize * 3, blockSize * 3, styles);

				i++;
			}
		}

		return svg;
	}
}
