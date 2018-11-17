package com.mingyuechunqiu.listbsdialogfragmenthelperproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.mingyuechunqiu.listbsdialogfragmenthelper.ui.view.HeaderViewable;

/**
 * <pre>
 *     author : 明月春秋
 *     e-mail : xiyujieit@163.com
 *     time   : 2018/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class CustomHeaderView implements HeaderViewable {

    private View vHeader, vCancel, vTitle, vConfirm;

    public CustomHeaderView(Context context) {
        vHeader = LayoutInflater.from(context).inflate(R.layout.custom_header_view, null);
        vCancel = vHeader.findViewById(R.id.iv_demo_cancel);
        vTitle = vHeader.findViewById(R.id.tv_demo_title);
        vConfirm = vHeader.findViewById(R.id.tv_demo_confirm);
    }

    @Override
    public View getHeaderView() {
        return vHeader;
    }

    @Override
    public View getCancelView() {
        return vCancel;
    }

    @Override
    public View getTitleView() {
        return vTitle;
    }

    @Override
    public View getConfirmView() {
        return vConfirm;
    }
}
