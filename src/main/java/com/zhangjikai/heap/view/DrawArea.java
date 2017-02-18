package com.zhangjikai.heap.view;

import com.zhangjikai.heap.share.SharedVar;
import com.zhangjikai.heap.utils.CalcNodePos;
import com.zhangjikai.heap.utils.HeapNodesPos;
import com.zhangjikai.heap.utils.ResourceManager;
import com.zhangjikai.heap.utils.DrawNodes;

import java.awt.*;

import javax.swing.JPanel;


/**
 * 画堆节点
 */
public class DrawArea extends JPanel {

    private static final long serialVersionUID = 3256509321447822381L;

    /**
     * 是否计算节点位置，当更改了数组大小的时候置为true,以重新确定节点位置
     */
    private boolean isCalcPos = true;

    public DrawArea() {
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (isCalcPos) {
            int size = SharedVar.nodes.size();
            CalcNodePos.calcNodePos(SharedVar.nodesNum, 20, this);
            CalcNodePos.calcANodePos(SharedVar.nodesNum, 34, this);

            for (int i = 1; i <= size; i++) {
                SharedVar.nodes.getNode(i).setLocation(HeapNodesPos.getNodePos(i));
                SharedVar.nodes.getNode(i).setaLocation(HeapNodesPos.getANodePos(i));
            }
            isCalcPos = false;
        }
        drawNodes(g);

    }

    private void drawNodes(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setBackground(ResourceManager.COLOR_BACKGROUND);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        DrawNodes.drawBorder(g2d, this);
        g2d.setColor(Color.BLUE);
        // 使用抗锯齿模式呈现图形
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        //g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        //	g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);

        for (int i = 2; i <= SharedVar.nodes.size(); i++)
            DrawNodes.drawNodesLine(SharedVar.nodes.getNode(i / 2), SharedVar.nodes.getNode(i), g2d);

        for (int i = 1; i <= SharedVar.nodes.size(); i++) {
            DrawNodes.drawNode(SharedVar.nodes.getNode(i), g2d);
            DrawNodes.drawANode(SharedVar.nodes.getNode(i), g2d);

        }
        for (int i = 1; i <= SharedVar.nodes.size(); i++) {
            DrawNodes.drawStringOnNode(SharedVar.nodes.getNode(i), g2d);
            DrawNodes.drawStringOnANode(SharedVar.nodes.getNode(i), g2d);
        }

        // 画缓存节点
        if (SharedVar.nodes.hasTempNode()) {
            for (int i = 1; i <= SharedVar.nodes.tempNodeSize(); i++) {
                DrawNodes.drawNode(SharedVar.nodes.getTempNode(i), g2d);
                DrawNodes.drawANode(SharedVar.nodes.getTempNode(i), g2d);
            }
            for (int i = 1; i <= SharedVar.nodes.tempNodeSize(); i++) {
                DrawNodes.drawStringOnNode(SharedVar.nodes.getTempNode(i), g2d);
                DrawNodes.drawStringOnANode(SharedVar.nodes.getTempNode(i), g2d);
            }
        }

        DrawNodes.drawSpareANode(SharedVar.nodes.size() + 1, SharedVar.nodesNum, 34, g2d);
        DrawNodes.drawMessage(g2d);
    }

    public boolean isCalcPos() {
        return isCalcPos;
    }

    public void setCalcPos(boolean isCalcPos) {
        this.isCalcPos = isCalcPos;
    }
}
