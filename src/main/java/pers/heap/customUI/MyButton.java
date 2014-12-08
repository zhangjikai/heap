package pers.heap.customUI;

import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

/**
 * 为JButton添加了鼠标事件,在鼠标移上去时变换边框
 */
public class MyButton extends JButton {

    private static final long serialVersionUID = 6033088596570120415L;

    public MyButton() {
        super();
        initialize();
    }

    public MyButton(Action a) {
        super(a);
        initialize();
    }

    public MyButton(Icon icon) {
        super(icon);
        initialize();
    }

    public MyButton(String text) {
        super(text);
        initialize();
    }

    public MyButton(String text, Icon icon) {
        super(text, icon);
        initialize();
    }

    private void initialize() {

        setMargin(new Insets(2, 2, 2, 2));
        setBorder(null);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseListener() {

            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            /** 鼠标离开 */
            public void mouseExited(MouseEvent e) {
                setBorder(null);
            }

            /** 鼠标进入 */
            public void mouseEntered(MouseEvent e) {
                setBorder(BorderFactory.createLoweredBevelBorder());
            }

            public void mouseClicked(MouseEvent e) {
            }
        });
    }
}
