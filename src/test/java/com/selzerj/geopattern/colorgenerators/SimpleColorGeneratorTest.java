package com.selzerj.geopattern.colorgenerators;

import com.selzerj.geopattern.color.HtmlColors;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleColorGeneratorTest {

	@Test
	public void testGenerate_shouldReturnCorrectColor() {
		Color result = new SimpleColorGenerator(HtmlColors.MAGENTA).generate();

		assertAll(
			() -> assertEquals(255, result.getRed()),
			() -> assertEquals(0, result.getGreen()),
			() -> assertEquals(255, result.getBlue())
		);
	}
}
