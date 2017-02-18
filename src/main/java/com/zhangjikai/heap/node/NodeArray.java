package com.zhangjikai.heap.node;


import java.util.HashMap;
import java.util.Map;

/**
 * 存储堆的集合类
 */
public class NodeArray {

    /**
     * 存放正常节点
     */
    private Map<Integer, HeapNode> map;
    /**
     * 用来存放缓存的节点
     */
    private Map<Integer, HeapNode> tempMap;
    /**
     * 存储堆排序时已经排好节点的数据
     */
    public static Map<Integer, Double> orderData;


    public NodeArray() {
        map = new HashMap<Integer, HeapNode>();
        tempMap = new HashMap<Integer, HeapNode>();
        orderData = new HashMap<Integer, Double>();
    }

    /**
     * 根据节点数组创建集合类
     *
     * @param nodes 节点数组
     */
    public NodeArray(HeapNode[] nodes) {
        map = new HashMap<Integer, HeapNode>();
        tempMap = new HashMap<Integer, HeapNode>();
        orderData = new HashMap<Integer, Double>();
        for (int i = 1; i <= nodes.length; i++)
            map.put(i, nodes[i - 1]);
    }


    /**
     * 获得当前堆的大小
     */
    public int size() {
        return map.size();
    }

    /**
     * 获得指定位置的元素
     *
     * @param index 元素所在的位置（从1开始）
     * @return
     */
    public HeapNode getNode(int index) {
        return map.get(index);
    }

    /**
     * 删除指定位置的元素
     *
     * @param index 元素所在的位置（从1开始）
     */
    public void removeNode(int index) {
        map.remove(index);
    }

    public void removeAllNode() {
        map.clear();
    }

    /**
     * 在最后一个位置添加一个节点
     *
     * @param node
     */
    public void addNode(HeapNode node) {
        map.put(map.size() + 1, node);
    }

    public void addNode(double data) {
        HeapNode node = new HeapNode(data, 20, 34);
        map.put(map.size() + 1, node);
    }

    /**
     * 获得指定节点的数据
     *
     * @param index
     * @return
     */
    public double getNodeData(int index) {
        HeapNode node = map.get(index);
        if (node == null)
            return 0;
        return node.getData();
    }

    /**
     * 是否存在缓存节点
     *
     * @return true表示存在缓存节点
     */
    public boolean hasTempNode() {
        return tempMap.size() != 0;
    }

    /**
     * 添加缓存节点
     */
    public void addTempNode(int index, HeapNode node) {
        tempMap.put(index, node);
    }


    /**
     * 获得缓存节点
     */
    public HeapNode getTempNode(int index) {
        return tempMap.get(index);
    }

    /**
     * 删除缓存节点
     */
    public void removeTempNode(int index) {
        tempMap.remove(index);
    }

    public void removeAllTempNode() {
        tempMap.clear();
    }

    public int tempNodeSize() {
        return tempMap.size();
    }

    public Map<Integer, HeapNode> getMap() {
        return map;
    }

    public void setMap(Map<Integer, HeapNode> map) {
        this.map = map;
    }
}
