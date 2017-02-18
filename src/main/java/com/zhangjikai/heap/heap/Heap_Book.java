package com.zhangjikai.heap.heap;


import com.zhangjikai.heap.node.HeapNode;
import com.zhangjikai.heap.node.NodeArray;
import com.zhangjikai.heap.share.SharedVar;
import com.zhangjikai.heap.utils.Constants;
import com.zhangjikai.heap.utils.DrawNodes;

/**
 * 按照书上的算法实现堆
 */
public class Heap_Book extends HeapAbs {

    public Heap_Book() {
        helper = new Helper_Book();
    }

    @Override
    public void init() {
        DrawNodes.isDrawDataAgain = true;
        int size = SharedVar.nodes.size();
        if (size == 1) {
            SharedVar.area.repaint();
            return;
        }
        for (int index = size / 2; index >= 1; index--) {

            HeapNode y = helper.getTempNode(index, 1);
            isStep();
            int c = 2 * index;
            while (c <= size) {
                if (c + 1 < size) {
                    helper.selectNode(c);
                    isStep();
                }

                switch (SharedVar.heapType) {
                    case Constants.MAX_HEAP:
                        if (c < size
                                && SharedVar.nodes.getNodeData(c) < SharedVar.nodes.getNodeData(c + 1))
                            c++;
                        break;
                    case Constants.MIN_HEAP:
                        if (c < size
                                && SharedVar.nodes.getNodeData(c) > SharedVar.nodes.getNodeData(c + 1))
                            c++;
                        break;
                    default:
                        break;
                }

                helper.selectNode(y, SharedVar.nodes.getNode(c));
                isStep();

                if (SharedVar.heapType == Constants.MAX_HEAP)
                    if (y.getData() >= SharedVar.nodes.getNodeData(c))
                        break;
                if (SharedVar.heapType == Constants.MIN_HEAP)
                    if (y.getData() <= SharedVar.nodes.getNodeData(c))
                        break;

                helper.moveNodeData(SharedVar.nodes.getNode(c), SharedVar.nodes.getNode(c / 2));
                SharedVar.nodes.getNode(c / 2).setData(SharedVar.nodes.getNodeData(c));
                SharedVar.area.repaint();
                c *= 2;
                isStep();
            }
            helper.moveNodeData(y, SharedVar.nodes.getNode(c / 2));
            SharedVar.nodes.getNode(c / 2).setData(y.getData());
            helper.removeTempNode();
            SharedVar.area.repaint();
            isStep();
        }
        DrawNodes.isDrawDataAgain = false;
        DrawNodes.message = "";
    }

    @Override
    public void add(double[] data) {
        DrawNodes.isDrawDataAgain = true;
        for (int index = 0; index < data.length; index++) {
            DrawNodes.message = "插入节点："
                    + (data[index] == (int) data[index] ? String.valueOf((int) data[index])
                    : data[index]);
            SharedVar.area.repaint();
            HeapNode tempNode = helper.getTempNode(data[index]);
            isStep();
            int i = SharedVar.nodes.size();
            while (i != 1) {
                helper.selectNode(tempNode, SharedVar.nodes.getNode(i / 2));
                isStep();

                if (SharedVar.heapType == Constants.MAX_HEAP)
                    if (tempNode.getData() <= SharedVar.nodes.getNodeData(i / 2))
                        break;
                if (SharedVar.heapType == Constants.MIN_HEAP)
                    if (tempNode.getData() >= SharedVar.nodes.getNodeData(i / 2))
                        break;

                helper.moveNodeData(SharedVar.nodes.getNode(i / 2), SharedVar.nodes.getNode(i));
                SharedVar.nodes.getNode(i).setShowData(true);
                SharedVar.nodes.getNode(i).setData(SharedVar.nodes.getNodeData(i / 2));
                SharedVar.area.repaint();
                i /= 2;
                isStep();
            }
            helper.moveNodeData(tempNode, SharedVar.nodes.getNode(i));
            SharedVar.nodes.getNode(i).setShowData(true);
            SharedVar.nodes.getNode(i).setData(tempNode.getData());
            helper.removeTempNode();
            SharedVar.area.repaint();
            if (index != data.length - 1)
                isStep();
        }
        DrawNodes.isDrawDataAgain = false;
        DrawNodes.message = "";
    }

    @Override
    public void delete() {
        deleteData();
        helper.removeTempNode();
        SharedVar.area.repaint();
    }

    @Override
    public void heapSort() {
        init();
        int a = SharedVar.nodes.size();
        for (int i = 0; i < a; i++) {
            deleteData();
            helper.removeTempNode(2);
            SharedVar.area.repaint();
            isStep();
            double data = SharedVar.nodes.getTempNode(1).getData();
            helper.moveTempData(SharedVar.nodes.getTempNode(1), a - i);
            NodeArray.orderData.put(i + 1, data);
            helper.removeTempNode();
            SharedVar.area.repaint();
            isStep();
        }
    }

    private void deleteData() {
        DrawNodes.isDrawDataAgain = true;
        helper.getTempNode(1, 1);
        isStep();
        DrawNodes.message = "删除节点：" + SharedVar.nodes.getNodeData(1);
        if (SharedVar.nodes.size() == 1) {
            SharedVar.nodes.removeNode(SharedVar.nodes.size());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SharedVar.area.repaint();
        }
        if (SharedVar.nodes.size() > 1) {
            SharedVar.area.repaint();
            HeapNode y = helper.getTempNode(SharedVar.nodes.size(), 2);
            SharedVar.nodes.removeNode(SharedVar.nodes.size());
            SharedVar.area.repaint();
            isStep();
            int i = 1, ci = 2;
            int CurrentSize = SharedVar.nodes.size();
            while (ci <= CurrentSize) {
                if (ci + 1 < CurrentSize) {
                    helper.selectNode(ci);
                    isStep();
                }

                switch (SharedVar.heapType) {
                    case Constants.MAX_HEAP:
                        if (ci < CurrentSize
                                && SharedVar.nodes.getNodeData(ci) < SharedVar.nodes
                                .getNodeData(ci + 1))
                            ci++;
                        break;
                    case Constants.MIN_HEAP:
                        if (ci < CurrentSize
                                && SharedVar.nodes.getNodeData(ci) > SharedVar.nodes
                                .getNodeData(ci + 1))
                            ci++;
                        break;
                    default:
                        break;
                }

                helper.selectNode(y, SharedVar.nodes.getNode(ci));
                isStep();

                if (SharedVar.heapType == Constants.MAX_HEAP)
                    if (y.getData() >= SharedVar.nodes.getNodeData(ci))
                        break;
                if (SharedVar.heapType == Constants.MIN_HEAP)
                    if (y.getData() <= SharedVar.nodes.getNodeData(ci))
                        break;

                helper.moveNodeData(SharedVar.nodes.getNode(ci), SharedVar.nodes.getNode(i));
                SharedVar.nodes.getNode(i).setData(SharedVar.nodes.getNodeData(ci));
                i = ci;
                ci *= 2;
                SharedVar.area.repaint();
                isStep();
            }
            helper.moveNodeData(y, SharedVar.nodes.getNode(i));
            SharedVar.nodes.getNode(i).setData(y.getData());
            // isStep();
        }
        DrawNodes.isDrawDataAgain = false;
        DrawNodes.message = "";
    }

}
