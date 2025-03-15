package com.selzerj.geopattern.composers;

import lombok.Data;
import lombok.experimental.Accessors;

import java.awt.Color;

@Data
@Accessors(chain = true)
public class PatternPreset {

	Color fillColorDark;
	Color fillColorLight;

	Color strokeColor;
	float strokeOpacity;

	float opacityMin;
	float opacityMax;
}
