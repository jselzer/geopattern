package com.selzerj.geopattern.svg;

import java.util.Map;

public class SvgImage {

	private int width;
	private int height;
	private String svgContent;

	public String toString() {
		return getSvgHeader() + svgContent + getSvgCloser();
	}

	public void addRect(int x, int y, int width, int height, Map<String, String> args) {
		svgContent += String.format("<rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" %s />",
				x, y, width, height, writeArgs(args));
	}

	public void addCircle(int cx, int cy, int r, Map<String, String> args) {
		svgContent += String.format("<circle cx=\"%d\" cy=\"%d\" r=\"%d\" %s />",
				cx, cy, r, writeArgs(args));
	}

	public void addPath(String d, Map<String, String> args) {
		svgContent += String.format("<path d=\"%s\" %s />", d, writeArgs(args));
	}


	private String writeArgs(Map<String, String> args) {
		return "";
	}

	private String getSvgHeader() {
		return String.format("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"%d\" height=\"%d\">", width, height);
	}

	private String getSvgCloser() {
		return "</svg>";
	}
}
