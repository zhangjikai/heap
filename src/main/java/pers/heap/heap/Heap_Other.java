package pers.heap.heap;

import pers.heap.node.NodeArray;
import pers.heap.share.SharedVar;
import pers.heap.utils.Constants;
import pers.heap.utils.DrawNodes;

/**
 * 另一种演示效果
 */
public class Heap_Other extends HeapAbs {

    public Heap_Other() {
        helper = new Helper_Other();
    }

    @Override
    public void init() {
        int size = SharedVar.nodes.size();
        if (size == 1) {
            SharedVar.area.repaint();
            return;
        }
        for (int index = size / 2; index >= 1; index--) {
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

                helper.selectNode(SharedVar.nodes.getNode(c / 2), SharedVar.nodes.getNode(c));
                isStep();

                if (SharedVar.heapType == Constants.MAX_HEAP)
                    if (SharedVar.nodes.getNodeData(c / 2) >= SharedVar.nodes.getNodeData(c)) {
                        c *= 2;
                        break;
                    }
                if (SharedVar.heapType == Constants.MIN_HEAP)
                    if (SharedVar.nodes.getNodeData(c / 2) <= SharedVar.nodes.getNodeData(c)) {
                        c *= 2;
                        break;
                    }

                helper.moveNodeData(SharedVar.nodes.getNode(c), SharedVar.nodes.getNode(c / 2));
                double data = SharedVar.nodes.getNode(c / 2).getData();
                SharedVar.nodes.getNode(c / 2).setData(SharedVar.nodes.getNodeData(c));
                SharedVar.nodes.getNode(c).setData(data);
                SharedVar.area.repaint();
                c *= 2;
                if (c <= size)
                    isStep();
            }
            SharedVar.area.repaint();
        }
        DrawNodes.message = "";
    }

    @Override
    public void add(double[] data) {
        for (int index = 0; index < data.length; index++) {
            DrawNodes.message = "插入节点："
                    + (data[index] == (int) data[index] ? String.valueOf((int) data[index])
                    : data[index]);
            SharedVar.area.repaint();
            helper.addNode(data[index]);
            isStep();
            int i = SharedVar.nodes.size();
            while (i != 1) {
                helper.selectNode(SharedVar.nodes.getNode(i), SharedVar.nodes.getNode(i / 2));
                isStep();

                if (SharedVar.heapType == Constants.MAX_HEAP)
                    if (SharedVar.nodes.getNodeData(i) <= SharedVar.nodes.getNodeData(i / 2))
                        break;
                if (SharedVar.heapType == Constants.MIN_HEAP)
                    if (SharedVar.nodes.getNodeData(i) >= SharedVar.nodes.getNodeData(i / 2))
                        break;

                helper.moveNodeData(SharedVar.nodes.getNode(i / 2), SharedVar.nodes.getNode(i));
                double temp = SharedVar.nodes.getNodeData(i);
                SharedVar.nodes.getNode(i).setData(SharedVar.nodes.getNodeData(i / 2));
                SharedVar.nodes.getNode(i / 2).setData(temp);
                SharedVar.area.repaint();
                i /= 2;
                if (i != 1)
                    isStep();
            }
            if (index != data.length - 1)
                isStep();
        }
        DrawNodes.message = "";
    }

    @Override
    public void delete() {
        helper.deleteData();
        DrawNodes.message = "删除节点：" + SharedVar.nodes.getNodeData(1);
        if (SharedVar.nodes.size() > 1) {
            helper.moveNodeData(SharedVar.nodes.getNode(SharedVar.nodes.size()),
                    SharedVar.nodes.getNode(1), 1);
            SharedVar.nodes.getNode(1).setData(SharedVar.nodes.getNodeData(SharedVar.nodes.size()));
        }
        deleteData();
        DrawNodes.message = "";
    }

    @Override
    public void heapSort() {
        init();
        int a = SharedVar.nodes.size();
        for (int i = 1; i < a; i++) {
            SharedVar.area.repaint();
            double data = SharedVar.nodes.getNodeData(1);
            helper.moveNodeData(SharedVar.nodes.getNode(1),
                    SharedVar.nodes.getNode(SharedVar.nodes.size()));
            SharedVar.nodes.getNode(1).setData(SharedVar.nodes.getNodeData(SharedVar.nodes.size()));
            NodeArray.orderData.put(i, data);
            SharedVar.area.repaint();
            deleteData();
            isStep();
        }
        NodeArray.orderData.put(a, SharedVar.nodes.getNodeData(1));
        SharedVar.nodes.removeNode(1);
        SharedVar.area.repaint();
        DrawNodes.message = "";
    }

    private void deleteData() {
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
            SharedVar.nodes.removeNode(SharedVar.nodes.size());
            isStep();
            SharedVar.area.repaint();
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

                helper.selectNode(SharedVar.nodes.getNode(i), SharedVar.nodes.getNode(ci));

                if (SharedVar.heapType == Constants.MAX_HEAP)
                    if (SharedVar.nodes.getNodeData(i) >= SharedVar.nodes.getNodeData(ci))
                        break;
                if (SharedVar.heapType == Constants.MIN_HEAP)
                    if (SharedVar.nodes.getNodeData(i) <= SharedVar.nodes.getNodeData(ci))
                        break;
                isStep();
                helper.moveNodeData(SharedVar.nodes.getNode(ci), SharedVar.nodes.getNode(i));
                double data = SharedVar.nodes.getNodeData(i);
                SharedVar.nodes.getNode(i).setData(SharedVar.nodes.getNodeData(ci));
                SharedVar.nodes.getNode(ci).setData(data);
                i = ci;
                ci *= 2;
                SharedVar.area.repaint();
                if (ci < CurrentSize)
                    isStep();
            }
        }
    }

}
