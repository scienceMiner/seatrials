package com.scienceminer.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ColourChooserPanel {

	public static void main(String args[]) 
	{
		Runnable runner = new Runnable() 
		{

			public void run() 
			{
				JFrame frame = new JFrame("ERC Colour Chooser Sample");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				Container contentPane = frame.getContentPane();

				final JLabel label = new JLabel
						("scienceMiner@gitHub", JLabel.CENTER);
				label.setFont(
						new Font("Serif", Font.BOLD | Font.ITALIC, 48));
				label.setSize(label.getPreferredSize());

				final JColorChooser colorChooser =
						new JColorChooser(Color.RED);
				colorChooser.setPreviewPanel(label);
				// For no preview panel, add a JComponent with no size
				// colorChooser.setPreviewPanel(new JPanel());

				contentPane.add(colorChooser, BorderLayout.CENTER);

				frame.pack();
				frame.setVisible(true);
			}
		};

		EventQueue.invokeLater(runner);
	}

}

