package pers.heap.customUI;

import java.awt.Cursor;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.BoundedRangeModel;
import javax.swing.JSlider;

/** 为JSlider添加了鼠标滚轮事件 */
public class MyJSlider extends JSlider{

    private static final long serialVersionUID = 1426635297127363229L;

    public MyJSlider() {
        super();
        initialize();
    }

    public MyJSlider(BoundedRangeModel brm) {
        super(brm);
        initialize();
    }

    public MyJSlider(int orientation) {
        super(orientation);
        initialize();
    }

    public MyJSlider(int min, int max) {
        super(min, max);
        initialize();
    }

    public MyJSlider(int min, int max, int value) {
        super(min, max, value);
        initialize();
    }

    public MyJSlider(int orientation,int min, int max, int value){
        super(orientation, min, max, value);
        initialize();
    }

    private void initialize() {
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        /** 添加鼠标滚轮事件 */
        addMouseWheelListener(new MouseWheelListener() {

            public void mouseWheelMoved(MouseWheelEvent e) {
                int count = e.getWheelRotation();
                if (count > 0) {
                    if (getValue() < getMaximum()) {
                        setValue(getValue() + 5);
                    }
                }
                if (count < 0) {
                    if (getValue() > getMinimum()) {
                        setValue(getValue() - 5);
                    }
                }
            }
        });
    }
}
