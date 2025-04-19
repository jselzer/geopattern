package com.selzerj.geopattern.internal.composers.background;

import com.selzerj.geopattern.internal.Seed;
import com.selzerj.geopattern.model.AdjustableColorPreset;
import com.selzerj.geopattern.model.ColorPreset;
import com.selzerj.geopattern.model.FixedColorPreset;
import com.selzerj.geopattern.model.Pattern;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SolidBackgroundComposerTest {

	private Seed mockSeed;

	@BeforeEach
	void init() {
		mockSeed = mock(Seed.class);
		when(mockSeed.getInteger(14, 3)).thenReturn(2616);
		when(mockSeed.getInteger(17, 1)).thenReturn(3);
	}

	@Test
	public void testComposeWithFixedColor_shouldReturnCorrectColor() {
		ColorPreset preset = new FixedColorPreset(Color.PINK);
		SolidBackgroundComposer composer = new SolidBackgroundComposer(mockSeed, preset);

		Pattern result = new Pattern();
		composer.compose(result);

		String svgBackground = result.getBackground().getBody();
		String expected = "<rect x=\"0.000000\" y=\"0.000000\" width=\"100%\" height=\"100%\" fill=\"rgb(255,175,175)\" />";
		assertEquals(expected, svgBackground);
	}

	@Test
	public void testComposeWithAdjustableColor_shouldReturnCorrectColor() {
		ColorPreset preset = new AdjustableColorPreset(Color.MAGENTA);
		SolidBackgroundComposer composer = new SolidBackgroundComposer(mockSeed, preset);

		Pattern result = new Pattern();
		composer.compose(result);

		String svgBackground = result.getBackground().getBody();
		String expected = "<rect x=\"0.000000\" y=\"0.000000\" width=\"100%\" height=\"100%\" fill=\"rgb(207,251,4)\" />";
		assertEquals(expected, svgBackground);
	}
}
