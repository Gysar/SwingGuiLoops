
public class Main {

	public static void main(String[] args) {
		MyTaskExample mtc = new MyTaskExample();
		Gui gui = new Gui(mtc, 99, 99);
		gui.start(); // <- gui ruft mtc.generate(this)
	}

}
