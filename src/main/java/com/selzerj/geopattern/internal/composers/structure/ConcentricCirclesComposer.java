package com.selzerj.geopattern.internal.composers.structure;

import com.selzerj.geopattern.internal.composers.PatternPreset;
import com.selzerj.geopattern.internal.pattern.Seed;
import com.selzerj.geopattern.internal.utils.ColorUtils;
import com.selzerj.geopattern.internal.utils.MathUtils;
import com.selzerj.geopattern.model.svg.SvgImage;

import java.awt.Color;
import java.util.Map;

public final class ConcentricCirclesComposer extends AbstractStructureComposer {

	private final double ringSize;
	private final double strokeWidth;

	public ConcentricCirclesComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		double scale = seed.getInteger(0, 1);
		this.ringSize = MathUtils.map(scale, 0, 15, 10, 60);
		this.strokeWidth = ringSize / 5;

		this.width = this.height = (ringSize + strokeWidth) * 6;
	}

	@Override
	protected SvgImage generate() {
		SvgImage svgImage = new SvgImage();
		int i = 0;

		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 6; x++) {
				drawBigCircle(svgImage, i, x, y);
				drawSmallCircle(svgImage, i, x, y);
				i++;
			}
		}

		return svgImage;
	}

	private void drawSmallCircle(SvgImage svgImage, int i, double x, double y) {
		int val = seed.getInteger(39 - i, 1);
		double opacity = opacity(val);
		Color fill = fillColor(val);

		svgImage.addCircle(x * ringSize + x * strokeWidth + (ringSize + strokeWidth) / 2.0,
				y * ringSize + y * strokeWidth + (ringSize + strokeWidth) / 2.0,
				ringSize / 4,
				Map.of("fill", ColorUtils.toRgbString(fill),
						"fill-opacity", Double.toString(opacity)));
	}

	private void drawBigCircle(SvgImage svgImage, int i, double x, double y) {
		int val = seed.getInteger(i, 1);
		double opacity = opacity(val);
		Color fill = fillColor(val);

		Map<String, String> styles = Map.of(
				"fill", "none",
				"stroke", ColorUtils.toRgbString(fill),
				"style", String.format("opacity: %s; stroke-width: %spx", opacity, strokeWidth)
		);

		svgImage.addCircle(x * ringSize + x * strokeWidth + (ringSize + strokeWidth) / 2.0,
				y * ringSize + y * strokeWidth + (ringSize + strokeWidth) / 2.0,
				ringSize / 2,
				styles);
	}
}
