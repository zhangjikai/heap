package com.zhangjikai.heap.node;


import com.zhangjikai.heap.utils.ResourceManager;

import java.awt.*;
import java.awt.geom.Point2D;


/**
 * 节点类
 */
public class HeapNode {

    /**
     * 节点的数据
     */
    protected double data;
    /**
     * 树节点圆圈的半径
     */
    protected int radius;
    /**
     * 树节点的位置
     */
    private Point2D location;
    /**
     * 树节点数据的位置
     */
    private Point2D dataPoint;
    /**
     * 树节点是否闪烁
     */
    private boolean isSelected;
    /**
     * 树节点数据是否在移动
     */
    private boolean isMoved;
    /**
     * 树节点填充的颜色
     */
    private Color fillColor;
    /**
     * 数组节点的边长
     */
    private int edge;
    /**
     * 数组节点的位置
     */
    private Point2D aLocation;
    /**
     * 数组节点数据的位置
     */
    private Point2D aDataPoint;
    /**
     * 节点是否显示数据
     */
    private boolean isShowData;

    /**
     * 构造方法
     *
     * @param data   节点数据
     * @param radius 树节点半径
     * @param edge   数组节点边长
     * @param x      树节点x坐标
     * @param y      树节点y坐标
     * @param aX     数组节点x坐标
     * @param aY     数组节点y坐标
     */
    public HeapNode(double data, int radius, int edge, double x, double y, double aX, double aY) {
        this.data = data;
        this.radius = radius;
        this.edge = edge;
        this.location = new Point2D.Double(x, y);
        this.aLocation = new Point2D.Double(aX, aY);
        init();
    }


    /**
     * @param data   节点数据
     * @param radius 树节点半径
     * @param edge   数组节点边长
     */
    public HeapNode(double data, int radius, int edge) {
        this.data = data;
        this.radius = radius;
        this.edge = edge;
        location = new Point2D.Double(0, 0);
        init();
    }

    /**
     * 对一些变量进行初始化操作
     */
    private void init() {
        dataPoint = new Point2D.Double(0, 0);
        aLocation = new Point2D.Double(0, 0);
        isSelected = false;
        isMoved = false;
        isShowData = true;
        fillColor = ResourceManager.COLOR_FILL_NODE;
    }

    /**
     * 将数字转化为字符串
     */
    public String transData() {
        int temp = (int) data;
        String text = (temp == data ? String.valueOf(temp) : String.valueOf(data));
        return text;
    }

    /**
     * 获得树节点中点的x坐标
     */
    public double getCenterX() {
        return location.getX() + radius;
    }

    /**
     * 获得树节点中点的y坐标
     */
    public double getCenterY() {
        return location.getY() + radius;
    }

    /**
     * 获得树节点的x坐标
     */
    public double getX() {
        return location.getX();
    }

    /**
     * 获得树节点的y坐标
     */
    public double getY() {
        return location.getY();
    }

    /**
     * 树节点数据居中显示时的x坐标
     */
    public double getDataX(Graphics g) {
        FontMetrics metrics = g.getFontMetrics();
        double x = metrics.stringWidth(transData());
        return getCenterX() - x / 2;
    }

    /**
     * 树节点数据居中显示时的y坐标
     */
    public double getDataY(Graphics g) {
        FontMetrics metrics = g.getFontMetrics();
        double y = metrics.getHeight();
        return (int) (getCenterY() + y * 0.3);
    }

    /**
     * 设置树节点的位置
     */
    public void setLocation(double x, double y) {
        if (location == null) {
            location = new Point2D.Double(x, y);
            return;
        }
        location.setLocation(x, y);
    }

    /**
     * 设置树节点数据的位置
     */
    public void setDataPoint(double x, double y) {
        if (dataPoint == null) {
            dataPoint = new Point2D.Double(x, y);
            return;
        }
        dataPoint.setLocation(x, y);
    }

    /**
     * 获得数组节点的x坐标
     */
    public double getAX() {
        return aLocation.getX();
    }

    /**
     * 获得数组节点的y坐标
     */
    public double getAY() {
        return aLocation.getY();
    }

    /**
     * 数组节点居中显示时的x坐标
     */
    public double getADataX(Graphics g) {
        FontMetrics metrics = g.getFontMetrics();
        double x = metrics.stringWidth(transData());
        return getAX() + (edge - x) / 2;
    }

    /**
     * 数组节点居中显示时的y坐标
     */
    public double getADataY(Graphics g) {
        FontMetrics metrics = g.getFontMetrics();
        double y = metrics.getHeight();
        return getAY() + (edge + y - 2) / 2;
    }

    /**
     * 设置数组节点的位置
     */
    public void setALocation(double x, double y) {
        if (aLocation == null) {
            aLocation = new Point2D.Double(x, y);
            return;
        }
        aLocation.setLocation(x, y);
    }

    /**
     * 设置数组节点数据的位置
     */
    public void setADataPoint(double x, double y) {
        if (aDataPoint == null) {
            aDataPoint = new Point2D.Double(x, y);
            return;
        }
        aDataPoint.setLocation(x, y);
    }

    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Point2D getLocation() {
        return location;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }

    public Point2D getDataPoint() {
        return dataPoint;
    }

    public void setDataPoint(Point2D dataPoint) {
        this.dataPoint = dataPoint;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean isMoved) {
        this.isMoved = isMoved;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public int getEdge() {
        return edge;
    }

    public void setEdge(int edge) {
        this.edge = edge;
    }

    public Point2D getaLocation() {
        return aLocation;
    }

    public void setaLocation(Point2D aLocation) {
        this.aLocation = aLocation;
    }

    public Point2D getaDataPoint() {
        return aDataPoint;
    }

    public void setaDataPoint(Point2D aDataPoint) {
        this.aDataPoint = aDataPoint;
    }

    public boolean isShowData() {
        return isShowData;
    }

    public void setShowData(boolean isShowData) {
        this.isShowData = isShowData;
    }
}
