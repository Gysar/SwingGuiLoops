package main;
import gui.Gui;
import tasks.MyTaskExample;

public class Main {

	public static void main(String[] args) {
		MyTaskExample mtc = new MyTaskExample();
		Gui gui = new Gui(mtc, 1000, 1000);
		gui.start(); // <- gui ruft mtc.generate(this)
	}

}