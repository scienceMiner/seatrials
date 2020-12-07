package com.scienceminer.panel;

import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import javax.swing.*;

public class CopyDialog {

  public static void main(String args[]) 
	{

    JFrame frame = new JFrame("Copy/Paste");
    Container contentPane = frame.getContentPane();

    final Toolkit kit = Toolkit.getDefaultToolkit();
    final Clipboard clipboard =
      kit.getSystemClipboard();

    final JTextArea jt = new JTextArea();

    JScrollPane pane = new JScrollPane(jt);
    contentPane.add(pane, BorderLayout.CENTER); 

    JPanel bottom = new JPanel();

    JButton copy = new JButton("Copy");
    bottom.add(copy);

    ActionListener copyListener = new 
      ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String selection = jt.getSelectedText();
        StringSelection data = new 
          StringSelection(selection);
        clipboard.setContents(data, data);
      }
    };

    copy.addActionListener(copyListener);

    JButton paste = new JButton("Paste");
    bottom.add(paste);

    ActionListener pasteListener = new 
      ActionListener() {
      public void actionPerformed(ActionEvent 
        actionEvent) {
        Transferable clipData = 
          clipboard.getContents(clipboard);
        if (clipData != null) {
          try {
            if (clipData.isDataFlavorSupported(
                DataFlavor.stringFlavor)) {
              String s = 
                (String)(clipData.getTransferData(
                DataFlavor.stringFlavor));
              jt.replaceSelection(s);
            } else {
              kit.beep();
            }
          } catch (Exception e) {
            System.err.println("Problems getting data: " + e);
          }
        }
      }
    };

    paste.addActionListener(pasteListener);

    contentPane.add(bottom, BorderLayout.SOUTH);

    frame.setDefaultCloseOperation
      (JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 300);
    frame.show();
  }
}
