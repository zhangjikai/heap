package com.zhangjikai.heap.dialog;

import com.zhangjikai.heap.share.SharedVar;
import com.zhangjikai.heap.utils.Constants;
import com.zhangjikai.heap.utils.ShowHelper;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

public class AddDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 3114749088994448635L;

    private TextField input;
    private JLabel msg;
    private JButton ok;
    private JButton cancle;
    private static int sort;


    public AddDialog() {
        init();
    }

    private void init() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        panel.setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        JLabel label = new JLabel("提示：如果输入多个数据，数据之间用空格隔开例如1 2");
        label.setBorder(BorderFactory.createTitledBorder(""));
        panel.add(label, BorderLayout.NORTH);
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel2.setBorder(BorderFactory.createTitledBorder("请输入"));
        input = new TextField();
        input.setFont(new Font("仿宋", 0, 16));

        input.setPreferredSize(new Dimension(300, 25));
        input.addTextListener(new TextListener() {
            String text;
            String[] numbers;

            @Override
            public void textValueChanged(TextEvent e) {
                text = input.getText();

                if (text == null || text.trim().equals("")) {
                    ok.setEnabled(false);
                    msg.setText("请输入数据");
                    return;
                }
                if (text.matches(".*\\ {2,}.*")) {
                    msg.setText("输入不合法,数字之间只能有一个空格");
                    ok.setEnabled(false);
                    return;
                }
                numbers = text.split(" ");
                for (int i = 0; i < numbers.length; i++) {
                    try {
                        Double.parseDouble(numbers[i]);
                    } catch (Exception e2) {
                        msg.setText("输入错误，请输入合法数据");
                        ok.setEnabled(false);
                        return;
                    }
                    if (numbers.length + SharedVar.nodes.size() > SharedVar.nodesNum) {
                        msg.setText("添加数据过多，超过数组大小");
                        ok.setEnabled(false);
                        return;
                    }
                }
                ok.setEnabled(true);
                msg.setText("输入正确");
            }
        });
        panel2.add(input);
        panel.add(panel2, BorderLayout.CENTER);
        msg = new JLabel("");
        panel2.add(msg);

        JPanel panel3 = new JPanel();
        panel3.setBorder(BorderFactory.createTitledBorder(""));
        panel3.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 2));
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
        AddDialog dialog = new AddDialog();
        dialog.setTitle("插入");
        dialog.setModal(true);
        dialog.setResizable(false);
        dialog.setSize(330, 180);
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
