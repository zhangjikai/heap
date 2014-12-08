package pers.heap.utils;


import pers.heap.share.SharedVar;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * 计算节点的位置
 */
public class CalcNodePos {

    private CalcNodePos() {
    }

    /**
     * 计算堆有几层
     *
     * @param total 堆中节点的总数
     * @return 堆的层数
     */
    public static int calcLayers(int total) {
        int count = 0;
        int temp = 0;
        for (int index = 0; index < total; index++) {
            temp += Math.pow(2, count);
            count++;
            if (temp >= total) {
                break;
            }
        }
        return count;
    }

    /**
     * 计算堆节点的位置
     *
     * @param total  节点总数
     * @param radius 堆节点半径
     * @param parent 节点绘制的面板
     */
    public static void calcNodePos(int total, int radius, JPanel parent) {

        HeapNodesPos.setNodesNum(total);
        int layers = calcLayers(total);
        // 节点层数小于3,先确定第一个节点
        if (layers < 3) {
            int flag;
            for (int i = 1; i <= total; i++) {
                if (i == 1) {
                    HeapNodesPos.getNodePos(i).setLocation(parent.getSize().getWidth() / 2 - radius / 2,
                            135);
                    continue;
                }
                flag = (i % 2 == 0 ? -1 : 1);
                HeapNodesPos.getNodePos(i).setLocation(HeapNodesPos.getNodePos(i / 2).getX() + flag * radius * 3,
                        HeapNodesPos.getNodePos(i / 2).getY() + radius * 4);
            }
        } else {// 节点层数大于等于3，先确定最后一层的节点
            int parentWidth = (int) parent.getSize().getWidth();
            int lastLayerNum = (int) Math.pow(2, layers - 1);
            Point2D.Double[] points = new Point2D.Double[lastLayerNum];

            for (int i = 0; i < lastLayerNum; i++) {
                points[i] = new Point2D.Double(parentWidth / 2 + (i - lastLayerNum / 2) * radius
                        * 3, radius * 4 * (layers - 1) + 135);
            }

            // 最后一层的节点的位置
            int begin = (int) Math.pow(2, layers - 1);
            for (int i = begin; i <= total; i++)
                HeapNodesPos.getNodePos(i).setLocation(points[i - begin]);

            // 倒数第二层的节点的位置
            begin = (int) Math.pow(2, layers - 2);
            int end = (int) Math.pow(2, layers - 1);
            for (int i = begin; i < end; i++)
                HeapNodesPos.getNodePos(i).setLocation(points[(i - begin) * 2].x + radius * 3 / 2,
                        radius * 4 * (layers - 2) + 135);

            // 剩余层的节点位置
            double space = 0;
            for (int i = begin - 1; i > 0; i--) {
                space = HeapNodesPos.getNodePos(2 * i + 1).getX() - HeapNodesPos.getNodePos(2 * i).getX();
                HeapNodesPos.getNodePos(i).setLocation(HeapNodesPos.getNodePos(2 * i).getX() + space / 2,
                        HeapNodesPos.getNodePos(2 * i).getY() - 4 * radius);
            }
        }
    }

    /**
     * 计算数组节点的位置
     */
    public static void calcANodePos(int total, int edge, JPanel parent) {
        Point2D point;
        HeapNodesPos.setNodesNum(total);
        int center = total / 2 + 1;
        for (int i = 1; i <= total; i++) {
            point = HeapNodesPos.getANodePos(i);
            point.setLocation(parent.getWidth() / 2 + (i - center) * (edge + 2), 50);
        }
    }

    /**
     * 设置绘画区域的大小
     */
    public static void setDrawArea(JPanel panel) {

        int layers = calcLayers(SharedVar.nodesNum);
        if (layers < 3) {
            panel.setPreferredSize(new Dimension(500, 500));
        } else {
            int num = (int) Math.pow(2, layers - 1);
            int x1 = 60 * num + 100;
            int x2 = 36 * SharedVar.nodesNum + 100;
            int x = x1 > x2 ? x1 : x2;
            panel.setPreferredSize(new Dimension(x, 80 * layers + 150));
        }
    }
}
