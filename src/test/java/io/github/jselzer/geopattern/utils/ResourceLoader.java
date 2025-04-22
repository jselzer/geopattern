package io.github.jselzer.geopattern.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class ResourceLoader {

	public String toString(String resourcePath) {
		ClassLoader classLoader = ResourceLoader.class.getClassLoader();
		File file = new File(classLoader.getResource(resourcePath).getFile());

		try {
			return FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
