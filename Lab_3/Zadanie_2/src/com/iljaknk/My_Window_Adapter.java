package com.iljaknk;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class My_Window_Adapter extends WindowAdapter
{
        public void windowClosing(WindowEvent e) { System.exit(0); }
}
