package com.selzerj.geopattern.svg;

import lombok.Getter;

import java.util.Map;
import java.util.stream.Collectors;

public class SvgImage {

	private int width;
	private int height;

	@Getter
	private String body;

	public SvgImage() {
		this.width = 100;
		this.height = 100;
		this.body = "";
	}

	public String toString() {
		return getSvgHeader() + body + getSvgCloser();
	}

	public void addRect(int x, int y, String width, String height, Map<String, String> args) {
		body += String.format("<rect x=\"%d\" y=\"%d\" width=\"%s\" height=\"%s\" %s />",
				x, y, width, height, writeArgs(args));
	}

	public void addCircle(int cx, int cy, int r, Map<String, String> args) {
		body += String.format("<circle cx=\"%d\" cy=\"%d\" r=\"%d\" %s />",
				cx, cy, r, writeArgs(args));
	}

	public void addPath(String d, Map<String, String> args) {
		body += String.format("<path d=\"%s\" %s />", d, writeArgs(args));
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
