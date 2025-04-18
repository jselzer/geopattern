package com.selzerj.geopattern.model.svg;

import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;

public final class Svg {

	private int width;
	private int height;

	@Getter
	private String body;

	public Svg() {
		this.width = 100;
		this.height = 100;
		this.body = "";
	}

	public Svg(int width, int height) {
		this();

		this.width = width;
		this.height = height;
	}

	public String getImageString() {
		return getSvgHeader() + body + getSvgCloser();
	}

	public Svg addRect(double x, double y, double width, double height) {
		return this.addRect(x, y, Double.toString(width), Double.toString(height), Map.of());
	}

	public Svg addRect(double x, double y, double width, double height, Map<String, String> args) {
		return this.addRect(x, y, Double.toString(width), Double.toString(height), args);
	}

	public Svg addRect(double x, double y, String width, String height, Map<String, String> args) {
		body += String.format("<rect x=\"%f\" y=\"%f\" width=\"%s\" height=\"%s\" %s />",
				x, y, width, height, writeArgs(args));
		return this;
	}

	public Svg addCircle(double cx, double cy, double r, Map<String, String> args) {
		body += String.format("<circle cx=\"%s\" cy=\"%s\" r=\"%s\" %s />",
				cx, cy, r, writeArgs(args));
		return this;
	}

	public Svg addPath(String d, Map<String, String> args) {
		body += String.format("<path d=\"%s\" %s />", d, writeArgs(args));
		return this;
	}

	public Svg addPolyline(String points) {
		return this.addPolyline(points, Map.of());
	}

	public Svg addPolyline(String points, Map<String, String> args) {
		body += String.format("<polyline points=\"%s\" %s />", points, writeArgs(args));
		return this;
	}

	public Svg addGroup(String elements, Map<String, String> args) {
		body += String.format("<g %s>", writeArgs(args));
		body += elements;
		body += "</g>";
		return this;
	}



	public Svg addBody(String value) {
		body += value;
		return this;
	}


	private String writeArgs(Map<String, String> args) {
		return args.entrySet().stream()
				.map(e -> String.format("%s=\"%s\"", e.getKey(), e.getValue()))
				.collect(Collectors.joining(" "));
	}

	private String getSvgHeader() {
		return String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\">", width, height);
	}

	private String getSvgCloser() {
		return "</svg>";
	}
}
