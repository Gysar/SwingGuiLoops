
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
	private static final int maxHeight = 1000;
	private int width;
	private int height;
	private Object mtc;
	private List<Integer> iSafe, jSafe;
	private List<Color> colorSafe;
	private boolean drawn; 

	public Gui(Object mtc, int width, int height) {
		if(width>maxWidth||height>maxHeight||width<1||height<1) {
			System.out.println("width und height müssen  >= 1 und <= "+maxWidth+" sein.");
			System.exit(0);
		}
		colorSafe= new ArrayList<Color>();
		iSafe = new ArrayList<Integer>();
		jSafe = new ArrayList<Integer>();
		this.height = height;
		this.width = width;
		this.mtc = mtc;
		this.drawn = false;
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
			int count = 0;
			while(count<iSafe.size()) {
				int i =iSafe.get(count);
				int j =jSafe.get(count);
				Color color = colorSafe.get(colorSafe.size()==1?0:count);
				count++;
				
				if(i<0||i>=width) {
					System.out.println("i muss  >= 0 und < "+width+" sein.");
					System.exit(0);
				}
				if(j<0||j>=height) {
					System.out.println("j muss  >= 0 und < "+height+" sein.");
					System.exit(0);
				}
				
				paintRectangle(i, j, color);
				try {
					if(!drawn)Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
			drawn=true;
	   }

	protected void paintRectangle(int i, int j, Color color) {
		Graphics g=this.getGraphics();
		super.paintComponents(g);
		g.setColor(color);
		g.fillRect(i*this.getBounds().width/width, j*this.getBounds().height/height, this.getBounds().width/width, this.getBounds().height/height);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(maxWidth, maxHeight);
	}

}
