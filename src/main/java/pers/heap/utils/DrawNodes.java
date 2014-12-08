package pers.heap.utils;


import pers.heap.node.HeapNode;
import pers.heap.node.NodeArray;

import java.awt.*;

import javax.swing.JPanel;


/**
 * 节点的工具类，根据节点的信息绘制节点
 */
public class DrawNodes {

    /**
     * 提示信息
     */
    public static String message = "";

    public static boolean isDrawDataAgain = false;

    /**
     * 绘制堆节点数据
     */
    public static void drawStringOnNode(HeapNode node, Graphics g) {
        if (node == null)
            return;
        if (node.isShowData()) {
            g.setColor(Color.BLACK);
            g.setFont(ResourceManager.LARGE_FONT);
            String text = node.transData();
            if (!node.isMoved())
                g.drawString(text, (int) node.getDataX(g), (int) node.getDataY(g));
            else {
                if (isDrawDataAgain)
                    g.drawString(text, (int) node.getDataX(g), (int) node.getDataY(g));
                g.setColor(Color.RED);
                g.setFont(ResourceManager.BOLD_FONT);
                g.drawString(text, (int) node.getDataPoint().getX(), (int) node.getDataPoint()
                        .getY());
                g.setFont(ResourceManager.LARGE_FONT);
            }
            g.setColor(Color.BLUE);
        }
    }

    /**
     * 绘制堆节点连线
     */
    public static void drawNodesLine(HeapNode node1, HeapNode node2, Graphics g) {
        if (node1 == null || node2 == null)
            return;
        g.drawLine((int) node1.getCenterX(), (int) node1.getCenterY(), (int) node2.getCenterX(),
                (int) node2.getCenterY());
    }

    /**
     * 绘制堆节点
     */
    public static void drawNode(HeapNode node, Graphics g) {
        if (node == null)
            return;
        if (!node.isSelected()) {
            g.setColor(node.getFillColor());
            g.fillOval((int) node.getX() - 1, (int) node.getY() - 1, node.getRadius() * 2 + 2,
                    node.getRadius() * 2 + 2);
            g.setColor(Color.BLUE);
            g.drawOval((int) node.getX(), (int) node.getY(), node.getRadius() * 2,
                    node.getRadius() * 2);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(node.getFillColor());
            g2d.fillOval((int) node.getX() - 1, (int) node.getY() - 1, node.getRadius() * 2 + 2,
                    node.getRadius() * 2 + 2);
            g2d.setStroke(ResourceManager.BOLD_STROKE);
            g2d.setColor(Color.RED);
            g2d.drawOval((int) node.getX(), (int) node.getY(), node.getRadius() * 2,
                    node.getRadius() * 2);
            g2d.setStroke(ResourceManager.THIN_STROKE);
            g2d.setColor(Color.BLUE);
        }
    }

    /**
     * 绘制数组节点
     */
    public static void drawANode(HeapNode node, Graphics g) {
        if (node != null) {
            if (!node.isSelected()) {
                g.setColor(node.getFillColor());
                g.fillRect((int) node.getAX(), (int) node.getAY(), node.getEdge(), node.getEdge());
                g.setColor(Color.BLUE);
                g.drawRect((int) node.getAX(), (int) node.getAY(), node.getEdge(), node.getEdge());
            } else {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(node.getFillColor());
                g.fillRect((int) node.getAX(), (int) node.getAY(), node.getEdge(), node.getEdge());
                g2d.setStroke(ResourceManager.BOLD_STROKE);
                g2d.setColor(Color.RED);
                g.drawRect((int) node.getAX(), (int) node.getAY(), node.getEdge(), node.getEdge());
                g2d.setStroke(ResourceManager.THIN_STROKE);
                g2d.setColor(Color.BLUE);
            }
        }
    }

    /**
     * 绘制数组节点数据
     */
    public static void drawStringOnANode(HeapNode node, Graphics g) {
        if (node == null)
            return;
        if (node.isShowData()) {
            g.setColor(Color.black);
            g.setFont(ResourceManager.LARGE_FONT);
            String text = node.transData();
            if (!node.isMoved()) {
                g.drawString(text, (int) node.getADataX(g), (int) node.getADataY(g));
            } else {
                if (isDrawDataAgain)
                    g.drawString(text, (int) node.getADataX(g), (int) node.getADataY(g));
                g.setColor(Color.RED);
                g.setFont(ResourceManager.BOLD_FONT);
                g.drawString(text, (int) node.getaDataPoint().getX() - 3, (int) node
                        .getaDataPoint().getY());
                g.setFont(ResourceManager.LARGE_FONT);
            }
            g.setColor(Color.BLUE);
        }
    }

    /**
     * 画多余的数组节点
     */
    public static void drawSpareANode(int start, int end, int edge, Graphics g) {
        g.setColor(Color.GRAY);
        for (int i = start; i <= end; i++) {
            g.drawRect((int) HeapNodesPos.getANodePos(i).getX(), (int) HeapNodesPos.getANodePos(i)
                    .getY(), edge, edge);
        }
        if (NodeArray.orderData.size() > 0) {
            String text;
            double data;
            FontMetrics metrics = g.getFontMetrics();
            int nums = NodeArray.orderData.size();
            for (int i = 1; i <= nums; i++) {
                data = NodeArray.orderData.get(nums - i + 1);
                text = (int) data == data ? String.valueOf((int) data) : String.valueOf(data);
                int width = metrics.stringWidth(text);
                int height = metrics.getHeight();
                double x = HeapNodesPos.getANodePos(end - nums + i).getX();
                double y = HeapNodesPos.getANodePos(end - nums + i).getY();
                int xx = (int) x + (edge - width) / 2;
                int yy = (int) y + (edge + height) / 2 - 2;
                g.drawString(text, xx, yy);
            }
        }
        g.setColor(Color.BLUE);
    }

    /**
     * 画边框
     */
    public static void drawBorder(Graphics g, JPanel panel) {
        Graphics2D g2d = (Graphics2D) g;
        int margin = 3;
        g2d.setColor(ResourceManager.COLOR_BORDER);
        g2d.drawLine(margin, margin, panel.getWidth() - margin, margin);
        g2d.drawLine(margin, panel.getHeight() - margin, panel.getWidth() - margin,
                panel.getHeight() - margin);
        g2d.drawLine(margin, margin, margin, panel.getHeight() - margin);
        g2d.drawLine(panel.getWidth() - margin, margin, panel.getWidth() - margin,
                panel.getHeight() - margin);
    }

    /**
     * 写提示信息
     */
    public static void drawMessage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(ResourceManager.MIDDLE_FONT);
        g2d.drawString(message, (int) HeapNodesPos.getANodePos(1).getX(), 30);
    }
}
