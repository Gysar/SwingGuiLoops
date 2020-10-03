
import java.awt.Color;

public class MyTaskExample {

	private boolean equalApprox(double asked, double target, double threshhold) {
		if (asked - asked * threshhold <= target && asked + asked * threshhold >= target)
			return true;
		return false;
	}

	void generate(Gui gui, int rows, int columns) {
		gui.setWaitMs(0);
		midPointCircleDraw(gui, gui.getWidth() / 2, gui.getHeight() / 2, (gui.getHeight()+gui.getWidth())/4);
//		pyramide(gui);

	}
	
	void pyramide(Gui gui) {
		int n = gui.getWidth();
		for (int i = 0; i < (n / 2) + 1; i++) {
			for (int j = 0; j < 2 * i  +n%2 + n / 2 - i; j++) {
				if(!(j < n / 2 - i))gui.rectangleAt(j, i, Color.GREEN);
			}
		}
	}

	void midPointCircleDraw(Gui gui, int x_centre, int y_centre, int r) {
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
