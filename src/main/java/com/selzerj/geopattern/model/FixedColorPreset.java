package com.selzerj.geopattern.model;

import lombok.Value;

import java.awt.Color;

@Value
public class FixedColorPreset implements ColorPreset {
	Color color;
}
