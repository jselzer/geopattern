package com.selzerj.geopattern.internal.composers.structure;

import com.selzerj.geopattern.internal.Seed;
import com.selzerj.geopattern.internal.composers.PatternPreset;
import com.selzerj.geopattern.internal.utils.ColorUtils;
import com.selzerj.geopattern.model.Svg;

import java.awt.Color;
import java.util.Map;

public final class PlaidComposer extends AbstractStructureComposer {

	public PlaidComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);
	}

	@Override
	protected Svg generate() {
		Svg svg = new Svg();


        // horizontal stripes
		int i = 0;
		int localHeight = 0;
		for (int x = 0; x < 18; x++) {
			int space = seed.getInteger(i, 1);
			localHeight += space + 5;

			int val = seed.getInteger(i + 1, 1);
			double opacity = opacity(val);
			Color fill = fillColor(val);
			int stripeHeight = val + 5;

			svg.addRect(0, localHeight, "100%", Double.toString(stripeHeight),
					Map.of("opacity", Double.toString(opacity),
							"fill", ColorUtils.toRgbString(fill)));
			localHeight += stripeHeight;
			i += 2;
		}

		// vertical stripes
		i = 0;
		int localWidth = 0;
		for (int x = 0; x < 18; x++) {
			int space = seed.getInteger(i, 1);
			localWidth += space + 5;

			int val = seed.getInteger(i + 1, 1);
			double opacity = opacity(val);
			Color fill = fillColor(val);
			int stripeWidth = val + 5;

			svg.addRect(localWidth, 0, Double.toString(stripeWidth), "100%",
					Map.of("opacity", Double.toString(opacity),
							"fill", ColorUtils.toRgbString(fill)));
			localWidth += stripeWidth;
			i += 2;
		}

		this.height = localHeight;
		this.width = localWidth;

		return svg;
	}
}
