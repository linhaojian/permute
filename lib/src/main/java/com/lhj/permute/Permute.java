package com.lhj.permute;

public interface Permute {
    //设置内容
    void setContent(String content);
    //设置内容显示的颜色
    void setContentColor(int color);
    //设置背景显示的颜色
    void setBackGroundColor(int color);
    //设置圆角
    void setCorner(float corner);
    //设置是否可以点击
    void setClickEnable(boolean clickEnable);
    //设置是否显示为圆形
    void setCircle(boolean circle);
}
