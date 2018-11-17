package com.mingyuechunqiu.listbsdialogfragmenthelper.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mingyuechunqiu.listbsdialogfragmenthelper.R;
import com.mingyuechunqiu.listbsdialogfragmenthelper.bean.BSDialogFgListItemBean;

import java.util.List;

/**
 * <pre>
 *     author : xyj
 *     e-mail : yujie.xi@ehailuo.com
 *     time   : 2018/11/14
 *     desc   : 列表适配器
 *              继承自BaseQuickAdapter
 *     version: 1.0
 * </pre>
 */
public class BSDialogFragmentListAdapter extends BaseQuickAdapter<BSDialogFgListItemBean, BSDialogFragmentListAdapter.MyViewHolder> {

    public BSDialogFragmentListAdapter(int layoutResId, @Nullable List<BSDialogFgListItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(MyViewHolder helper, BSDialogFgListItemBean item) {
        helper.addOnClickListener(R.id.tv_bs_dfg_list_item_name)
                .addOnLongClickListener(R.id.tv_bs_dfg_list_item_name);
        helper.actvName.setText(item.getText());
    }

    public static class MyViewHolder extends BaseViewHolder {

        private AppCompatTextView actvName;

        public MyViewHolder(View view) {
            super(view);
            actvName = view.findViewById(R.id.tv_bs_dfg_list_item_name);
        }
    }
}
