package com.selzerj.geopattern.model.svg;

import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;

public final class SvgImage {

	// FIXME, what data type to use for width/height?
	private int width;
	private int height;

	@Getter
	private String body;

	public SvgImage() {
		this.width = 100;
		this.height = 100;
		this.body = "";
	}

	public SvgImage(int width, int height) {
		this();

		this.width = width;
		this.height = height;
	}

	public String toString() {
		return getSvgHeader() + body + getSvgCloser();
	}

	public SvgImage addRect(double x, double y, double width, double height) {
		return this.addRect(x, y, Double.toString(width), Double.toString(height), Map.of());
	}

	public SvgImage addRect(double x, double y, double width, double height, Map<String, String> args) {
		return this.addRect(x, y, Double.toString(width), Double.toString(height), args);
	}

	public SvgImage addRect(double x, double y, String width, String height, Map<String, String> args) {
		body += String.format("<rect x=\"%f\" y=\"%f\" width=\"%s\" height=\"%s\" %s />",
				x, y, width, height, writeArgs(args));
		return this;
	}

	public SvgImage addCircle(double cx, double cy, double r, Map<String, String> args) {
		body += String.format("<circle cx=\"%s\" cy=\"%s\" r=\"%s\" %s />",
				cx, cy, r, writeArgs(args));
		return this;
	}

	public SvgImage addPath(String d, Map<String, String> args) {
		body += String.format("<path d=\"%s\" %s />", d, writeArgs(args));
		return this;
	}

	public SvgImage addPolyline(String points) {
		return this.addPolyline(points, Map.of());
	}

	public SvgImage addPolyline(String points, Map<String, String> args) {
		body += String.format("<polyline points=\"%s\" %s />", points, writeArgs(args));
		return this;
	}

	public SvgImage addGroup(String elements, Map<String, String> args) {
		body += String.format("<g %s>", writeArgs(args));
		body += elements;
		body += "</g>";
		return this;
	}



	public SvgImage addBody(String value) {
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
