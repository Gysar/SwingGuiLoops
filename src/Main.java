
public class Main {

	public static void main(String[] args) {
		MyTaskExample mtc = new MyTaskExample();
		Gui gui = new Gui(mtc, 255, 255);
		gui.start(); // <- gui ruft mtc.generate(this)
	}

}
