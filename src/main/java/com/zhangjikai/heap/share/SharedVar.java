package com.zhangjikai.heap.share;

import com.zhangjikai.heap.heap.Heap_Other;
import com.zhangjikai.heap.node.NodeArray;
import com.zhangjikai.heap.heap.HeapAbs;
import com.zhangjikai.heap.heap.Heap_Book;
import com.zhangjikai.heap.utils.Constants;
import com.zhangjikai.heap.view.DrawArea;

/**
 * 提供一些共享的变量，使一些类通过改变这些共享变量来改变其他的类的状态
 */
public class SharedVar {

    private SharedVar() {
    }

    /**
     * 存放节点
     */
    public static NodeArray nodes;
    /**
     * 绘画区域
     */
    public static DrawArea area;
    /**
     * 堆的实现类一
     */
    public static HeapAbs book;
    /**
     * 堆的实现类二
     */
    public static HeapAbs other;
    /**
     * 数组的大小
     */
    public static int nodesNum;
    /**
     * 演示类型
     */
    public static int showType;
    /**
     * 演示方式
     */
    public static int showWay;
    /**
     * 堆的类型
     */
    public static int heapType;
    /**
     * 延迟时间
     */
    public static int delayTime;
    /**
     * 是否自动演示
     */
    public static boolean isAuto;

    public static void initVar() {
        nodes = new NodeArray();
        area = new DrawArea();
        book = new Heap_Book();
        other = new Heap_Other();
        //默认数组大小为31
        nodesNum = 31;
        showType = Constants.DO_NOTHING;
        showWay = Constants.SHOW_BOOK;
        heapType = Constants.MAX_HEAP;
        delayTime = 30;
        isAuto = true;

    }
}
