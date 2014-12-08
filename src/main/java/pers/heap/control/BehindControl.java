package pers.heap.control;


import pers.heap.heap.HeapAbs;
import pers.heap.node.NodeArray;
import pers.heap.share.SharedVar;
import pers.heap.utils.Constants;
import pers.heap.view.ControlPane;
import pers.heap.view.MainWindow;

/**
 * 进行后台控制的类，实现runnable接口
 */
public class BehindControl implements Runnable {


    /**
     * 堆的实现类
     */
    protected HeapAbs heap;
    private double[] data;
    private ControlPane pane;
    private MainWindow main;

    public BehindControl() {
        super();
    }

    /**
     * 使当前进程暂时停止
     */
    public synchronized void stop() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void start() {
        notify();
    }

    @Override
    public void run() {
        while (true) {
            decideShowWay();
            SharedVar.isAuto = true;
            SharedVar.delayTime = pane.getSpeed().getValue();
            NodeArray.orderData.clear();
            switch (SharedVar.showType) {
                case Constants.INIT_HEAP:
                    heap.init();
                    break;
                case Constants.ADD_NODE:
                    heap.add(data);
                    break;
                case Constants.DELETE_NODE:
                    heap.delete();
                    break;
                case Constants.SORT_HEAP:
                    heap.heapSort();
                    break;
                default:
                    break;
            }
            stopState();
            stop();
            runState();
        }
    }

    /**
     * 判断演示方式
     */
    private void decideShowWay() {
        if (SharedVar.showWay == Constants.SHOW_BOOK) {
            heap = SharedVar.book;
        } else {
            heap = SharedVar.other;
        }
        heap.setControl(this);
    }

    private void stopState() {
        pane.stopState();
        main.stopState();

    }

    private void runState() {
        pane.runState();
        main.runState();
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    public ControlPane getPane() {
        return pane;
    }

    public void setPane(ControlPane pane) {
        this.pane = pane;
    }

    public MainWindow getMain() {
        return main;
    }

    public void setMain(MainWindow main) {
        this.main = main;
    }

}
