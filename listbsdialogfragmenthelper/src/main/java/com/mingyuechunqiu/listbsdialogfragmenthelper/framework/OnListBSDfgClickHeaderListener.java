package com.mingyuechunqiu.listbsdialogfragmenthelper.framework;

import android.support.annotation.NonNull;

import com.mingyuechunqiu.listbsdialogfragmenthelper.bean.BSDialogFgListItemBean;
import com.mingyuechunqiu.listbsdialogfragmenthelper.ui.bottomSheetDialogFragment.ListBSDialogFragment;

/**
 * <pre>
 *     author : xyj
 *     e-mail : yujie.xi@ehailuo.com
 *     time   : 2018/11/14
 *     desc   : 列表对话框文本监听器
 *     version: 1.0
 * </pre>
 */
public interface OnListBSDfgClickHeaderListener {

    /**
     * 当点击取消view时回调
     *
     * @param fragment 对话框
     * @param itemBean 选中的列表item数据
     */
    void onClickCancel(@NonNull ListBSDialogFragment fragment, BSDialogFgListItemBean itemBean);

    /**
     * 当点击标题view时回调
     *
     * @param fragment 对话框
     * @param itemBean 选中的列表item数据
     */
    void onClickTitle(@NonNull ListBSDialogFragment fragment, BSDialogFgListItemBean itemBean);

    /**
     * 当点击确认view时回调
     *
     * @param fragment 对话框
     * @param itemBean 选中的列表item数据
     */
    void onClickConfirm(@NonNull ListBSDialogFragment fragment, BSDialogFgListItemBean itemBean);

    /**
     * 当没有选择item点击确认view时回调
     *
     * @param fragment 对话框
     */
    void onClickConfirmWithoutSelecting(@NonNull ListBSDialogFragment fragment);
}
