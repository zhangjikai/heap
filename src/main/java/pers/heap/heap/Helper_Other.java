package pers.heap.heap;

import pers.heap.node.HeapNode;
import pers.heap.node.NodeArray;
import pers.heap.share.SharedVar;
import pers.heap.utils.HeapNodesPos;

import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

public class Helper_Other extends HelperAbs {

    /**
     * 使节点数据产生移动效果
     *
     * @param node1 数据的原节点
     * @param node2 数据移动的目标节点
     */
    public void moveNodeData(HeapNode node1, HeapNode node2) {
        moveNodeData(node1, node2, 0);
    }

    public void moveNodeData(HeapNode node1, HeapNode node2, int type) {
        Graphics g = SharedVar.area.getGraphics();
        double node1_x = node1.getDataX(g);
        double node1_y = node1.getDataY(g);
        double node1_aX = node1.getADataX(g);
        double node1_aY = node1.getADataY(g);
        double node2_x = node2.getDataX(g);
        double node2_y = node2.getDataY(g);
        double node2_aX = node2.getADataX(g);
        double node2_aY = node2.getADataY(g);
        node1.setDataPoint(node1_x, node1_y);
        node2.setDataPoint(node2_x, node2_y);
        node1.setADataPoint(node1_aX, node1_aY);
        node2.setADataPoint(node2_aX, node2_aY);
        double xSpace = (node2_x - node1_x) / (10 * moveTime);
        double ySpace = (node2_y - node1_y) / (10 * moveTime);
        double aXSpace = (node2_aX - node1_aX) / (10 * moveTime);
        double aYspace = (node2_aY - node1_aY) / (10 * moveTime);
        node1.setMoved(true);
        if (type == 0)
            node2.setMoved(true);
        else
            node2.setShowData(false);
        for (int i = 0; i < 10 * moveTime - 1; i++) {
            node1.setDataPoint(node1.getDataPoint().getX() + xSpace, node1.getDataPoint().getY()
                    + ySpace);
            node1.setADataPoint(node1.getaDataPoint().getX() + aXSpace, node1.getaDataPoint()
                    .getY() + aYspace);
            node2.setDataPoint(node2.getDataPoint().getX() - xSpace, node2.getDataPoint().getY()
                    - ySpace);
            node2.setADataPoint(node2.getaDataPoint().getX() - aXSpace, node2.getaDataPoint()
                    .getY() - aYspace);

            try {
                TimeUnit.MILLISECONDS.sleep(SharedVar.delayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SharedVar.area.repaint();
        }
        node1.setMoved(false);
        if (type == 0)
            node2.setMoved(false);
        else
            node2.setShowData(true);

    }

    public void addNode(double data) {
        NodeArray array = SharedVar.nodes;
        HeapNode node = new HeapNode(data, 20, 34);
        node.setLocation(HeapNodesPos.getNodePos(array.size() + 1));
        node.setaLocation(HeapNodesPos.getANodePos(array.size() + 1));
        array.addNode(node);
        double x = HeapNodesPos.getANodePos(1).getX() + 130;
        double y = HeapNodesPos.getANodePos(1).getY() - 40;
        double x1 = array.getNode(array.size()).getAX();
        double y1 = array.getNode(array.size()).getAY();
        double x2 = array.getNode(array.size()).getX();
        double y2 = array.getNode(array.size()).getY();
        double xSpace1 = (x1 - x) / (10 * moveTime);
        double ySpace1 = (y1 - y) / (10 * moveTime);
        double xSpace2 = (x2 - x) / (10 * moveTime);
        double ySpace2 = (y2 - y) / (10 * moveTime);
        node.setLocation(x, y);
        node.setALocation(x, y);
        for (int i = 1; i <= (10 * moveTime); i++) {
            node.setLocation(x + i * xSpace2, y + i * ySpace2);
            node.setALocation(x + i * xSpace1, y + i * ySpace1);
            try {
                TimeUnit.MILLISECONDS.sleep(SharedVar.delayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SharedVar.area.repaint();
        }
        node.setShowData(true);
    }

    public void deleteData() {
        HeapNode node = SharedVar.nodes.getNode(1);
        Graphics g = SharedVar.area.getGraphics();

        node.setMoved(true);
        double x = HeapNodesPos.getANodePos(1).getX() + 130;
        double y = HeapNodesPos.getANodePos(1).getY() - 10;
        double x1 = node.getDataX(g);
        double y1 = node.getDataY(g);
        double x2 = node.getADataX(g);
        double y2 = node.getADataY(g);
        double xSpace1 = (x - x1) / (10 * moveTime);
        double ySpace1 = (y - y1) / (10 * moveTime);
        double xSpace2 = (x - x2) / (10 * moveTime);
        double ySpace2 = (y - y2) / (10 * moveTime);

        for (int i = 1; i < (10 * moveTime - 1); i++) {
            node.setDataPoint(x1 + i * xSpace1, y1 + i * ySpace1);
            node.setADataPoint(x2 + i * xSpace2, y2 + i * ySpace2);
            try {
                TimeUnit.MILLISECONDS.sleep(SharedVar.delayTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SharedVar.area.repaint();
        }
        node.setMoved(false);
    }
}
