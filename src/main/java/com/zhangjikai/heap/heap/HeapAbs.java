package com.zhangjikai.heap.heap;


import com.zhangjikai.heap.control.BehindControl;
import com.zhangjikai.heap.share.SharedVar;

/**
 * 堆的父类
 */
public abstract class HeapAbs {

    /**
     * 使节点产生动态的效果
     */
    protected HelperAbs helper;
    /**
     * 后台控制程序
     */
    protected BehindControl control;

    public HeapAbs() {
    }

    /**
     * 是否单步执行
     */
    public void isStep() {
        if (!SharedVar.isAuto)
            control.stop();
    }

    /**
     * 初始化堆
     */
    public abstract void init();

    /**
     * 添加数据
     */
    public abstract void add(double[] data);

    /**
     * 删除数据
     */
    public abstract void delete();

    /**
     * 堆排序
     */
    public abstract void heapSort();


    public HelperAbs getHelper() {
        return helper;
    }

    public void setHelper(HelperAbs helper) {
        this.helper = helper;
    }


    public BehindControl getControl() {
        return control;
    }

    public void setControl(BehindControl control) {
        this.control = control;
    }


}
