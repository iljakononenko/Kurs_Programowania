package com.iljaknk;

import javax.swing.*;
import java.awt.*;

public class my_Dialog extends Dialog
{

    public my_Dialog(Frame owner, String text, String title)
    {
        super(owner);
        setSize(480, 360);
        setTitle(title);
        setLayout(new BorderLayout());

        Button button_close = new Button("Close");
        button_close.addActionListener( e -> { this.setVisible(false);});

        JTextArea text_info = new JTextArea(text);

        //text_info.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        text_info.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(text_info);

        add(scrollPane, BorderLayout.CENTER);
        add(button_close, BorderLayout.SOUTH);
    }
}
