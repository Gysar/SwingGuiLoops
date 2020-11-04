package tasks;

import java.awt.*;
import java.util.ArrayList;

import gui.Gui;

/**
 * Example class to show how to pass the right values to the Gui class to
 * draw certain patterns.
 *
 * @author Gysar Flegel, Anestis Lalidis Mateo
 * @email gysar.flegel@fh-bielefeld.de, anestis-pere.lalidis_mateo@fh-bielefeld.de
 */

public class MyTaskExample {

    /**
     * The generate Method that is invoked in the Gui class.
     * It can call different methods for different drawing algorithms.
     * The drawing algorithm (for example pyramde(gui)) must call gui.rectangleAt,
     * thats why it needs an gui object as parameter.
     *
     * @param gui    the class that handles the drawing, needs the right coordinates.
     * @param height the amount of squares in the height of the window
     * @param width  the amount of squares in the width of the window
     */
    public void generate(Gui gui, int width, int height) {
        gui.setWaitMs(1000);
//		midPointCircleDraw(gui, gui.getWidth() / 2, gui.getHeight() / 2, (gui.getHeight()+gui.getWidth())/6);
		//pyramidDifficult(gui);
		//pyramidDifficultArray(gui);
//		checkerPattern(gui);
        drawByLine(gui);
//		checkerPatternArray(gui);
//      row(gui,3);
//		column(gui,3);
//		diagonal(gui);
//		rectangle(gui);
//        pyramidEasy(gui);
    }

    private void drawByLine(Gui gui){

        var firstRow = new ArrayList<Point>();
        var firstColors = new ArrayList<Color>();

        firstRow.add(new Point(1,1));
        firstRow.add(new Point(2,1));
        firstRow.add(new Point(3,1));
        firstColors.add(new Color(10,10,10));
        firstColors.add(new Color(10,10,10));
        firstColors.add(new Color(10,10,10));


        var secondRow = new ArrayList<Point>();
        var secondColors = new ArrayList<Color>();

        secondRow.add(new Point(1,2));
        secondRow.add(new Point(2,2));
        secondRow.add(new Point(3,2));
        secondColors.add(new Color(10,10,10));
        secondColors.add(new Color(10,10,10));
        secondColors.add(new Color(10,10,10));


        var thirdRow = new ArrayList<Point>();
        var thirdColors = new ArrayList<Color>();

        thirdRow.add(new Point(1,3));
        thirdRow.add(new Point(2,3));
        thirdRow.add(new Point(3,3));
        thirdColors.add(new Color(10,10,10));
        thirdColors.add(new Color(10,10,10));
        thirdColors.add(new Color(10,10,10));


        var fourthRow = new ArrayList<Point>();
        var fourthColors = new ArrayList<Color>();

        fourthRow.add(new Point(1,4));
        fourthRow.add(new Point(2,4));
        fourthRow.add(new Point(3,4));
        fourthColors.add(new Color(10,10,10));
        fourthColors.add(new Color(10,10,10));
        fourthColors.add(new Color(10,10,10));

        gui.rectangleAt(firstRow.toArray(new Point[0]), firstColors.toArray(new Color[1]));
        gui.rectangleAt(secondRow.toArray(new Point[0]), secondColors.toArray(new Color[1]));
        gui.rectangleAt(thirdRow.toArray(new Point[0]), thirdColors.toArray(new Color[1]));
        gui.rectangleAt(fourthRow.toArray(new Point[0]), fourthColors.toArray(new Color[1]));
    }

    /**
     * Draws a rectangle at the border of the screen
     *
     * @param gui the class that handles the drawing, needs the right coordinates.
     */
    private void rectangle(Gui gui) {
        int n = gui.getWidth();
        int m = gui.getHeight();

        /**
         * where should the first (top left) point be?
         */
        int rectangleStartI = 3;
        int rectangleStartJ = 3;

        /**
         * where should the last (bottom right) point be?
         */
        int rectangleEndI = 2;
        int rectangleEndJ = 2;

        // n and m need to be in bounds (should not be bigger than width and height)
        //int n = 4;
        //int m = 4;

        for (int i = rectangleStartI; i < n-rectangleEndI; i++) {
            for (int j = rectangleStartJ; j < m-rectangleEndJ; j++) {
                gui.rectangleAt(i,j,Color.MAGENTA);
            }
        }
    }

    /**
     * Paints given row
     *
     * @param gui the class that handles the drawing, needs the right coordinates.
     * @param j   the number of the row. Has to be in bounds.
     */
    private void row(Gui gui, int j) {
        int n = gui.getWidth();

        // n and m need to be in bounds (should not be bigger than width and height)
        //int n = 4;
        for (int i = 0; i < n; i++) {
            gui.rectangleAt(i, j, Color.CYAN);
        }
    }

    /**
     * Paints given column
     *
     * @param gui the class that handles the drawing, needs the right coordinates.
     * @param i   the number of the column. Has to be in bounds.
     */
    private void column(Gui gui, int i) {
        int n = gui.getHeight();

        // n and m need to be in bounds (should not be bigger than width and height)
        //int n = 4;
        for (int j = 0; j < n; j++) {
            gui.rectangleAt(i, j, Color.YELLOW);
        }
    }

    /**
     * Draw a diagonal line
     *
     * @param gui the class that handles the drawing, needs the right coordinates.
     */
    private void diagonal(Gui gui) {
        int n = gui.getWidth();

        // n and m need to be in bounds (should not be bigger than width and height)
        //int n = 4;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) gui.rectangleAt(i, j, Color.PINK);
            }
        }
    }

	/**
	 * More readable method to draw a pyramid
	 * @param gui the class that handles the drawing, needs the right coordinates.
	 */
	private void pyramidEasy(Gui gui) {
        int n = gui.getWidth();

        // n and m need to be in bounds (should not be bigger than width and height)
        //int n = 4;

		/**
		 * CounterA is used to store the steps, counterB is manipulated in a loop
		 */
		int counterA= 0;
        int counterB = 0;

		/**
		 * if the width is odd we can draw a square at the top middle,
		 * the counter has to start with 1 (if first row has 1 pyramid element than the next one has three).
		 * if its even the first row stays empty and the counter starts with 0.
		 */
		if(n%2!=0){
			gui.rectangleAt(n/2,0,Color.RED);
			counterA = 1;
		}

        for (int j = 1; j < n; j++) {
			/**
			 * increase the counter by 2 (we want to draw two more squares than before)
			 */
			counterA += 2;
			counterB = counterA;

			/**
			 * Starting at the middle (n/2). Then go j steps back and paint
			 * as many squares in this row as counter allows us to.
			 */
            for (int i = (n / 2) - j; i < n; i++) {
            	if(counterB > 0 && counterB <= n){
            		gui.rectangleAt(i,j,Color.RED);
            		counterB--;
				}
            }
        }
    }


    /**
     * Draw a pyramid (difficult version)
     *
     * @param gui the class that handles the drawing, needs the right coordinates.
     */
    private void pyramidDifficult(Gui gui) {
        int n = gui.getWidth();

        // n and m need to be in bounds (should not be bigger than width and height)
        //int n = 4;

        for (int i = 0; i < (n / 2) + 1; i++) {
            for (int j =  n / 2 - i; j < 2 * i + n % 2 + n / 2 - i; j++) {
                gui.rectangleAt(j, i, Color.GREEN);
            }
        }
    }

    /**
     * Draws a checker pattern
     *
     * @param gui used to pass the coordinates with gui.rectangleAt
     */
    private void checkerPattern(Gui gui) {
        int n = gui.getWidth();
        int m = gui.getHeight();


        // n and m need to be in bounds (should not be bigger than width and height)
        //int n = 4;
        //int m = 4;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                /** to get the pattern we add the current row we are looking at to the current
                 * column we are looking at. If the number is even ( (i+j)%2==0 is only true if it is even )
                 * we paint the square at this row and column black
                 * Example: the first rectangle has the coordinates 0,0
                 * (0 + 0) % 2 = 0: it will become black,
                 * the rectangles next to it (coordinates 0,1 and 1,0) have to stay white:
                 * (1 + 0) % 2 = 1: it will stay white
                 * (0 + 1) % 2 = 1: it will stay white
                 */
                if ((i + j) % 2 == 0) {
                    gui.rectangleAt(j, i, Color.BLACK);
                }
            }
        }
    }

    /**
     * Draws a checker pattern using the getRectangle(int[],int[],Colors[] method)
     *
     * @param gui used to pass the coordinates with gui.rectangleAt
     */
    private void checkerPatternArray(Gui gui) {

        int n = gui.getWidth();
        int m = gui.getHeight();
        final int MAX_SIZE = (gui.getWidth() * gui.getHeight() + 1) / 2;
        int[] is = new int[MAX_SIZE];
        int[] js = new int[MAX_SIZE];

        Color[] colors = new Color[MAX_SIZE];
        int counter = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((i + j) % 2 == 0) {
                    is[counter] = i;
                    js[counter] = j;
                    colors[counter] = Color.BLACK;
                    counter++;
                }
            }
        }

        gui.rectangleAt(is, js, colors);
    }

    /**
     * Draw a pyramid (difficult version) using the getRectangle(int[],int[],Colors[] method)
     *
     * @param gui the class that handles the drawing, needs the right coordinates.
     */
    private void pyramidDifficultArray(Gui gui) {
        int n = gui.getWidth();
        int counter = 0;
        final int MAX_SIZE = gui.getWidth() * gui.getHeight();

        /** Arrays must have fixed size: MAX_SIZE*/
        int[] is = new int[MAX_SIZE];
        int[] js = new int[MAX_SIZE];
        Color[] colors = new Color[MAX_SIZE];
        for (int i = 0; i < (n / 2) + 1; i++) {
            for (int j = n / 2 - i; j < 2 * i + n % 2 + n / 2 - i; j++) {
                    js[counter] = j;
                    is[counter] = i;

                    /** if i is even paint blue */
                    if (i % 2 == 0) {
                        colors[counter] = Color.BLUE;
                    } else {
                        colors[counter] = Color.BLACK;
                    }
                    counter++;
            }
        }

        /**
         * MAX_SIZE is bigger than the amount of coordinates we need,
         * set the unused Variables to -1 (default value 0 would draw at 0,0)
         */
        for (int i = counter; i < MAX_SIZE; i++) {
            js[i] = js[0];
            is[i] = is[0];
            colors[i] = colors[0];
        }


        gui.rectangleAt(js, is, colors);
    }


    private void midPointCircleDraw(Gui gui, int x_centre, int y_centre, int r) {
        int x = r, y = 0;

        // Printing the initial point on the axes
        // after translation
        gui.rectangleAt(x + x_centre, y + y_centre, Color.RED);

        // When radius is zero only a single
        // point will be printed
        if (r > 0) {
            gui.rectangleAt(x + x_centre, -y + y_centre, Color.RED);
            gui.rectangleAt(y + x_centre, x + y_centre, Color.RED);
            gui.rectangleAt(-y + x_centre, -x + y_centre, Color.RED);
            gui.rectangleAt(-x + x_centre, -y + y_centre, Color.RED);
        }

        // Initialising the value of P
        int P = 1 - r;
        while (x > y) {
            y++;

            // Mid-point is inside or on the perimeter
            if (P <= 0)
                P = P + 2 * y + 1;

                // Mid-point is outside the perimeter
            else {
                x--;
                P = P + 2 * y - 2 * x + 1;
            }

            // All the perimeter points have already been printed
            if (x < y)
                break;

            // Printing the generated point and its reflection
            // in the other octants after translation
            gui.rectangleAt(x + x_centre, y + y_centre, Color.RED);
            gui.rectangleAt(-x + x_centre, y + y_centre, Color.RED);
            gui.rectangleAt(x + x_centre, -y + y_centre, Color.RED);
            gui.rectangleAt(-x + x_centre, -y + y_centre, Color.RED);

            // If the generated point is on the line x = y then
            // the perimeter points have already been printed
            if (x != y) {
                gui.rectangleAt(y + x_centre, x + y_centre, Color.RED);
                gui.rectangleAt(-y + x_centre, x + y_centre, Color.RED);
                gui.rectangleAt(y + x_centre, -x + y_centre, Color.RED);
                gui.rectangleAt(-y + x_centre, -x + y_centre, Color.RED);
            }
        }
    }
}
