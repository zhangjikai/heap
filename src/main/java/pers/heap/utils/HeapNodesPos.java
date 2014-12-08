package pers.heap.utils;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;

/**
 * 保存预先计算好的节点位置
 */
public class HeapNodesPos {

    /**
     * 存储树节点的位置
     */
    private static Map<Integer, Point2D> nodesPos;
    /**
     * 存储数组节点的位置
     */
    private static Map<Integer, Point2D> ANodesPos;

    private static void initMap() {
        if (nodesPos == null)
            nodesPos = new HashMap<Integer, Point2D>();
        if (ANodesPos == null)
            ANodesPos = new HashMap<Integer, Point2D>();
    }

    /**
     * 设置当前节点的数目
     */
    public static void setNodesNum(int total) {
        initMap();
        int size = nodesPos.size();
        if (total > size) {
            for (int i = size + 1; i <= total; i++) {
                nodesPos.put(i, new Point2D.Double());
                ANodesPos.put(i, new Point2D.Double());
            }
        }
    }

    /**
     * 获得第index个树节点的坐标
     */
    public static Point2D getNodePos(int index) {
        return nodesPos.get(index);
    }

    /**
     * 获得第index个数组节点的坐标
     */
    public static Point2D getANodePos(int index) {
        return ANodesPos.get(index);
    }
}
