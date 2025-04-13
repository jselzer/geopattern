package com.selzerj.geopattern.pattern;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PatternSelectorTest {

	private Seed mockSeed;

	@BeforeEach
	void init() {
		mockSeed = mock(Seed.class);
	}

	@ParameterizedTest
	@MethodSource("provideSeedAndExpectedPattern")
	public void testSelectPatternWithNoDesiredPatterns(int seedValue, PatternType expectedPattern) {
		when(mockSeed.getInteger(20, 1)).thenReturn(seedValue);
		PatternSelector selector = new PatternSelector(mockSeed, null);
		PatternType result = selector.selectPattern();
		assertEquals(expectedPattern, result);
	}


	private static Stream<Arguments> provideSeedAndExpectedPattern() {
		return Stream.of(
				Arguments.of(0, PatternType.CHEVRONS),
				Arguments.of(1, PatternType.CONCENTRIC_CIRCLES),
				Arguments.of(2, PatternType.DIAMONDS),
				Arguments.of(3, PatternType.HEXAGONS),
				Arguments.of(4, PatternType.MOSAIC_SQUARES),
				Arguments.of(5, PatternType.NESTED_SQUARES),
				Arguments.of(6, PatternType.OCTAGONS),
				Arguments.of(7, PatternType.OVERLAPPING_CIRCLES),
				Arguments.of(8, PatternType.OVERLAPPING_RINGS),
				Arguments.of(9, PatternType.PLAID),
				Arguments.of(10, PatternType.PLUS_SIGNS),
				Arguments.of(11, PatternType.SINE_WAVES),
				Arguments.of(12, PatternType.SQUARES),
				Arguments.of(13, PatternType.TESSELLATION),
				Arguments.of(14, PatternType.TRIANGLES),
				Arguments.of(15, PatternType.XES)
		);
	}

}
