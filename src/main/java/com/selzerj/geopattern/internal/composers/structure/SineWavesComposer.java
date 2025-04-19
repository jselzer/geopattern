package com.selzerj.geopattern.internal.composers.structure;

import com.selzerj.geopattern.internal.Seed;
import com.selzerj.geopattern.internal.composers.PatternPreset;
import com.selzerj.geopattern.internal.utils.ColorUtils;
import com.selzerj.geopattern.internal.utils.MathUtils;
import com.selzerj.geopattern.model.Svg;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public final class SineWavesComposer extends AbstractStructureComposer {

	private final double period;
	private final double amplitude;
	private final double waveWidth;

	public SineWavesComposer(Seed seed, PatternPreset patternPreset) {
		super(seed, patternPreset);

		this.period = Math.floor(MathUtils.map(this.seed.getInteger(0, 1), 0, 15, 100, 400));
		this.amplitude = Math.floor(MathUtils.map(this.seed.getInteger(1, 1), 0, 15, 30, 100));
		this.waveWidth = Math.floor(MathUtils.map(this.seed.getInteger(2, 1), 0, 15, 3, 30));

		this.width = this.period;
		this.height = this.waveWidth * 36;
	}

	@Override
	protected Svg generate() {
		Svg svg = new Svg();

		for (int i = 0; i < 36; i++) {
			int value = seed.getInteger(i, 1);
			double opacity = opacity(value);
			Color fillColor = fillColor(value);
			double xOffset = (period / 4.0) * 0.7;

			Map<String, String> styles = new HashMap<>();
			styles.put("fill", "none");
			styles.put("stroke", ColorUtils.toRgbString(fillColor));
			styles.put("style", "opacity: " + opacity + "; stroke-width: " + waveWidth + "px");

			StringBuilder dBuilder = new StringBuilder()
					.append("M0 ").append(amplitude)
					.append(" C ").append(xOffset).append(" 0, ")
					.append(period / 2 - xOffset).append(" 0, ")
					.append(period / 2).append(" ").append(amplitude)
					.append(" S ").append(period - xOffset).append(" ").append(amplitude * 2).append(", ")
					.append(period).append(" ").append(amplitude)
					.append(" S ").append(period * 1.5 - xOffset).append(" 0, ")
					.append(period * 1.5).append(" ").append(amplitude);

			styles.put("transform", "translate(-" + period / 4 + ", " + (waveWidth * i - amplitude * 1.5) + ")");
			svg.addPath(dBuilder.toString(), styles);
			styles.put("transform", "translate(-" + period / 4 + ", " + (waveWidth * i - amplitude * 1.5 + waveWidth * 36) + ")");
			svg.addPath(dBuilder.toString(), styles);
		}

		return svg;
	}
}
