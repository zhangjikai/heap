package pers.heap.heap;

import pers.heap.node.HeapNode;
import pers.heap.share.SharedVar;

/**
 * 使堆的节点产生动态效果
 */
public abstract class HelperAbs {

    protected int moveTime = 3;

    /**
     * 使第index个和第index+1（如果存在）产生闪烁的效果
     *
     * @param index
     */
    public void selectNode(int index) {
        for (int i = 1; i < 11; i++) {
            if (i % 2 == 0) {
                SharedVar.nodes.getNode(index).setSelected(false);
                if (index + 1 <= SharedVar.nodes.size())
                    SharedVar.nodes.getNode(index + 1).setSelected(false);
            } else {
                SharedVar.nodes.getNode(index).setSelected(true);
                if (index + 1 <= SharedVar.nodes.size())
                    SharedVar.nodes.getNode(index + 1).setSelected(true);
            }
            SharedVar.area.repaint();
            try {
                Thread.sleep(SharedVar.delayTime * moveTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 使选中的两个节点同时产生闪烁效果
     *
     * @param node1 第一个节点
     * @param node2 第二个节点
     */
    public void selectNode(HeapNode node1, HeapNode node2) {
        for (int i = 1; i < 11; i++) {
            if (i % 2 == 0) {
                node1.setSelected(false);
                node2.setSelected(false);
            } else {
                node1.setSelected(true);
                node2.setSelected(true);
            }
            SharedVar.area.repaint();
            try {
                Thread.sleep(SharedVar.delayTime * moveTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 产生第index个节点的缓存节点
     *
     * @param index
     */
    public HeapNode getTempNode(int index, int tempIndex) {
        return null;
    }

    /**
     * 添加节点时产生缓存节点
     */
    public HeapNode getTempNode(double data) {
        return null;
    }

    /**
     * 使节点数据产生移动效果
     *
     * @param node  数据的原节点
     * @param node2 数据移动的目标节点
     */
    public void moveNodeData(HeapNode node, HeapNode node2) {
    }

    /**
     * 移动缓存节点的数据
     */
    public void moveTempData(HeapNode tempNode, int i) {
    }

    /**
     * 移除所有的缓存节点
     */
    public void removeTempNode() {
    }

    /**
     * 移除指定位置的缓存节点
     */
    public void removeTempNode(int index) {
    }

    ///-----------------------------------------///

    /**
     * 添加节点
     */
    public void addNode(double d) {
    }

    /**
     * 删除节点
     */
    public void deleteData() {
    }

    /**
     * 使节点数据产生移动效果
     */
    public void moveNodeData(HeapNode node1, HeapNode node2, int type) {
    }
}
