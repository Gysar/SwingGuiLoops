package tasks;

import java.awt.Color;

import gui.Gui;

public class MyTaskExample {

	/** The generate Method that is invoked in the Gui class.
	 *  It can call different methods for different drawing algorithms.
	 *  The drawing algorithm (for example pyramde(gui)) must call gui.rectangleAt,
	 *  thats why it needs an gui object as parameter.
	 * @param gui the class that handles the drawing, only needs the right coordinates.
	 * @param height the amount of squares in the height of the window
	 * @param width the amount of squares in the width of the window
	 */
	public void generate(Gui gui, int width, int height) {
		gui.setWaitMs(0);
		midPointCircleDraw(gui, gui.getWidth() / 2, gui.getHeight() / 2, (gui.getHeight()+gui.getWidth())/6);
//		pyramide(gui);
//		pyramideArray(gui);
//		checkerPattern(gui);
//		checkerPatternArray(gui);
	}

	private void pyramide(Gui gui) {
		int n = gui.getWidth();
		for (int i = 0; i < (n / 2) + 1; i++) {
			for (int j = 0; j < 2 * i  +n%2 + n / 2 - i; j++) {
				if(!(j < n / 2 - i))gui.rectangleAt(j, i, Color.GREEN);
			}
		}
	}

	/** Draws a checker pattern
	 * @param gui used to pass the coordinates with gui.rectangleAt
	 */
	private void checkerPattern(Gui gui){
		/** we need the height and width to draw a checker pattern */
		int n = gui.getWidth();
		int m = gui.getHeight();

		/** the first for-loop iterates through the whole width*/
		for(int i = 0; i< n; i++){
			/** the second for-loop iterates through the whole height*/
			for(int j = 0;j<m;j++){

				/** to get the pattern we add the current row we are looking at to the current
				 * column we are looking at. If the number is even ( (i+j)%2==0 is only true if it is even )
				 * we paint the square at this row and column black
				 * Example: the first rectangle has the coordinates 0,0
				 * (0 + 0) % 2 = 0: it will become black,
				 * the rectangles next to it (coordinates 0,1 and 1,0) have to stay white:
				 * (1 + 0) % 2 = 1: it will stay white
				 * (0 + 1) % 2 = 1: it will stay white
				 */
				if((i+j)%2==0) {
					/** gui.rectangleAt sends the coordnates and the color of the rectangle to the Gui class */
					gui.rectangleAt(i, j, Color.BLACK);
				}
			}
		}
	}

	private void checkerPatternArray(Gui gui){
		int n = gui.getWidth();
		int m = gui.getHeight();
		final int MAX_SIZE = (gui.getWidth()*gui.getHeight()+1)/2;
		int[] is = new int[MAX_SIZE];
		int[] js = new int[MAX_SIZE];
		Color[] colors = new Color[MAX_SIZE];
		int counter = 0;

		for(int i = 0; i< n; i++){
			for(int j = 0;j<m;j++){
				if((i+j)%2==0) {
					is[counter] = i;
					js[counter] = j;
					colors[counter] = Color.BLACK;
					counter++;
				}
			}
		}

		gui.rectangleAt(is,js,colors);
	}

	private void pyramideArray(Gui gui){
		int n = gui.getWidth();
		int counter = 0;
		final int MAX_SIZE = gui.getWidth()*gui.getHeight();

		/** Arrays must have fixed size: MAX_SIZE*/
		int[] is = new int[MAX_SIZE];
		int[] js = new int [MAX_SIZE];
		Color[] colors = new Color[MAX_SIZE];
		for (int i = 0; i < (n / 2) + 1; i++) {
			for (int j = 0; j < 2 * i  +n%2 + n / 2 - i; j++) {
				if(!(j < n / 2 - i)){
					js[counter] = j;
					is[counter] = i;

					/** if i is even paint blue */
					if(i%2==0){
						colors[counter] = Color.BLUE;
					}
					else{
						colors[counter] = Color.BLACK;
					}
					counter++;
				}
			}
		}

		/**
		 * MAX_SIZE is bigger than the amount of coordinates we need,
		 * set the unused Variables to -1 (default value 0 would draw at 0,0)
		 */
		for(int i = counter; i < MAX_SIZE; i++ ){
			js[i] = js[0];
			is[i] = is[0];
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
