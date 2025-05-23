package io.github.jselzer.geopattern;

import io.github.jselzer.geopattern.model.Pattern;
import io.github.jselzer.geopattern.model.PatternType;
import io.github.jselzer.geopattern.utils.ResourceLoader;
import io.github.jselzer.geopattern.utils.SvgUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatternGeneratorTest {

	private static final String SEED_STRING = "Mastering Markdown";

	@ParameterizedTest
	@MethodSource("providePatternsAndFixtures")
	void testPatternGenerator_shouldMatchFixture(PatternType patternType, String fixtureFilename) {
		PatternGenerator patternGenerator = PatternGenerator.builder()
				.seedString(SEED_STRING)
				.desiredPatterns(List.of(patternType))
				.build();
		Pattern pattern = patternGenerator.generate();
		byte[] expected = getSvgAsTiffBytes(fixtureFilename);
		byte[] actual = SvgUtils.convertToBufferedImage(pattern.toSvg());

		assertArrayEquals(expected, actual);
	}

	@Test
	void testBase64Encoding_shouldWork() {
		PatternGenerator patternGenerator = PatternGenerator.builder()
			.seedString(SEED_STRING)
			.desiredPatterns(List.of(PatternType.CHEVRONS))
			.build();
		Pattern pattern = patternGenerator.generate();
		assertTrue(pattern.toBase64().startsWith("PHN2ZyB4bWxuc"));
	}

	private static Stream<Arguments> providePatternsAndFixtures() {
		return Stream.of(
				Arguments.of(PatternType.CHEVRONS, "chevrons.svg"),
				Arguments.of(PatternType.CONCENTRIC_CIRCLES, "concentric_circles.svg"),
				Arguments.of(PatternType.DIAMONDS, "diamonds.svg"),
				Arguments.of(PatternType.HEXAGONS, "hexagons.svg"),
				Arguments.of(PatternType.MOSAIC_SQUARES, "mosaic_squares.svg"),
				Arguments.of(PatternType.NESTED_SQUARES, "nested_squares.svg"),
				Arguments.of(PatternType.OCTAGONS, "octagons.svg"),
				Arguments.of(PatternType.OVERLAPPING_CIRCLES, "overlapping_circles.svg"),
				Arguments.of(PatternType.OVERLAPPING_RINGS, "overlapping_rings.svg"),
				Arguments.of(PatternType.PLAID, "plaid.svg"),
				Arguments.of(PatternType.PLUS_SIGNS, "plus_signs.svg"),
				Arguments.of(PatternType.SQUARES, "squares.svg"),
				Arguments.of(PatternType.SINE_WAVES, "sine_waves.svg"),
				Arguments.of(PatternType.TESSELLATION, "tessellation.svg"),
				Arguments.of(PatternType.TRIANGLES, "triangles.svg"),
				Arguments.of(PatternType.XES, "xes.svg")
		);
	}

	private byte[] getSvgAsTiffBytes(String fixtureName) {
		String svgContent = ResourceLoader.toString("fixtures/" + fixtureName);
		return SvgUtils.convertToBufferedImage(svgContent);
	}
}
