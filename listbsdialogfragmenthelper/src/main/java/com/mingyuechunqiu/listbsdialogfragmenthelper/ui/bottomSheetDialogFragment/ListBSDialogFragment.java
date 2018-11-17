package com.mingyuechunqiu.listbsdialogfragmenthelper.ui.bottomSheetDialogFragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mingyuechunqiu.listbsdialogfragmenthelper.R;
import com.mingyuechunqiu.listbsdialogfragmenthelper.bean.BSDialogFgListItemBean;
import com.mingyuechunqiu.listbsdialogfragmenthelper.bean.BSDialogFgListOption;
import com.mingyuechunqiu.listbsdialogfragmenthelper.framework.OnListBSDfgClickItemListener;
import com.mingyuechunqiu.listbsdialogfragmenthelper.framework.OnListBSDfgClickTextListener;
import com.mingyuechunqiu.listbsdialogfragmenthelper.ui.adapter.BSDialogFragmentListAdapter;

/**
 * <pre>
 *     author : xyj
 *     e-mail : yujie.xi@ehailuo.com
 *     time   : 2018/11/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ListBSDialogFragment extends BaseBSDialogFragment implements View.OnClickListener {

    private BSDialogFgListOption mOption;
    private OnListBSDfgClickTextListener mTextListener;
    private OnListBSDfgClickItemListener mItemListener;
    private BSDialogFgListItemBean mItemBean;

    private boolean isExtendBaseQuickAdapter;//标记是否是自定义适配器
    private View vSelected;

    @Override
    protected View initView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup) {
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        View view = layoutInflater.inflate(R.layout.bs_dialog_fragment_list, viewGroup, false);
        AppCompatTextView actvCancel = view.findViewById(R.id.tv_bs_dfg_cancel);
        AppCompatTextView actvTitle = view.findViewById(R.id.tv_bs_dfg_title);
        AppCompatTextView actvConfirm = view.findViewById(R.id.tv_bs_dfg_confirm);
        RecyclerView rvList = view.findViewById(R.id.rv_bs_dfg_list);

        actvCancel.setOnClickListener(this);
        actvTitle.setOnClickListener(this);
        actvConfirm.setOnClickListener(this);
        setOption(actvCancel, actvTitle, actvConfirm, rvList);
        return view;
    }

    @Override
    protected void release() {
        mOption = null;
        mTextListener = null;
        mItemListener = null;
        mItemBean = null;
        isExtendBaseQuickAdapter = false;
        vSelected = null;
    }

    @Override
    public void onClick(View v) {
        //Resource IDs cannot be used in a switch statement in Android library modules
        int id = v.getId();
        if (id == R.id.tv_bs_dfg_cancel) {
            dismiss();
            if (mTextListener != null) {
                mTextListener.onClickCancel(this, mItemBean);
            }
        } else if (id == R.id.tv_bs_dfg_title) {
            if (mTextListener != null) {
                mTextListener.onClickTitle(this, mItemBean);
            }
        } else if (id == R.id.tv_bs_dfg_confirm) {
            if (mTextListener != null) {
                //因为自定义adapter可能不是BaseQuickAdapter的子类，无法检测点击事件，
                //所以未选择确认事件只针对BaseQuickAdapter的子类
                if (isExtendBaseQuickAdapter && mItemBean == null) {
                    mTextListener.onClickConfirmWithoutSelecting(this);
                } else {
                    dismiss();
                    mTextListener.onClickConfirm(this, mItemBean);
                }
            }
        }
    }

    public OnListBSDfgClickTextListener getOnListBSDialogFragmentListener() {
        return mTextListener;
    }

    public void setOnListBSDialogFragmentListener(OnListBSDfgClickTextListener textListener) {
        mTextListener = textListener;
    }

    public OnListBSDfgClickItemListener getOnListBSDfgClickItemListener() {
        return mItemListener;
    }

    public void setOnListBSDfgClickItemListener(OnListBSDfgClickItemListener itemListener) {
        mItemListener = itemListener;
    }

    /**
     * 创建列表底部对话框实例
     *
     * @param option 列表选项
     * @return 返回创建的对话框实例
     */
    public static ListBSDialogFragment newInstance(BSDialogFgListOption option) {
        ListBSDialogFragment fragment = new ListBSDialogFragment();
        fragment.mOption = option;
        return fragment;
    }

    /**
     * 根据列表选项进行参数设置
     *
     * @param actvCancel  取消按钮
     * @param actvTitle   标题按钮
     * @param actvConfirm 确认按钮
     * @param rvList      列表
     */
    private void setOption(AppCompatTextView actvCancel, AppCompatTextView actvTitle, AppCompatTextView actvConfirm, RecyclerView rvList) {
        if (mOption == null) {
            return;
        }
        if (!TextUtils.isEmpty(mOption.getCancelText())) {
            actvCancel.setText(mOption.getCancelText());
        }
        if (!TextUtils.isEmpty(mOption.getTitleText())) {
            actvTitle.setText(mOption.getTitleText());
        }
        if (!TextUtils.isEmpty(mOption.getConfirmText())) {
            actvConfirm.setText(mOption.getConfirmText());
        }
        if (mOption.getCancelColor() != 0) {
            actvCancel.setTextColor(mOption.getCancelColor());
        }
        if (mOption.getTitleColor() != 0) {
            actvTitle.setTextColor(mOption.getTitleColor());
        }
        if (mOption.getConfirmColor() != 0) {
            actvConfirm.setTextColor(mOption.getConfirmColor());
        }
        if (mOption.getOnListBSDfgClickTextListener() != null) {
            mTextListener = mOption.getOnListBSDfgClickTextListener();
        }
        if (mOption.getLayoutManager() != null) {
            rvList.setLayoutManager(mOption.getLayoutManager());
        } else {
            rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        boolean isSetAdapter = false;
        if (mOption.getAdapter() != null) {
            rvList.setAdapter(mOption.getAdapter());
            isSetAdapter = true;
        } else {
            if (mOption.getList() != null) {
                rvList.setAdapter(new BSDialogFragmentListAdapter(
                        R.layout.rv_bs_dfg_list_item, mOption.getList()));
                isSetAdapter = true;
            }
        }
        isExtendBaseQuickAdapter = rvList.getAdapter() instanceof BaseQuickAdapter;
        if (isSetAdapter && isExtendBaseQuickAdapter) {
            if (getContext() != null) {
                rvList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
            }
            ((BaseQuickAdapter) rvList.getAdapter()).setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    if (vSelected != null) {
                        vSelected.setSelected(false);
                    }
                    vSelected = view;
                    vSelected.setSelected(true);
                    mItemBean = mOption.getList().get(position);
                    if (mItemListener != null) {
                        mItemListener.onClickListItem(adapter, view, position, mItemBean);
                    }
                }
            });
            ((BaseQuickAdapter) rvList.getAdapter()).setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
                @Override
                public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                    if (mItemListener != null) {
                        return mItemListener.onLongClickListItem(adapter, view, position, mOption.getList().get(position));
                    }
                    return false;
                }
            });
        }
    }
}
