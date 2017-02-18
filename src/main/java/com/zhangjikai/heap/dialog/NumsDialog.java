package com.zhangjikai.heap.dialog;

import com.zhangjikai.heap.share.SharedVar;
import com.zhangjikai.heap.utils.Constants;
import com.zhangjikai.heap.utils.ShowHelper;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * 更改数组大小的对话框
 */
public class NumsDialog extends JDialog implements ActionListener {

    private TextField input;
    private JLabel msg;
    private JButton ok;
    private JButton cancle;
    private static int sort;

    public NumsDialog() {
        init();
    }

    private static final long serialVersionUID = -1872168015376779835L;

    private void init() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        panel.setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        JLabel label = new JLabel("提示：请输入新的数组容量");
        label.setBorder(BorderFactory.createTitledBorder(""));
        panel.add(label, BorderLayout.NORTH);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2.setBorder(BorderFactory.createTitledBorder("请输入"));
        input = new TextField();
        input.setFont(new Font("仿宋", 0, 16));

        input.setPreferredSize(new Dimension(200, 25));
        input.setText(SharedVar.nodesNum + "");
        input.select(0, input.getText().length());
        input.addTextListener(new TextListener() {
            String text;

            @Override
            public void textValueChanged(TextEvent e) {
                text = input.getText();

                if (text == null || text.trim().equals("")) {
                    msg.setText("请输入数据");
                    ok.setEnabled(false);
                    return;
                }
                try {
                    int i = Integer.parseInt(text.trim());
                    if (i <= 0) {
                        msg.setText("请输入一个正整数");
                        ok.setEnabled(false);
                        return;
                    }
                    ok.setEnabled(true);
                } catch (Exception e2) {
                    ok.setEnabled(false);
                    msg.setText("请输入一个正整数");
                }
            }
        });
        panel2.add(input);

        panel.add(panel2, BorderLayout.CENTER);
        msg = new JLabel("");
        panel2.add(msg);

        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createTitledBorder(""));
        panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 2));
        panel.add(panel3, BorderLayout.SOUTH);
        ok = new JButton("确认");
        ok.setEnabled(false);
        ok.setPreferredSize(new Dimension(70, 25));
        ok.addActionListener(this);
        ok.registerKeyboardAction(this,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        panel3.add(ok);
        cancle = new JButton("取消");
        cancle.setPreferredSize(new Dimension(70, 25));
        cancle.addActionListener(this);
        cancle.registerKeyboardAction(this,
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_IN_FOCUSED_WINDOW);
        panel3.add(cancle);
    }

    public static String open() {
        sort = 0;
        NumsDialog dialog = new NumsDialog();
        dialog.setTitle("调整数组大小");
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.setSize(230, 180);
        ShowHelper.showCenter(dialog);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        if (sort == Constants.DIALOG_OK)
            return dialog.input.getText();
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            sort = Constants.DIALOG_OK;
            dispose();
        }
        if (e.getSource() == cancle) {
            sort = Constants.DIALOG_CANCLE;
            dispose();
        }
    }
}
