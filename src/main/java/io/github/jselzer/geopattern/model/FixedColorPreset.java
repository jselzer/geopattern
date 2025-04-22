package io.github.jselzer.geopattern.model;

import lombok.Value;

import java.awt.Color;

/**
 * A color preset that will force the pattern to use a fixed color
 * (no changes based on the seed string).
 */
@Value
public class FixedColorPreset implements ColorPreset {
	Color color;
}
