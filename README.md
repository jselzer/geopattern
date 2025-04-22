# GeoPattern


[![Status](https://img.shields.io/badge/status-active-success.svg)]()
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](/LICENSE.txt)

---

Generate beautiful tiling SVG patterns from a string.
This is a Java port of the original [Ruby library](https://github.com/jasonlong/geo_pattern) by Jason Long.

## 📝 Table of Contents
- [About](#about)
- [Getting Started](#getting_started)
- [Usage](#usage)
- [Built Using](#built_using)
- [Acknowledgments](#acknowledgement)

## 🧐 About <a name = "about"></a>
GeoPattern is a library for generating tiled SVG images based on a seed string.  The string is converted to a SHA and a pattern and colour are determined using the values in the hash.

All of the patterns in the original GeoPattern library are implemented and tested to confirm that they exactly match the original.  For an example of what GeoPatterns look like, see the [live preview](http://btmills.github.io/geopattern/geopattern.html) page implemented by Brandon Mills for the Javascript port.

## 🏁 Getting Started <a name = "getting_started"></a>

You can download the latest release JAR from the [Github Releases](https://github.com/jselzer/geopattern/releases) page.

Alternatively, you can pull it from the central Maven repositories:

```xml
<dependency>
  <groupId>io.github.jselzer</groupId>
  <artifactId>geopattern</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Building
Building requires a Java JDK (>= 11) and Gradle.  Intellij IDEA is recommended.

## 🎈 Usage <a name="usage"></a>

Make a new pattern:
```java
Pattern pattern = new PatternGenerator("seed string").generate();
```

To specify a base background color (with a hue and saturation that adjusts depending on the string):
```java
Pattern pattern = new PatternGenerator("seed string", new AdjustableColorPreset(Color.MAGENTA)).generate();
```

To use a specific background color (w/o any hue or saturation adjustments):
```java
Pattern pattern = new PatternGenerator("seed string", new FixedColorPreset(Color.MAGENTA)).generate();
```

To use a specific pattern generator:
```java
Pattern pattern = new PatternGenerator("seed string", List.of(PatternType.SINE_WAVES)).generate();
```

To use a subset of the available patterns:
```java
Pattern pattern = new PatternGenerator("seed string", List.of(PatternType.SINE_WAVES, PatternType.CHEVRONS)).generate();
```

To construct a pattern using a Builder:
```java
PatternGenerator patternGenerator = PatternGenerator.builder()
				.seedString("seed string")
				.colorPreset(new FixedColorPreset(new Color(255, 40, 96)))
				.desiredPatterns(List.of(PatternType.CHEVRONS))
				.build();
```

To get an SVG String:
```java
String svg = pattern.toSvg();
```

## ⛏️ Built Using <a name = "built_using"></a>
- [Intellij IDEA](https://www.jetbrains.com/idea/)
- [Apache Batik](https://xmlgraphics.apache.org/batik/) - Unit test validation

## 🎉 Acknowledgements <a name = "acknowledgement"></a>
- [Jason Long](https://github.com/jasonlong) and all contributors to the [original Ruby GeoPattern library](https://github.com/jasonlong/geo_pattern)
- The excellent [Javascript port](https://github.com/btmills/geopattern) of GeoPattern by [Brandon Mills](https://github.com/btmills)
