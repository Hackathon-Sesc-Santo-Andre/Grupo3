/**
 * 
 */
package br.com.infosescsa.utils;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;

/**
 * @author saulo - Hackaton Sesc Santo Andre
 * @D02/06/2013
 */
public class MatrixToImageConfig {

	/**
	 * Encapsulates custom configuration used in methods of {@link MatrixToImageWriter}.
	 */

	  public static final int BLACK = 0xFF000000;
	  public static final int WHITE = 0xFFFFFFFF;
	  
	  private final int onColor;
	  private final int offColor;

	  /**
	   * Creates a default config with on color {@link #BLACK} and off color {@link #WHITE}, generating normal
	   * black-on-white barcodes.
	   */
	  public MatrixToImageConfig() {
	    this(BLACK, WHITE);
	  }

	  /**
	   * @param onColor pixel on color, specified as an ARGB value as an int
	   * @param offColor pixel off color, specified as an ARGB value as an int
	   */
	  public MatrixToImageConfig(int onColor, int offColor) {
	    this.onColor = onColor;
	    this.offColor = offColor;
	  }

	  public int getPixelOnColor() {
	    return onColor;
	  }

	  public int getPixelOffColor() {
	    return offColor;
	  }

	  int getBufferedImageColorModel() {
	    // Use faster BINARY if colors match default
	    return onColor == BLACK && offColor == WHITE ? BufferedImage.TYPE_BYTE_BINARY : BufferedImage.TYPE_INT_RGB;
	  }

}
