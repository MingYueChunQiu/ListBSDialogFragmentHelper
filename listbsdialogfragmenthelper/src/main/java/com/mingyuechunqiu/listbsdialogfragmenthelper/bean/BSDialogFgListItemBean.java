package com.mingyuechunqiu.listbsdialogfragmenthelper.bean;

import android.os.Bundle;

/**
 * <pre>
 *     author : xyj
 *     e-mail : yujie.xi@ehailuo.com
 *     time   : 2018/11/14
 *     desc   : 列表item项数据类
 *     version: 1.0
 * </pre>
 */
public class BSDialogFgListItemBean {

    private String text;//显示文本
    private Bundle extra;//额外数据

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Bundle getExtra() {
        return extra;
    }

    public void setExtra(Bundle extra) {
        this.extra = extra;
    }
}
