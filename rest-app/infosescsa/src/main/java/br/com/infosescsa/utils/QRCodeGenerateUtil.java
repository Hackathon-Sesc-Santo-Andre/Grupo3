/**
 * 
 */
package br.com.infosescsa.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

//import com.google.zxing.client.j2se.MatrixToImageWriter

/**
 * @author saulo - Hackaton Sesc Santo Andre
 * @D02/06/2013
 */
public class QRCodeGenerateUtil {

	private static final MatrixToImageConfig DEFAULT_CONFIG = new MatrixToImageConfig();

	public static void main(String[] args) {
			generateQRCode("http://localhost:8080/infosescsa/services/eventos/80", null);
	}

	public static BufferedImage generateQRCodeImage(String conteudo) {
		Charset charset = Charset.forName("ISO-8859-1");
		CharsetEncoder encoder = charset.newEncoder();
		byte[] b = null;
		try {
			// Convert a string to ISO-8859-1 bytes in a ByteBuffer
			ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(conteudo));
			b = bbuf.array();
		} catch (CharacterCodingException e) {
			System.out.println(e.getMessage());
		}

		String data = null;
		try {
			data = new String(b, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}

		// get a byte matrix for the data
		BitMatrix matrix = null;
		int h = 100;
		int w = 100;
		com.google.zxing.Writer writer = new QRCodeWriter();
		try {
			matrix = writer.encode(data,
					com.google.zxing.BarcodeFormat.QR_CODE, w, h);
		} catch (com.google.zxing.WriterException e) {
			System.out.println(e.getMessage());
		}

		String filePath = "//Users/saulo/projetos/android/teste.png";
		File file = new File(filePath);
		try {
			// writeToFile(matrix, "PNG", file);
			// InputStream stream = new FileInputStream(file);
			// System.out.println("printing to " + file.getAbsolutePath());

			return toBufferedImage(matrix, DEFAULT_CONFIG);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void generateQRCode(String conteudo,
			String filePath) {
		Charset charset = Charset.forName("ISO-8859-1");
		CharsetEncoder encoder = charset.newEncoder();
		byte[] b = null;
		try {
			// Convert a string to ISO-8859-1 bytes in a ByteBuffer
			ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(conteudo));
			b = bbuf.array();
		} catch (CharacterCodingException e) {
			System.out.println(e.getMessage());
		}

		String data = null;
		try {
			data = new String(b, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}

		// get a byte matrix for the data
		BitMatrix matrix = null;
		int h = 100;
		int w = 100;
		com.google.zxing.Writer writer = new QRCodeWriter();
		try {
			matrix = writer.encode(data,
					com.google.zxing.BarcodeFormat.QR_CODE, w, h);
		} catch (com.google.zxing.WriterException e) {
			System.out.println(e.getMessage());
		}

		File file = new File(filePath);
		try {
			 writeToFile(matrix, "PNG", file);
//			 InputStream stream = new FileInputStream(file);
//			 ByteArrayOutputStream os = new ByteArrayOutputStream(); 
//			 
//			 ImageIO.write(toBufferedImage(matrix, DEFAULT_CONFIG),"png", os)
			 ;
			 System.out.println("printing to " + file.getAbsolutePath());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * As {@link #toBufferedImage(BitMatrix)}, but allows customization of the
	 * output.
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix,
			MatrixToImageConfig config) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		int onColor = config.getPixelOnColor();
		int offColor = config.getPixelOffColor();
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? onColor : offColor);
			}
		}
		return image;
	}

	/**
	 * Writes a {@link BitMatrix} to a file.
	 * 
	 * @see #toBufferedImage(BitMatrix)
	 */
	public static void writeToFile(BitMatrix matrix, String format, File file)
			throws IOException {
		writeToFile(matrix, format, file, DEFAULT_CONFIG);
	}

	/**
	 * As {@link #writeToFile(BitMatrix, String, File)}, but allows
	 * customization of the output.
	 */
	public static void writeToFile(BitMatrix matrix, String format, File file,
			MatrixToImageConfig config) throws IOException {
		BufferedImage image = toBufferedImage(matrix, config);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format "
					+ format + " to " + file);
		}
	}

}
