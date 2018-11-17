package com.mingyuechunqiu.listbsdialogfragmenthelper.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.mingyuechunqiu.listbsdialogfragmenthelper.R;

/**
 * <pre>
 *     author : 明月春秋
 *     e-mail : xiyujieit@163.com
 *     time   : 2018/11/17
 *     desc   : 默认的头部view
 *              实现HeaderViewable
 *     version: 1.0
 * </pre>
 */
public class DefaultHeaderView implements HeaderViewable {

    private View vHeader, vCancel, vTitle, vConfirm;

    private DefaultHeaderView() {
    }

    /**
     * 获取头部view
     *
     * @param context 上下文
     * @return 返回头部view
     */
    public static DefaultHeaderView getInstance(Context context) {
        DefaultHeaderView headerView = new DefaultHeaderView();
        headerView.vHeader = LayoutInflater.from(context).inflate(R.layout.bs_dfg_list_header_view, null);
        headerView.vCancel = headerView.vHeader.findViewById(R.id.tv_bs_dfg_cancel);
        headerView.vTitle = headerView.vHeader.findViewById(R.id.tv_bs_dfg_title);
        headerView.vConfirm = headerView.vHeader.findViewById(R.id.tv_bs_dfg_confirm);
        return headerView;
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
