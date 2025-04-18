package com.selzerj.geopattern.internal.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class MathUtils {

	// map a value from one range to another
	public double map(double value, double inputMin, double inputMax, double outputMin, double outputMax) {
		double inputRange = inputMax - inputMin;
		double outputRange = outputMax - outputMin;

		return ((value - inputMin) * outputRange / inputRange) + outputMin;
	}
}
