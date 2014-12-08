package pers.heap.utils;


import java.awt.*;

/**
 * 用来管理程序中的一些资源
 */
public class ResourceManager {

    private ResourceManager() {
    }

    public static Font LARGE_FONT = new Font("Tahoma", Font.PLAIN, 13);

    public static Font MIDDLE_FONT = new Font("楷体", Font.PLAIN, 20);
    /**
     * 粗字体
     */
    public static Font BOLD_FONT = new Font("Tahoma", Font.BOLD, 13);
    /**
     * 绘制粗线条
     */
    public static Stroke BOLD_STROKE = new BasicStroke(3);
    /**
     * 绘制细线条
     */
    public static Stroke THIN_STROKE = new BasicStroke(1);
    /**
     * 填充节点的颜色
     */
    public static Color COLOR_FILL_NODE = new Color(204, 232, 207);
    /**
     * 填充的背景色
     */
    public static Color COLOR_BACKGROUND = new Color(232, 238, 244);
    /**
     * 填充缓存节点的颜色
     */
    public static Color COLOR_FILL_TEMPNODE = new Color(224, 224, 224);
    /**
     * 边框颜色
     */
    public static Color COLOR_BORDER = new Color(64, 112, 161);

}
