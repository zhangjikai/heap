package pers.heap.view;

import pers.heap.control.BehindControl;
import pers.heap.customUI.MyButton;
import pers.heap.customUI.MyJSlider;
import pers.heap.dialog.AddDialog;
import pers.heap.dialog.InitDialog;
import pers.heap.dialog.SortDialog;
import pers.heap.node.NodeArray;
import pers.heap.share.SharedVar;
import pers.heap.utils.Constants;
import pers.heap.utils.DrawNodes;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * 控制面板
 */
public class ControlPane extends JPanel implements ActionListener {

    private static final long serialVersionUID = 3277753480232784575L;

    private JPanel panel;
    /**
     * 开始按钮
     */
    private JButton start;
    /**
     * 下一步
     */
    private JButton next;
    /**
     * 跳过
     */
    private JButton jump;
    /**
     * 插入
     */
    private JButton add;
    /**
     * 删除
     */
    private JButton delete;
    /**
     * 初始化
     */
    private JButton init;
    /**
     * 堆排序
     */
    private JButton sort;
    /**
     * 重置
     */
    private JButton reset;
    /**
     * 分割条
     */
    private JSeparator[] separator;
    /**
     * 调节速度的滚动条
     */
    private JSlider speed;
    /**
     * 后台控制程序
     */
    private BehindControl control;

    private Font font = new Font("仿宋", Font.BOLD, 15);

    public ControlPane() {
        init();
    }

    private void init() {
        panel = new JPanel();

        setLayout(new BorderLayout());
        add(panel, BorderLayout.WEST);
        FlowLayout layout = (FlowLayout) panel.getLayout();
        layout.setVgap(4);
        layout.setHgap(20);

        separator = new JSeparator[3];
        for (int i = 0; i < 3; i++) {
            separator[i] = new JSeparator(1);
            separator[i].setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            separator[i].setPreferredSize(new Dimension(3, 40));
        }

        start = new MyButton("开始");
        start.setFont(font);
        start.setEnabled(false);
        start.setPreferredSize(new Dimension(60, 30));
        start.addActionListener(this);

        next = new MyButton("下一步");
        next.setFont(font);
        next.setEnabled(false);
        next.setPreferredSize(new Dimension(60, 30));
        next.addActionListener(this);

        jump = new MyButton("跳过");
        jump.setFont(font);
        jump.setEnabled(false);
        jump.setPreferredSize(new Dimension(60, 30));
        jump.addActionListener(this);

        add = new MyButton("插入");
        add.setFont(font);
        add.setPreferredSize(new Dimension(60, 30));
        add.addActionListener(this);

        delete = new MyButton("删除");
        delete.setFont(font);
        delete.setPreferredSize(new Dimension(60, 30));
        delete.addActionListener(this);

        init = new MyButton("创建堆");
        init.setFont(font);
        init.setPreferredSize(new Dimension(60, 30));
        init.addActionListener(this);

        sort = new MyButton("排序");
        sort.setFont(font);
        sort.setPreferredSize(new Dimension(60, 30));
        sort.addActionListener(this);

        reset = new MyButton("重置");
        reset.setFont(font);
        reset.setPreferredSize(new Dimension(60, 30));
        reset.addActionListener(this);

        speed = new MyJSlider(2, 100, 30);
        speed.setPreferredSize(new Dimension(150, 55));
        speed.setBorder(BorderFactory.createTitledBorder("动画速度"));
        speed.setPaintLabels(true);
        speed.setPaintTicks(true);
        speed.setInverted(true);
        speed.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                SharedVar.delayTime = speed.getValue();
            }
        });

        panel.add(start);
        panel.add(next);
        panel.add(jump);
        panel.add(separator[0]);
        panel.add(init);
        panel.add(add);
        panel.add(delete);
        panel.add(sort);
        panel.add(separator[1]);
        panel.add(reset);
        panel.add(separator[2]);
        panel.add(speed);
    }

    /**
     * 代码演示时按钮状态
     */
    public void runState() {
        start.setText("暂停");
        start.setEnabled(true);
        jump.setEnabled(true);
        add.setEnabled(false);
        delete.setEnabled(false);
        init.setEnabled(false);
        sort.setEnabled(false);
        reset.setEnabled(false);
    }

    /**
     * 非演示时按钮状态
     */
    public void stopState() {
        start.setEnabled(false);
        start.setText("开始");
        next.setEnabled(false);
        jump.setEnabled(false);
        add.setEnabled(true);
        delete.setEnabled(true);
        init.setEnabled(true);
        sort.setEnabled(true);
        reset.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 添加节点
        if (e.getSource() == add) {
            String data = AddDialog.open();
            if (data != null && !data.equals("")) {
                String[] datas = data.split(" ");
                double[] addData = new double[datas.length];
                for (int i = 0; i < datas.length; i++) {
                    addData[i] = Double.parseDouble(datas[i]);
                }
                control.setData(addData);
                SharedVar.showType = Constants.ADD_NODE;
                control.start();
            }
            return;
        }
        // 删除节点
        if (e.getSource() == delete) {
            if (SharedVar.nodes.size() <= 0) {
                JOptionPane.showMessageDialog(null, "没有可删除节点", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                SharedVar.showType = Constants.DELETE_NODE;
                control.start();
            }
            return;
        }

        // 初始化
        if (e.getSource() == init) {
            String data = InitDialog.open();
            if (data != null && !data.equals("")) {
                String[] datas = data.split(" ");
                SharedVar.nodesNum = datas.length;
                SharedVar.nodes.removeAllNode();
                for (int i = 0; i < datas.length; i++) {
                    SharedVar.nodes.addNode(Double.parseDouble(datas[i]));
                    SharedVar.area.setCalcPos(true);
                }

                SharedVar.showType = Constants.INIT_HEAP;
                control.start();
            }
            return;
        }


        //堆排序
        if (e.getSource() == sort) {
            String data = SortDialog.open();
            if (data == null || data.equals(""))
                return;
            if (data.equals(Constants.DIALOG_USE_EXIT_DATA)) {
                if (SharedVar.nodes.size() == 0)
                    return;
                SharedVar.showType = Constants.SORT_HEAP;
                control.start();
                return;
            }

            String[] datas = data.split(" ");
            SharedVar.nodesNum = datas.length;
            SharedVar.nodes.removeAllNode();
            for (int i = 0; i < datas.length; i++) {
                SharedVar.nodes.addNode(Double.parseDouble(datas[i]));
                SharedVar.area.setCalcPos(true);
            }
            SharedVar.showType = Constants.SORT_HEAP;
            control.start();
            return;
        }

        //重置
        if (e.getSource() == reset) {
            SharedVar.nodes.removeAllNode();
            SharedVar.nodes.removeAllTempNode();
            NodeArray.orderData.clear();
            SharedVar.area.repaint();
            DrawNodes.message = "";
            return;
        }

        // 开始
        if (e.getSource() == start) {
            if (start.getText().equals("开始")) {
                start.setText("暂停");
                next.setEnabled(false);
                control.start();
                SharedVar.isAuto = true;
                return;
            }
            if (start.getText().equals("暂停")) {
                start.setText("开始");
                next.setEnabled(true);
                SharedVar.isAuto = false;
                return;
            }
        }

        //下一步
        if (e.getSource() == next) {
            control.start();
        }

        if (e.getSource() == jump) {
            SharedVar.delayTime = 0;
            if (start.getText().equals("开始"))
                SharedVar.isAuto = true;
            control.start();
        }
    }

    public BehindControl getControl() {
        return control;
    }

    public void setControl(BehindControl control) {
        this.control = control;
    }

    public JSlider getSpeed() {
        return speed;
    }

    public void setSpeed(JSlider speed) {
        this.speed = speed;
    }

}
