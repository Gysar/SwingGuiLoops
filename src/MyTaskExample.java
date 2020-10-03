
import java.awt.Color;

public class MyTaskExample {
	
	private boolean equalApprox(double asked, double target, double threshhold) {
		if(asked-asked*threshhold<=target&&asked+asked*threshhold>=target)return true;
		return false;
	}
	
	void generate(Gui gui, int rows, int columns) {

//			malt eine Pyramide
     		gui.setWaitMs(100);
			int n = gui.getWidth();
			for (int i = 0; i < (n / 2) + 1; i++) {
				for (int j = 0; j < 2 * i  +n%2 + n / 2 - i; j++) {
					if(!(j < n / 2 - i))gui.rectangleAt(j, i, Color.GREEN);
				}
			}

	}
}
