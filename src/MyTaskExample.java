
import java.awt.Color;

public class MyTaskExample {
	void generate(Gui gui, int rows, int columns) {

			//malt eine Pyramide
			String s = "";
			int n = 5 * 2 - 1;
			for (int i = 0; i < (n / 2) + 1; i++) {
				for (int j = 0; j < 2 * i + 1 + n / 2 - i; j++) {
					gui.rectangleAt(j, i, j < n / 2 - i ? Color.GRAY : Color.GREEN);
				}
			}
	}
}
