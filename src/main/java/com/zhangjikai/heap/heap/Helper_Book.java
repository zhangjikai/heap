package com.zhangjikai.heap.heap;

import com.zhangjikai.heap.node.HeapNode;
import com.zhangjikai.heap.node.NodeArray;
import com.zhangjikai.heap.share.SharedVar;
import com.zhangjikai.heap.utils.HeapNodesPos;
import com.zhangjikai.heap.utils.ResourceManager;

import java.awt.Graphics;
import java.util.concurrent.TimeUnit;


public class Helper_Book extends HelperAbs {

    @Override
    public HeapNode getTempNode(int index, int tempIndex) {
        HeapNode node = SharedVar.nodes.getNode(index);
        HeapNode node2 = new HeapNode(node.getData(), node.getRadius(), node.getEdge(),
                node.getX(), node.getY(), node.getAX(), node.getAY());
        node2.setFillColor(ResourceManager.COLOR_FILL_TEMPNODE);
        SharedVar.nodes.addTempNode(tempIndex, node2);
        for (int i = 0; i < 7 * moveTime; i++) {
            node2.setLocation(node.getX() + i * node2.getRadius() * 2 / (5 * moveTime), node.getY());
            node2.setALocation(node.getAX(), node.getAY() + i * node.getEdge() / (5 * moveTime));
            try {
                Thread.sleep(SharedVar.delayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SharedVar.area.repaint();
        }
        return node2;
    }

    @Override
    public HeapNode getTempNode(double data) {
        NodeArray array = SharedVar.nodes;
        HeapNode node = new HeapNode(0, 20, 34);
        node.setLocation(HeapNodesPos.getNodePos(array.size() + 1));
        node.setaLocation(HeapNodesPos.getANodePos(array.size() + 1));
        node.setShowData(false);
        array.addNode(node);
        HeapNode temp = new HeapNode(data, 20, 34);
        double x = HeapNodesPos.getANodePos(1).getX() + 130;
        double y = HeapNodesPos.getANodePos(1).getY() - 40;
        double x1 = array.getNode(array.size()).getAX();
        double y1 = array.getNode(array.size()).getAY() + array.getNode(1).getEdge() * 6 / 5;
        double x2 = array.getNode(array.size()).getX() + array.getNode(1).getRadius() * 12 / 5;
        double y2 = array.getNode(array.size()).getY();
        double xSpace1 = (x1 - x) / (10 * moveTime);
        double ySpace1 = (y1 - y) / (10 * moveTime);
        double xSpace2 = (x2 - x) / (10 * moveTime);
        double ySpace2 = (y2 - y) / (10 * moveTime);
        temp.setLocation(x, y);
        temp.setALocation(x, y);
        temp.setFillColor(ResourceManager.COLOR_FILL_TEMPNODE);
        array.addTempNode(1, temp);
        for (int i = 1; i <= (10 * moveTime); i++) {
            temp.setLocation(x + i * xSpace2, y + i * ySpace2);
            temp.setALocation(x + i * xSpace1, y + i * ySpace1);
            try {
                Thread.sleep(SharedVar.delayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SharedVar.area.repaint();
        }
        return temp;
    }

    @Override
    public void moveNodeData(HeapNode node1, HeapNode node2) {
        Graphics g = SharedVar.area.getGraphics();
        double node1_x = node1.getDataX(g);
        double node1_y = node1.getDataY(g);
        double node1_aX = node1.getADataX(g);
        double node1_aY = node1.getADataY(g);
        double temp = node2.getData();
        node2.setData(node1.getData());
        double node2_x = node2.getDataX(g);
        double node2_y = node2.getDataY(g);
        double node2_aX = node2.getADataX(g);
        double node2_aY = node2.getADataY(g);
        node2.setData(temp);
        node1.setDataPoint(node1_x, node1_y);
        node2.setDataPoint(node2_x, node2_y);
        node1.setADataPoint(node1_aX, node1_aY);
        node2.setADataPoint(node2_aX, node2_aY);
        double xSpace = (node2_x - node1_x) / (10 * moveTime);
        double ySpace = (node2_y - node1_y) / (10 * moveTime);
        double aXSpace = (node2_aX - node1_aX) / (10 * moveTime);
        double aYspace = (node2_aY - node1_aY) / (10 * moveTime);
        node1.setMoved(true);
        for (int i = 0; i < (10 * moveTime - 1); i++) {
            node1.setDataPoint(node1.getDataPoint().getX() + xSpace, node1.getDataPoint().getY()
                    + ySpace);
            node1.setADataPoint(node1.getaDataPoint().getX() + aXSpace, node1.getaDataPoint()
                    .getY() + aYspace);
            try {
                TimeUnit.MILLISECONDS.sleep(SharedVar.delayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SharedVar.area.repaint();
        }
        node1.setMoved(false);
    }

    @Override
    public void moveTempData(HeapNode node, int index) {
        double x = HeapNodesPos.getANodePos(index).getX() + 10;
        double y = HeapNodesPos.getANodePos(index).getY() + 20;
        Graphics g = SharedVar.area.getGraphics();
        double x1 = node.getDataX(g);
        double y1 = node.getDataY(g);
        double x2 = node.getADataX(g);
        double y2 = node.getADataY(g);
        double xSpace = (x - x1) / (10 * moveTime);
        double ySpace = (y - y1) / (10 * moveTime);
        double aXSapce = (x - x2) / (10 * moveTime);
        double aYSpace = (y - y2) / (10 * moveTime);
        node.setMoved(true);
        for (int i = 1; i < (10 * moveTime); i++) {
            node.setDataPoint(x1 + xSpace * i, y1 + i * ySpace);
            node.setADataPoint(x2 + i * aXSapce, y2 + i * aYSpace);
            try {
                TimeUnit.MILLISECONDS.sleep(SharedVar.delayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SharedVar.area.repaint();
        }
        node.setMoved(false);
    }

    @Override
    public void removeTempNode() {
        SharedVar.nodes.removeAllTempNode();
        SharedVar.area.repaint();
    }

    @Override
    public void removeTempNode(int index) {
        SharedVar.nodes.removeTempNode(index);
        SharedVar.area.repaint();
    }
}
