package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Creates a window and invokes the generate(int, int, Color) method of the
 * given class.
 *
 * @author Gysar Flegel, Anestis Lalidis Mateo
 * @email gysar.flegel@fh-bielefeld.de, anestis-pere.lalidis_mateo@fh-bielefeld.de
 */
public class Gui extends JPanel implements ActionListener {

    /**
     * used to allow resize only if start has not been called
     */
    private boolean allowResize = true;

    /**
     * only repaint if certain condition occures
     */
    private boolean doRepaint = false;

    /**
     * used to store the counter outside of a function
     */
    private int countGlobal = 0;

    Timer timer = null;
    

    /**
     * Window width in pixels
     */
    private int maxWidth = 500;
    /**
     * Window height in pixels
     */
    private int maxHeight = 500;
    /**
     * The number of rectangles to devide the window width by.
     */
    private int width;
    /**
     * The number of rectangles to devide the window height by.
     */
    private int height;
    /**
     * The object which's generate(int, int, Color) method is invoked.
     */
    private Object mtc;
    /**
     * Saves the coordinates of the squares to draw.
     */
    private List<Integer> iSafe, jSafe;
    /**
     * Saves the color of the squares to draw.
     */
    private List<Color> colorSafe;
    /**
     * The time to wait between each drawing step.
     */
    private int waitMs = 0;

    /**
     * generates a gui object.
     *
     * @param mtc    the object with a generate(int, int, Color) method, which can be used by this gui.
     * @param width  the number of rectangles to devide the window width by.
     * @param height the number of rectangles to devide the window height by.
     */
    public Gui(Object mtc, int width, int height) {
        if (width > 100 || height > 100 || width < 5 || height < 5) {
            System.out.println("width and height must be >= 5 and <= 100");
            System.exit(0);
        }
        if(width > maxWidth || height > maxHeight){
            System.out.println("width and height must be <= maxWidth/maxHeight");
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
     *
     * @param waitMs The time to wait after drawing a rectangle.
     */
    public void setWaitMs(int waitMs) {
        if (waitMs < 100 || waitMs > 2000) {
            System.out.println("waitMs must be >= 100 and <= 2000");
            System.exit(0);
        }
        this.waitMs = waitMs;
    }

    /**
     * Starts the gui.
     */
    public void start() {
        allowResize = false;
        Method generate = null;
        try {
            Class[] cArg = new Class[3];
            cArg[0] = this.getClass();
            cArg[1] = int.class;
            cArg[2] = int.class;
            generate = mtc.getClass().getDeclaredMethod("generate", cArg);
        } catch (NoSuchMethodException e) {
            System.out.println("Missing method \"void generate(Gui gui, int rows, int columns)\"");
            System.exit(0);
        }
        try {
            generate.invoke(mtc, this, width, height);
        } catch (IllegalAccessException e) {
            System.out.println("The generate(Gui, int, int) is not public.");
            System.exit(0);
        } catch (IllegalArgumentException e) {
            System.out.println("The generate(Gui, int, int) didn't get the right types of arguments.");
            System.exit(0);
        } catch (InvocationTargetException e) {
            System.out.println("Something went wrong in generate(Gui, int, int). Please make sure, it doesn't throw any exceptions");
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
        timer = new Timer(waitMs, this);
        timer.setInitialDelay(1000);
        timer.start();

    }

    /**
     * Paints a rectangle at the specified coordinates.
     *
     * @param i     the x coordinate of the rectangle
     * @param j     the y coordinate of the rectangle
     * @param color The awt.Color to paint the rectangle in
     */
    public void rectangleAt(int i, int j, Color color) {
        this.iSafe.add(i);
        this.jSafe.add(j);
        this.colorSafe.add(color);
    }

    /**
     * Paints rectangles from coordinate arrays.
     *
     * @param is     the x coordinates array of the rectangles
     * @param js     the y coordinates array of the rectangles
     * @param colors The awt.Colors to paint the rectangles in
     */
    public void rectangleAt(int[] is, int[] js, Color[] colors) {
        iSafe = new LinkedList<Integer>();
        for (int i : is) {
            iSafe.add(i);
        }
        jSafe = new LinkedList<Integer>();
        for (int j : js) {
            jSafe.add(j);
        }
        for (Color color : colors) {
            colorSafe.add(color);
        }
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
    	super.paintComponent(g);
        int count = 0;

        while (count < countGlobal) {
            int i = iSafe.get(count);
            int j = jSafe.get(count);
            Color color = colorSafe.get(colorSafe.size() == 1 ? 0 : count);
            count++;

            if (!(i < 0 || i >= width) && !(j < 0 || j >= height)) {
                paintRectangle(i, j, color);
            } else {
                System.out.println("Dimensions i=" + i + " and j=" + j + " are not in bounds.");
                System.exit(0);
            }

        }
        if (countGlobal < Math.min(iSafe.size(), jSafe.size())) {
            if (doRepaint) {
                countGlobal++;
                doRepaint = false;
            }
        }
    }

    /**
     * Paints a rectangle
     *
     * @param x     the x coordinate of the rectangle
     * @param y     the y coordinate of the rectangle
     * @param color The awt.Color to paint the rectangle in
     */
    private void paintRectangle(int x, int y, Color color) {
        Graphics g = this.getGraphics();
        g.setColor(color);
        g.fillRect(x * this.getBounds().width / width, y * this.getBounds().height / height,
                (this.getBounds().width+width) / width, (this.getBounds().height+height) / height);
    }

    /**
     * Returns the dimensions of the window
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(maxWidth, maxHeight);
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource() == timer) {
            doRepaint = true;
//            repaint();
            update(this.getGraphics());
        }

    }

    //Kann immer aufgerufen werden ist aber nie ein problem!?
    /**
     * used to change the size of the window
     * @param newMaxHeight new window height
     * @param newMaxWidth  new window width
     */
    public void resizeWindow(int newMaxHeight, int newMaxWidth) {
        if(newMaxHeight < 50 || newMaxWidth < 50 || newMaxHeight > 1000 || newMaxWidth > 1000){
            System.out.println("newMaxHeight and newMaxWidth must be in the interval (50,1000)");
            System.exit(0);
        }
        if (allowResize == true) {
            maxHeight = newMaxHeight;
            maxWidth = newMaxWidth;
            getPreferredSize();
        } else{
            System.out.println("window should only be resized before calling gui.start()");
            System.exit(0);
        }
    }
}
