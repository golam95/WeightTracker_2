package com.tracker.java.main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.tracker.java.view.MainFrame;

public class Main {
	public static void main(String[] arg) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			MainFrame.createAndShowGUI();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}

}
