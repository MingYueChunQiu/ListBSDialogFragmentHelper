package com.mingyuechunqiu.listbsdialogfragmenthelper.framework;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mingyuechunqiu.listbsdialogfragmenthelper.bean.BSDialogFgListItemBean;

/**
 * <pre>
 *     author : 明月春秋
 *     e-mail : xiyujieit@163.com
 *     time   : 2018/11/17
 *     desc   : 列表对话框item监听器
 *     version: 1.0
 * </pre>
 */
public interface OnListBSDfgClickItemListener {

    /**
     * 列表item的点击事件（除非自己传入adapter并设置，否则需与BaseRecyclerViewAdapterHelper第三方库配合使用）
     *
     * @param adapter  适配器
     * @param view     点击的view
     * @param position item索引位置
     * @param itemBean 选中的列表item数据
     */
    void onClickListItem(BaseQuickAdapter adapter, View view, int position, BSDialogFgListItemBean itemBean);

    /**
     * 列表item的长按点击事件（除非自己传入adapter并设置，否则需与BaseRecyclerViewAdapterHelper第三方库配合使用）
     *
     * @param adapter  适配器
     * @param view     点击的view
     * @param position item索引位置
     * @param itemBean 选中的列表item数据
     * @return 成功点击长按事件返回true，否则返回false
     */
    boolean onLongClickListItem(BaseQuickAdapter adapter, View view, int position, BSDialogFgListItemBean itemBean);
}
