
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

public class Gui extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int maxWidth = 1000;
	private int width;
	private int height;
	Object mtc;
	List<Integer> iSafe, jSafe;
	List<Color> colorSafe;

	public Gui(Object mtc, int width, int height) {
		colorSafe= new ArrayList<Color>();
		iSafe = new ArrayList<Integer>();
		jSafe = new ArrayList<Integer>();
		this.height = height;
		this.width = width;
		this.mtc = mtc;
	}

	public Gui() {
		// TODO Auto-generated constructor stub
	}

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
		JFrame frame = new JFrame(mtc.getClass().getSimpleName()+" execution gui");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(mainPanel);
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		

	}

	public void rectangleAt(int i, int j, Color color) {
		this.iSafe.add(i);
		this.jSafe.add(j);
		this.colorSafe.add(color);
	}

	public void rectangleAt(int[] is, int[] js, Color color) {
		iSafe = new ArrayList<Integer>(is.length);
		for (int i : is) {
			iSafe.add(i);
		}
		jSafe = new ArrayList<Integer>(js.length);
		for (int j : js) {
			jSafe.add(j);
		}
		colorSafe.add(color);
	}

	  @Override
	   protected void paintComponent(Graphics g) {
	      super.paintComponent(g);
			while(!iSafe.isEmpty()) {
				paintRectangle(iSafe.remove(iSafe.size()-1), jSafe.remove(jSafe.size()-1), colorSafe.remove(colorSafe.size()-1));
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
				}
			}
			
			//Ohne das verschwindet alles sofort wieder, mit gibt's ne exeption
			try {
				g.wait();
			} catch (InterruptedException e) {
			}
	   }

	protected void paintRectangle(int i, int j, Color color) {
		Graphics g=this.getGraphics();
		super.paintComponents(g);
		g.setColor(color);
		g.fillRect(i*this.getBounds().width/width, j*this.getBounds().height/height, this.getBounds().width/width, this.getBounds().height/height);
	}

	@Override
	public Dimension getPreferredSize() {
		// so that our GUI is big enough
		return new Dimension(maxWidth, maxWidth);
	}

}
