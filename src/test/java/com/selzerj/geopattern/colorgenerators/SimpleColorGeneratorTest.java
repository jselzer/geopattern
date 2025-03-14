package com.selzerj.geopattern.colorgenerators;

import com.selzerj.geopattern.color.HtmlColors;
import com.selzerj.geopattern.color.RgbColor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleColorGeneratorTest {

	@Test
	public void testGenerate_shouldReturnCorrectColor() {
		RgbColor result = new SimpleColorGenerator(HtmlColors.MAGENTA).generate();

		assertAll(
			() -> assertEquals(255, result.getRed()),
			() -> assertEquals(0, result.getGreen()),
			() -> assertEquals(255, result.getBlue())
		);
	}
}
