package com.mingyuechunqiu.listbsdialogfragmenthelper.ui.bottomSheetDialogFragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mingyuechunqiu.listbsdialogfragmenthelper.R;
import com.mingyuechunqiu.listbsdialogfragmenthelper.bean.BSDialogFgListItemBean;
import com.mingyuechunqiu.listbsdialogfragmenthelper.bean.BSDialogFgListOption;
import com.mingyuechunqiu.listbsdialogfragmenthelper.framework.OnListBSDfgClickItemListener;
import com.mingyuechunqiu.listbsdialogfragmenthelper.framework.OnListBSDfgClickHeaderListener;
import com.mingyuechunqiu.listbsdialogfragmenthelper.ui.adapter.BSDialogFragmentListAdapter;
import com.mingyuechunqiu.listbsdialogfragmenthelper.ui.view.DefaultHeaderView;
import com.mingyuechunqiu.listbsdialogfragmenthelper.ui.view.HeaderViewable;

/**
 * <pre>
 *     author : xyj
 *     e-mail : yujie.xi@ehailuo.com
 *     time   : 2018/11/14
 *     desc   : 底部列表对话框
 *              继承自BaseBSDialogFragment
 *     version: 1.0
 * </pre>
 */
public class ListBSDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    private BSDialogFgListOption mOption;
    private OnListBSDfgClickHeaderListener mTextListener;
    private OnListBSDfgClickItemListener mItemListener;
    private BSDialogFgListItemBean mItemBean;

    private HeaderViewable vHeaderViewable;
    private boolean isExtendBaseQuickAdapter;//标记是否是自定义适配器
    private View vSelected;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        View view = inflater.inflate(R.layout.bs_dialog_fragment_list, container, false);
        FrameLayout flHeaderContainer = view.findViewById(R.id.fl_bs_dfg_header_container);
        if (mOption != null && mOption.getHeaderViewable() != null) {
            vHeaderViewable = mOption.getHeaderViewable();
        } else {
            vHeaderViewable = DefaultHeaderView.getInstance(getContext());
        }
        flHeaderContainer.addView(vHeaderViewable.getHeaderView());

        RecyclerView rvList = view.findViewById(R.id.rv_bs_dfg_list);

        vHeaderViewable.getCancelView().setOnClickListener(this);
        vHeaderViewable.getTitleView().setOnClickListener(this);
        vHeaderViewable.getConfirmView().setOnClickListener(this);
        setOption(vHeaderViewable.getCancelView(), vHeaderViewable.getTitleView(),
                vHeaderViewable.getConfirmView(), rvList);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mOption = null;
        mTextListener = null;
        mItemListener = null;
        mItemBean = null;
        vHeaderViewable = null;
        isExtendBaseQuickAdapter = false;
        vSelected = null;
    }

    @Override
    public void onClick(View v) {
        //Resource IDs cannot be used in a switch statement in Android library modules
        int id = v.getId();
        if (id == vHeaderViewable.getCancelView().getId()) {
            dismiss();
            if (mTextListener != null) {
                mTextListener.onClickCancel(this, mItemBean);
            }
        } else if (id == vHeaderViewable.getTitleView().getId()) {
            if (mTextListener != null) {
                mTextListener.onClickTitle(this, mItemBean);
            }
        } else if (id == vHeaderViewable.getConfirmView().getId()) {
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

    public OnListBSDfgClickHeaderListener getOnListBSDialogFragmentListener() {
        return mTextListener;
    }

    public void setOnListBSDialogFragmentListener(OnListBSDfgClickHeaderListener textListener) {
        mTextListener = textListener;
    }

    public OnListBSDfgClickItemListener getOnListBSDfgClickItemListener() {
        return mItemListener;
    }

    public void setOnListBSDfgClickItemListener(OnListBSDfgClickItemListener itemListener) {
        mItemListener = itemListener;
    }

    /**
     * 创建列表底部对话框实例，选用默认头部view
     *
     * @return 返回创建的对话框实例
     */
    public static ListBSDialogFragment newInstance() {
        return newInstance(null);
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
     * @param vCancel  取消按钮
     * @param vTitle   标题按钮
     * @param vConfirm 确认按钮
     * @param rvList   列表
     */
    private void setOption(View vCancel, View vTitle, View vConfirm, RecyclerView rvList) {
        if (mOption == null) {
            mOption = new BSDialogFgListOption.Builder().build();
        }
        if (vCancel instanceof AppCompatTextView) {
            AppCompatTextView actvCancel = (AppCompatTextView) vCancel;
            if (!TextUtils.isEmpty(mOption.getCancelText())) {
                actvCancel.setText(mOption.getCancelText());
            }
            if (mOption.getCancelColor() != 0) {
                actvCancel.setTextColor(mOption.getCancelColor());
            }
        }
        if (vTitle instanceof AppCompatTextView) {
            AppCompatTextView actvTitle = (AppCompatTextView) vTitle;
            if (!TextUtils.isEmpty(mOption.getTitleText())) {
                actvTitle.setText(mOption.getTitleText());
            }
            if (mOption.getTitleColor() != 0) {
                actvTitle.setTextColor(mOption.getTitleColor());
            }
        }
        if (vConfirm instanceof AppCompatTextView) {
            AppCompatTextView actvConfirm = (AppCompatTextView) vConfirm;
            if (!TextUtils.isEmpty(mOption.getConfirmText())) {
                actvConfirm.setText(mOption.getConfirmText());
            }
            if (mOption.getConfirmColor() != 0) {
                actvConfirm.setTextColor(mOption.getConfirmColor());
            }
        }
        if (!mOption.isCancelVisible()) {
            vCancel.setVisibility(View.GONE);
        }
        if (!mOption.isTitleVisible()) {
            vTitle.setVisibility(View.GONE);
        }
        if (!mOption.isConfirmVisible()) {
            vConfirm.setVisibility(View.GONE);
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
