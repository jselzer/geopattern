package com.selzerj.geopattern;

import com.selzerj.geopattern.model.FixedColorPreset;
import com.selzerj.geopattern.model.Pattern;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GeneratePatternMain {
	public static void main(String[] args) {


//		PatternGenerator patternGenerator = PatternGenerator.builder()
//				.seedString("test")
//				.build();
//		Pattern pattern = patternGenerator.generate();

		Pattern pattern = new PatternGenerator("seed string", new FixedColorPreset(new Color(255, 30, 90))).generate();


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
