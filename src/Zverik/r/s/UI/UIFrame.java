package Zverik.r.s.UI;

import javax.swing.*;

public class UIFrame {

    private static UIFrame uiFrame;

    public static UIFrame getFactory() {
        if (uiFrame != null) {
            return uiFrame;
        } else
            return new UIFrame();
    }


    private JFrame jFrame;

    private UIFrame() {
        jFrame = new JFrame("Test");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public void show() {

    }
}
