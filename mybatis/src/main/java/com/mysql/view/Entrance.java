package com.mysql.view;

import java.awt.*;

public class Entrance {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogOnFrm frame = new LogOnFrm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
