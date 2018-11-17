package com.mingyuechunqiu.listbsdialogfragmenthelperproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingyuechunqiu.listbsdialogfragmenthelper.bean.BSDialogFgListItemBean;

import java.util.List;

/**
 * <pre>
 *     author : 明月春秋
 *     e-mail : xiyujieit@163.com
 *     time   : 2018/11/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<BSDialogFgListItemBean> mList;

    CustomAdapter(List<BSDialogFgListItemBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.rv_list_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.actvName.setText(mList.get(i).getText());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private AppCompatImageView acivIcon;
        private AppCompatTextView actvName;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            acivIcon = itemView.findViewById(R.id.iv_list_item_icon);
            actvName = itemView.findViewById(R.id.tv_list_item_name);
        }
    }
}
