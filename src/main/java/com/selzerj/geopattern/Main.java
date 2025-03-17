package com.selzerj.geopattern;

import com.selzerj.geopattern.pattern.Pattern;
import com.selzerj.geopattern.pattern.PatternType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		PatternGenerator patternGenerator = PatternGenerator.builder()
				.seedString("Mastering Markdown")
				.desiredPatterns(List.of(PatternType.SINE_WAVES))
				.build();
		Pattern pattern = patternGenerator.generate();

		try (FileWriter writer = new FileWriter("test.svg")) {
			writer.write(pattern.toSvg());
			System.out.println("File created and content written successfully.");
		} catch (IOException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}
}