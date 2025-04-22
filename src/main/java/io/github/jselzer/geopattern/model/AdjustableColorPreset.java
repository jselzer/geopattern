package io.github.jselzer.geopattern.model;

import lombok.Value;

import java.awt.Color;

@Value
public class AdjustableColorPreset implements ColorPreset {
	Color color;
}
