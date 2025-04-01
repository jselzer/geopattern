package com.selzerj.geopattern.utils;

import lombok.experimental.UtilityClass;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.TIFFTranscoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@UtilityClass
public class SvgUtils {

	public static void main(String[] args) {
		SvgUtils.convertToBufferedImage(ResourceLoader.toString("fixtures/squares.svg"));
	}

	public byte[] convertToBufferedImage(String svg) {
		TIFFTranscoder transcoder = new TIFFTranscoder();

		try (InputStream in = new ByteArrayInputStream(svg.getBytes());
				ByteArrayOutputStream out = new ByteArrayOutputStream()) {
			TranscoderInput input = new TranscoderInput(in);
			TranscoderOutput output = new TranscoderOutput(out);
			transcoder.transcode(input, output);
			return out.toByteArray();
		} catch (TranscoderException e) {
			throw new RuntimeException("Failed to convert SVG to TIFF", e);
		} catch (IOException e) {
			throw new RuntimeException("IOException converting SVG to TIFF", e);
		}
	}


}
