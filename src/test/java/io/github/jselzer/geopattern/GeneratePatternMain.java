package io.github.jselzer.geopattern;

import io.github.jselzer.geopattern.model.FixedColorPreset;
import io.github.jselzer.geopattern.model.Pattern;
import io.github.jselzer.geopattern.model.PatternType;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneratePatternMain {
	public static void main(String[] args) {
		PatternGenerator patternGenerator = PatternGenerator.builder()
				.seedString("test")
				.colorPreset(new FixedColorPreset(new Color(150, 40, 96)))
				.desiredPatterns(List.of(PatternType.CHEVRONS))
				.build();
		Pattern pattern = patternGenerator.generate();

		File file = new File("temp/temp.svg");
		file.getParentFile().mkdirs();
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(pattern.toSvg());
			System.out.println("File created and content written successfully.");
		} catch (IOException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}
}
