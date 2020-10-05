package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Creates a window and invokes the generate(int, int, Color) method of the
 * given class.
 * 
 * @author Gysar Flegel, 
 * @email gysar.flegel@fh-bielefeld.de
 */
public class Gui extends JPanel {
	/** Window width in pixels */
	private static final int maxWidth = 1000;
	/** Window height in pixels */
	private static final int maxHeight = 1000;
	/** The number of rectangles to devide the window width by. */
	private int width;
	/** The number of rectangles to devide the window height by. */
	private int height;
	/** The object which's generate(int, int, Color) method is invoked. */
	private Object mtc;
	/** Saves the coordinates of the squares to draw. */
	private List<Integer> iSafe, jSafe;
	/** Saves the color of the squares to draw. */
	private List<Color> colorSafe;
	/** The time to wait between each drawing step. */
	private int waitMs = 0;

	/**
	 * generates a gui object.
	 * @param mtc the object with a generate(int, int, Color) method, which can be used by this gui.
	 * @param width the number of rectangles to devide the window width by.
	 * @param height the number of rectangles to devide the window height by.
	 */
	public Gui(Object mtc, int width, int height) {
		if (width > maxWidth || height > maxHeight || width < 1 || height < 1) {
			System.out.println("width und height müssen  >= 1 und <= " + maxWidth + " sein.");
			System.exit(0);
		}
		colorSafe = new LinkedList<Color>();
		iSafe = new LinkedList<Integer>();
		jSafe = new LinkedList<Integer>();
		this.height = height;
		this.width = width;
		this.mtc = mtc;
	}

	/**
	 * Sets the time to wait after drawing a rectangle.
	 * @param waitMs The time to wait after drawing a rectangle.
	 */
	public void setWaitMs(int waitMs) {
		this.waitMs = waitMs;
	}

	/**
	 * Starts the gui.
	 */
	public void start() {

		Method generate = null;
		try {
			Class[] cArg = new Class[3];
			cArg[0] = this.getClass();
			cArg[1] = int.class;
			cArg[2] = int.class;
			generate = mtc.getClass().getDeclaredMethod("generate", cArg);
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("Missing method \"void generate(Gui gui, int rows, int columns)\"");
			System.exit(0);
		}
		try {
			generate.invoke(mtc, this, width, height);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println("Something went wrong, please try again.");
			e.printStackTrace();
			System.exit(0);
		}
		Gui mainPanel = this;
		JFrame frame = new JFrame(mtc.getClass().getSimpleName() + " execution gui");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);

	}

	/**
	 * Paints a rectangle at the specified coordinates.
	 * @param x the x coordinate of the rectangle
	 * @param y the y coordinate of the rectangle
	 * @param color The awt.Color to paint the rectangle in
	 */
	public void rectangleAt(int i, int j, Color color) {
		this.iSafe.add(i);
		this.jSafe.add(j);
		this.colorSafe.add(color);
	}

	/**
	 * Paints rectangles from coordinate arrays.
	 * @param x the x coordinates array of the rectangles
	 * @param y the y coordinates array of the rectangles
	 * @param color The awt.Color to paint the rectangles in
	 */
	public void rectangleAt(int[] is, int[] js, Color color) {
		iSafe = new LinkedList<Integer>();
		for (int i : is) {
			iSafe.add(i);
		}
		jSafe = new LinkedList<Integer>();
		for (int j : js) {
			jSafe.add(j);
		}
		colorSafe.add(color);
	}

	/**
	* Returns the width.
	*/
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Paints the objects, defined by the generate(int, int, Color) method.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		int count = 0;
		while (count < iSafe.size()) {
			int i = iSafe.get(count);
			int j = jSafe.get(count);
			Color color = colorSafe.get(colorSafe.size() == 1 ? 0 : count);
			count++;

			if (i < 0 || i >= width) {
				System.out.println("i has to be  >= 0 and < " + width + ".");
				System.exit(0);
			}
			if (j < 0 || j >= height) {
				System.out.println("j has to be  >= 0 and < " + height + ".");
				System.exit(0);
			}

			paintRectangle(i, j, color);
			try {
				Thread.sleep(this.waitMs);
			} catch (InterruptedException e) {
			}
		}
		
		// Das hier ist nicht schön. Es wäre besser, wenn man g sagen könnte, dass es nicht repainted werden soll.
		// Am besten wäre es sagen zu können "Das hier ist fertig, es muss nicht mehr gemalt werden.".
		// Es ist nicht gut den Thread zu stoppen.
		Thread.currentThread().stop();
	}

	/**
	 * Paints a rectangle
	 * @param x the x coordinate of the rectangle
	 * @param y the y coordinate of the rectangle
	 * @param color The awt.Color to paint the rectangle in
	 */
	private void paintRectangle(int x, int y, Color color) {
		Graphics g = this.getGraphics();
		g.setColor(color);
		g.fillRect(x * this.getBounds().width / width, y * this.getBounds().height / height,
				this.getBounds().width / width, this.getBounds().height / height);
	}

	/**
	 *Returns the dimensions of the window
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(maxWidth, maxHeight);
	}

}
