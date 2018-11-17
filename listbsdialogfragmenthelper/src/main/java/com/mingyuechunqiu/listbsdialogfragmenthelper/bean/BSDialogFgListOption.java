package com.mingyuechunqiu.listbsdialogfragmenthelper.bean;

import android.support.v7.widget.RecyclerView;

import com.mingyuechunqiu.listbsdialogfragmenthelper.framework.OnListBSDfgClickItemListener;
import com.mingyuechunqiu.listbsdialogfragmenthelper.framework.OnListBSDfgClickTextListener;

import java.util.List;

/**
 * <pre>
 *     author : xyj
 *     e-mail : yujie.xi@ehailuo.com
 *     time   : 2018/11/14
 *     desc   : 列表选项
 *     version: 1.0
 * </pre>
 */
public class BSDialogFgListOption {

    private String titleText;//标题文本
    private String cancelText;//取消文本
    private String confirmText;//确认文本
    private int titleColor;//标题文本颜色
    private int cancelColor;//取消文本颜色
    private int confirmColor;//确认文本颜色
    private List<BSDialogFgListItemBean> list;//列表数据
    private RecyclerView.LayoutManager layoutManager;//列表布局管理器
    private RecyclerView.Adapter adapter;//列表适配器
    private OnListBSDfgClickTextListener textListener;//列表对话框文本监听器
    private OnListBSDfgClickItemListener itemListener;//列表对话框item监听器

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getCancelText() {
        return cancelText;
    }

    public void setCancelText(String cancelText) {
        this.cancelText = cancelText;
    }

    public String getConfirmText() {
        return confirmText;
    }

    public void setConfirmText(String confirmText) {
        this.confirmText = confirmText;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public int getCancelColor() {
        return cancelColor;
    }

    public void setCancelColor(int cancelColor) {
        this.cancelColor = cancelColor;
    }

    public int getConfirmColor() {
        return confirmColor;
    }

    public void setConfirmColor(int confirmColor) {
        this.confirmColor = confirmColor;
    }

    public List<BSDialogFgListItemBean> getList() {
        return list;
    }

    public void setList(List<BSDialogFgListItemBean> list) {
        this.list = list;
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public RecyclerView.Adapter getAdapter() {
        return adapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public OnListBSDfgClickTextListener getOnListBSDfgClickTextListener() {
        return textListener;
    }

    public void setOnListBSDfgClickTextListener(OnListBSDfgClickTextListener textListener) {
        this.textListener = textListener;
    }

    public OnListBSDfgClickItemListener getOnListBSDfgClickItemListener() {
        return itemListener;
    }

    public void setOnListBSDfgClickItemListener(OnListBSDfgClickItemListener itemListener) {
        this.itemListener = itemListener;
    }

    /**
     * 链式调用
     */
    public static class Builder {

        private BSDialogFgListOption mOption;

        public Builder() {
            mOption = new BSDialogFgListOption();
        }

        public BSDialogFgListOption build() {
            return mOption;
        }

        public String getTitleText() {
            return mOption.titleText;
        }

        public Builder setTitleText(String titleText) {
            mOption.titleText = titleText;
            return this;
        }

        public String getCancelText() {
            return mOption.cancelText;
        }

        public Builder setCancelText(String cancelText) {
            mOption.cancelText = cancelText;
            return this;
        }

        public String getConfirmText() {
            return mOption.confirmText;
        }

        public Builder setConfirmText(String confirmText) {
            mOption.confirmText = confirmText;
            return this;
        }

        public int getTitleColor() {
            return mOption.titleColor;
        }

        public Builder setTitleColor(int titleColor) {
            mOption.titleColor = titleColor;
            return this;
        }

        public int getCancelColor() {
            return mOption.cancelColor;
        }

        public Builder setCancelColor(int cancelColor) {
            mOption.cancelColor = cancelColor;
            return this;
        }

        public int getConfirmColor() {
            return mOption.confirmColor;
        }

        public Builder setConfirmColor(int confirmColor) {
            mOption.confirmColor = confirmColor;
            return this;
        }

        public List<BSDialogFgListItemBean> getList() {
            return mOption.list;
        }

        public Builder setList(List<BSDialogFgListItemBean> list) {
            mOption.list = list;
            return this;
        }

        public RecyclerView.LayoutManager getLayoutManager() {
            return mOption.layoutManager;
        }

        public Builder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
            mOption.layoutManager = layoutManager;
            return this;
        }

        public RecyclerView.Adapter getAdapter() {
            return mOption.adapter;
        }

        public Builder setAdapter(RecyclerView.Adapter adapter) {
            mOption.adapter = adapter;
            return this;
        }

        public OnListBSDfgClickTextListener getOnListBSDfgClickTextListener() {
            return mOption.textListener;
        }

        public Builder setOnListBSDfgClickTextListener(OnListBSDfgClickTextListener textListener) {
            mOption.textListener = textListener;
            return this;
        }

        public OnListBSDfgClickItemListener getOnListBSDfgClickItemListener() {
            return mOption.itemListener;
        }

        public Builder setOnListBSDfgClickItemListener(OnListBSDfgClickItemListener itemListener) {
            mOption.itemListener = itemListener;
            return this;
        }
    }
}
