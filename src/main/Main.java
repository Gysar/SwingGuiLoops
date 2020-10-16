package main;
import gui.Gui;
import tasks.MyTaskExample;

/**
 * Example Main class
 *
 * @author Gysar Flegel, Anestis Lalidis Mateo
 * @email gysar.flegel@fh-bielefeld.de, anestis-pere.lalidis_mateo@fh-bielefeld.de
 */
public class Main {

	public static void main(String[] args) {
		MyTaskExample mtc = new MyTaskExample();
		Gui gui = new Gui(mtc, 10, 10);
		gui.resizeWindow(500,1000);
		gui.start(); // <- gui calls mtc.generate(this)
	}

}
