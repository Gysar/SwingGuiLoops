
import java.awt.Color;

public class MyTaskExample {
	
	private boolean equalApprox(double asked, double target, double threshhold) {
		if(asked-asked*threshhold<=target&&asked+asked*threshhold>=target)return true;
		return false;
	}
	
	void generate(Gui gui, int rows, int columns) {

//			malt eine Pyramide
     		gui.setWaitMs(0);
			int n = gui.getWidth();
			for (int i = 0; i < (n / 2) + 1; i++) {
				for (int j = 0; j < 2 * i + 1 + n / 2 - i; j++) {
					if(!(j < n / 2 - i))gui.rectangleAt(j, i, Color.GREEN);
				}
			}

//		for(int i=0;i<gui.getWidth();i++) {
//			for(int j=0;j<gui.getHeight();j++) {
//				gui.rectangleAt(i, j, Color.RED);
//			}
//		}
			
//		int[] i={0,1,2,3,4};
//		gui.rectangleAt(i, i, Color.RED);
			
	}
}
