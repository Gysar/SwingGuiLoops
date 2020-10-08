package tasks;

import java.awt.Color;

import gui.Gui;

public class MyTaskExample {

	//fuer rectangleAt(int[], int[], Color), sinnvoller Wert?
	private static final int MAX_SIZE = 1920;

	//wozu?
	private boolean equalApprox(double asked, double target, double threshhold) {
		if (asked - asked * threshhold <= target && asked + asked * threshhold >= target)
			return true;
		return false;
	}

	public void generate(Gui gui, int rows, int columns) {
		gui.setWaitMs(0);
//		midPointCircleDraw(gui, gui.getWidth() / 2, gui.getHeight() / 2, (gui.getHeight()+gui.getWidth())/6);
//		pyramide(gui);
//		pyramideArray(gui);
//		checkerPattern(gui);
		checkerPatternArray(gui);
	}

	private void pyramide(Gui gui) {
		int n = gui.getWidth();
		for (int i = 0; i < (n / 2) + 1; i++) {
			for (int j = 0; j < 2 * i  +n%2 + n / 2 - i; j++) {
				if(!(j < n / 2 - i))gui.rectangleAt(j, i, Color.GREEN);
			}
		}
	}

	private void checkerPattern(Gui gui){
		int n = gui.getWidth();
		int m = gui.getHeight();
		for(int i = 0; i< n; i++){
			for(int j = 0;j<m;j++){
				if((i+j)%2==0) {
					gui.rectangleAt(i, j, Color.BLACK);
				}
			}
		}
	}

	private void checkerPatternArray(Gui gui){
		int n = gui.getWidth();
		int m = gui.getHeight();
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

		/** set the unused Variables to -1 (default value 0 would draw at 0,0)*/
		for(int i = counter; i < MAX_SIZE; i++ ){
			js[i] = -1;
			is[i] = -1;
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
			gui.rectangleAt(-y + x_centre, x + y_centre, Color.RED);
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
