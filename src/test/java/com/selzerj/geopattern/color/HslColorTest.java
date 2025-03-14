package com.selzerj.geopattern.color;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HslColorTest {
	// TODO, does the fact that this value needs to be so high indicate a bug?
	private static final double COMPARISON_EPSILON = 0.05;

	@Test
	public void testFromRgb_shouldReturnCorrectHslColor() {
		assertAll(
				() -> assertFuzzyEquals(new HslColor(0, 0, 0), HslColor.fromRgbColor(RgbColor.fromHtmlColor(HtmlColors.BLACK))),
				() -> assertFuzzyEquals(new HslColor(60.0, 100.0, 50.0), HslColor.fromRgbColor(RgbColor.fromHtmlColor(HtmlColors.YELLOW))),
				() -> assertFuzzyEquals(new HslColor(180.0, 100.0, 50.0), HslColor.fromRgbColor(RgbColor.fromHtmlColor(HtmlColors.CYAN))),
				() -> assertFuzzyEquals(new HslColor(300.0, 100.0, 50.0), HslColor.fromRgbColor(RgbColor.fromHtmlColor(HtmlColors.MAGENTA))),
				() -> assertFuzzyEquals(new HslColor(0.0, 100.0, 50.0), HslColor.fromRgbColor(RgbColor.fromHtmlColor(HtmlColors.RED))),
				() -> assertFuzzyEquals(new HslColor(120.0, 100.0, 50.0), HslColor.fromRgbColor(RgbColor.fromHtmlColor(HtmlColors.LIME))),
				() -> assertFuzzyEquals(new HslColor(240.0, 100.0, 50.0), HslColor.fromRgbColor(RgbColor.fromHtmlColor(HtmlColors.BLUE))),
				() -> assertFuzzyEquals(new HslColor(300.0, 100.0, 25.1), HslColor.fromRgbColor(RgbColor.fromHtmlColor(HtmlColors.PURPLE))),
				() -> assertFuzzyEquals(new HslColor(0.0, 59.42, 40.59), HslColor.fromRgbColor(RgbColor.fromHtmlColor(HtmlColors.BROWN))),
				() -> assertFuzzyEquals(new HslColor(317.5, 100.0, 68.43), HslColor.fromRgbColor(RgbColor.fromHtmlColor(HtmlColors.CARNATION))),
				() -> assertFuzzyEquals(new HslColor(0.0, 100.0, 27.64), HslColor.fromRgbColor(RgbColor.fromHtmlColor(HtmlColors.CAYENNE)))
		);
	}

	private void assertFuzzyEquals(HslColor expected, HslColor actual) {
		assertAll(
				() -> assertEquals(expected.getHue(), actual.getHue(), COMPARISON_EPSILON),
				() -> assertEquals(expected.getSaturation(), actual.getSaturation(), COMPARISON_EPSILON),
				() -> assertEquals(expected.getLightness(), actual.getLightness(), COMPARISON_EPSILON)
		);
	}
}