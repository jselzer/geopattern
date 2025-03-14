package com.selzerj.geopattern.colorgenerators;

import com.selzerj.geopattern.Seed;
import com.selzerj.geopattern.color.HtmlColors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseColorGeneratorTest {

	private Seed mockSeed;

	@BeforeEach
	void init() {
		mockSeed = mock(Seed.class);
		when(mockSeed.getInteger(14, 3)).thenReturn(2616);
		when(mockSeed.getInteger(17, 1)).thenReturn(2);
	}

	@Test
	public void testGenerate_shouldReturnCorrectColorWhenSaturationDigitIsEven() {
		Color result = new BaseColorGenerator(HtmlColors.MAGENTA, mockSeed).generate();
		assertEquals(new Color(210, 255, 0), result);
	}

	@Test
	public void testGenerate_shouldReturnCorrectColorWhenSaturationDigitIsOdd() {
		when(mockSeed.getInteger(17, 1)).thenReturn(3);
		Color result = new BaseColorGenerator(HtmlColors.MAGENTA, mockSeed).generate();
		assertEquals(new Color(207, 251, 4), result);
	}
}
