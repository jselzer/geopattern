package io.github.jselzer.geopattern.model;

import lombok.Value;

import java.awt.Color;

/**
 * A color preset that will modify the provided color based on the seed string.
 */
@Value
public class AdjustableColorPreset implements ColorPreset {
	Color color;
}
