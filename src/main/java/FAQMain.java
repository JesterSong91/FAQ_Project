import ui_service.FAQ_UI;

import javax.swing.*;
import java.awt.*;

public class FAQMain {

    public FAQMain() {

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame frame = new JFrame("FAQ");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new FAQ_UI().rootPanel);
                frame.setPreferredSize(new Dimension(900, 700));
                frame.pack();
                frame.setVisible(true);

            }
        });

    }
}
