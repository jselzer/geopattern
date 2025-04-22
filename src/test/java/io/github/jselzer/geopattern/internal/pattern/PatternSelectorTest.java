package io.github.jselzer.geopattern.internal.pattern;

import io.github.jselzer.geopattern.internal.Seed;
import io.github.jselzer.geopattern.model.PatternType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.IntStream;
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

	@ParameterizedTest
	@MethodSource("provideSeeds")
	public void testSelectPatternWithLimitedDesiredPatterns(int seedValue) {
		when(mockSeed.getInteger(20, 1)).thenReturn(seedValue);
		List<PatternType> availablePatterns = List.of(PatternType.CHEVRONS, PatternType.CONCENTRIC_CIRCLES, PatternType.OCTAGONS);
		PatternSelector selector = new PatternSelector(mockSeed, availablePatterns);
		PatternType result = selector.selectPattern();
		assertEquals(availablePatterns.get(seedValue % availablePatterns.size()), result);
	}

	@ParameterizedTest
	@MethodSource("provideSeeds")
	public void testSelectPatternWithOnePattern_shouldReturnPattern(int seedValue) {
		when(mockSeed.getInteger(20, 1)).thenReturn(seedValue);
		PatternSelector selector = new PatternSelector(mockSeed, List.of(PatternType.DIAMONDS));
		PatternType result = selector.selectPattern();
		assertEquals(PatternType.DIAMONDS, result);
	}



	private static Stream<Arguments> provideSeedAndExpectedPattern() {
		return IntStream.range(0, 16)
				.mapToObj(i -> Arguments.of(i, PatternType.values()[i]));
	}

	private static Stream<Arguments> provideSeeds() {
		return IntStream.range(0, 16)
				.mapToObj(Arguments::of);
	}

}
