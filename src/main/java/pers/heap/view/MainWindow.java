package pers.heap.view;


import pers.heap.control.BehindControl;
import pers.heap.dialog.NumsDialog;
import pers.heap.share.SharedVar;
import pers.heap.utils.CalcNodePos;
import pers.heap.utils.Constants;
import pers.heap.utils.ShowHelper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.BevelBorder;


/**
 * 程序的主界面
 */
public class MainWindow {

    private JFrame frame;
    private JScrollPane scrollPane;
    /**
     * 控制面板
     */
    private ControlPane controlPane;
    /**
     * 演示方式一的菜单选项
     */
    private JRadioButtonMenuItem showWay1;
    /**
     * 演示方式二
     */
    private JRadioButtonMenuItem showWay2;
    /**
     * 最大堆
     */
    private JRadioButtonMenuItem maxHeap;
    /**
     * 最小堆
     */
    private JRadioButtonMenuItem minHeap;
    /**
     * 数组大小
     */
    private JMenuItem numbers;
    /**
     * 关于
     */
    private JMenuItem about;
    /**
     * 菜单选项的监听
     */
    private HeapMenuListener listener;
    /**
     * 后台控制程序
     */
    private BehindControl control;

    // 在程序开始运行之前对共享变量进行初始化
    static {
        SharedVar.initVar();
    }

    public MainWindow() {
        init();
    }

    private void init() {
        frame = new JFrame();

        scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setAutoscrolls(true);
        scrollPane.getViewport().setView(SharedVar.area);
        CalcNodePos.setDrawArea(SharedVar.area);

        SharedVar.area.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        controlPane = new ControlPane();
        control = new BehindControl();
        control.setPane(controlPane);
        control.setMain(this);
        controlPane.setControl(control);
        controlPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        controlPane.setPreferredSize(new Dimension(100, 65));
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(controlPane, BorderLayout.SOUTH);
        addMenu();

        new Thread(control).start();
    }

    public void addMenu() {
        listener = new HeapMenuListener();
        JMenuBar bar = new JMenuBar();
        frame.setJMenuBar(bar);
        JMenu menu = new JMenu("设置");
        bar.add(menu);
        JMenu menu_ = new JMenu("关于");
        bar.add(menu_);
        about = new JMenuItem("关于。。。");
        about.addActionListener(listener);
        menu_.add(about);

        JMenu menu1 = new JMenu("演示方式");
        menu.add(menu1);
        JMenu menu2 = new JMenu("堆的类型");
        menu.add(menu2);
        menu.addSeparator();
        numbers = new JMenuItem("数组大小");
        menu.add(numbers);
        numbers.addActionListener(listener);
        menu.addSeparator();
        JMenuItem exit = new JMenuItem("退出");
        menu.add(exit);
        exit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        showWay1 = new JRadioButtonMenuItem("方式一");
        showWay1.setSelected(true);
        showWay1.addActionListener(listener);
        showWay2 = new JRadioButtonMenuItem("方式二");
        showWay2.addActionListener(listener);
        maxHeap = new JRadioButtonMenuItem("最大堆");
        maxHeap.addActionListener(listener);
        maxHeap.setSelected(true);
        minHeap = new JRadioButtonMenuItem("最小堆");
        minHeap.addActionListener(listener);

        ButtonGroup group1 = new ButtonGroup();
        group1.add(showWay1);
        group1.add(showWay2);
        ButtonGroup group2 = new ButtonGroup();
        group2.add(maxHeap);
        group2.add(minHeap);

        menu1.add(showWay1);
        menu1.add(showWay2);

        menu2.add(maxHeap);
        menu2.add(minHeap);

    }

    public void run() {
        frame.setSize(1300, 660);
        frame.setTitle("堆");
        ShowHelper.showCenter(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * 演示时设置按钮不可用
     */
    public void runState() {
        showWay1.setEnabled(false);
        showWay2.setEnabled(false);
        maxHeap.setEnabled(false);
        minHeap.setEnabled(false);
        numbers.setEnabled(false);
    }

    public void stopState() {
        showWay1.setEnabled(true);
        showWay2.setEnabled(true);
        maxHeap.setEnabled(true);
        minHeap.setEnabled(true);
        numbers.setEnabled(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        new MainWindow().run();
    }

    /**
     * 监听菜单选项的变化
     */
    public class HeapMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == showWay1) {
                SharedVar.showWay = Constants.SHOW_BOOK;
                return;
            }
            if (e.getSource() == showWay2) {
                SharedVar.showWay = Constants.SHOW_OTHER;
                return;
            }
            if (e.getSource() == maxHeap) {
                SharedVar.heapType = Constants.MAX_HEAP;
                SharedVar.showType = Constants.INIT_HEAP;
                control.start();
                return;
            }
            if (e.getSource() == minHeap) {
                SharedVar.heapType = Constants.MIN_HEAP;
                SharedVar.showType = Constants.INIT_HEAP;
                control.start();
                return;
            }
            if (e.getSource() == numbers) {
                String text = NumsDialog.open();
                if (text != null && !text.equals("")) {
                    int num = Integer.parseInt(text);
                    if (num < SharedVar.nodesNum)
                        SharedVar.nodes.removeAllNode();
                    SharedVar.nodesNum = num;
                    CalcNodePos.setDrawArea(SharedVar.area);
                    scrollPane.remove(SharedVar.area);
                    scrollPane.getViewport().setView(SharedVar.area);
                    scrollPane.validate();
                    frame.validate();
                    SharedVar.area.setCalcPos(true);
                    SharedVar.area.repaint();
                }
                return;
            }
            if (e.getSource() == about) {
                JOptionPane.showMessageDialog(frame, "https://github.com/zhangjikai", "关于",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

}
