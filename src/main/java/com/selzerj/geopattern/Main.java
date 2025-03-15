package com.selzerj.geopattern;

import java.io.FileWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		PatternGenerator patternGenerator = new PatternGenerator("testing!");
		Pattern pattern = patternGenerator.generate();

		try (FileWriter writer = new FileWriter("test.svg")) {
			writer.write(pattern.toSvg());
			System.out.println("File created and content written successfully.");
		} catch (IOException e) {
			System.err.println("An error occurred: " + e.getMessage());
		}
	}
}