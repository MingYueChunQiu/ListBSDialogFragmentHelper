package com.mingyuechunqiu.listbsdialogfragmenthelperproject;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mingyuechunqiu.listbsdialogfragmenthelper.bean.BSDialogFgListItemBean;
import com.mingyuechunqiu.listbsdialogfragmenthelper.bean.BSDialogFgListOption;
import com.mingyuechunqiu.listbsdialogfragmenthelper.framework.OnListBSDfgClickItemListener;
import com.mingyuechunqiu.listbsdialogfragmenthelper.framework.OnListBSDfgClickTextListener;
import com.mingyuechunqiu.listbsdialogfragmenthelper.ui.bottomSheetDialogFragment.ListBSDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatButton acbtnDefault = findViewById(R.id.btn_default);
        AppCompatButton acbtnCustom = findViewById(R.id.btn_custom);
        AppCompatButton acbtnCustomHeader = findViewById(R.id.btn_custom_header);
        acbtnDefault.setOnClickListener(this);
        acbtnCustom.setOnClickListener(this);
        acbtnCustomHeader.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_default:
                BSDialogFgListOption listOption = new BSDialogFgListOption.Builder()
                        .setList(getList())
                        .setOnListBSDfgClickTextListener(new OnListBSDfgClickTextListener() {
                            @Override
                            public void onClickCancel(@NonNull ListBSDialogFragment fragment, BSDialogFgListItemBean itemBean) {
                                if (itemBean == null) {
                                    Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "取消" + itemBean.getText(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onClickTitle(@NonNull ListBSDialogFragment fragment, BSDialogFgListItemBean itemBean) {
                                if (itemBean == null) {
                                    Toast.makeText(MainActivity.this, "标题", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "标题" + itemBean.getText(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onClickConfirm(@NonNull ListBSDialogFragment fragment, @NonNull BSDialogFgListItemBean itemBean) {
                                Toast.makeText(MainActivity.this, "确认" + itemBean.getText(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onClickConfirmWithoutSelecting(@NonNull ListBSDialogFragment fragment) {
                                Toast.makeText(MainActivity.this, "未选择确认", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setOnListBSDfgClickItemListener(new OnListBSDfgClickItemListener() {
                            @Override
                            public void onClickListItem(BaseQuickAdapter adapter, View view, int position, BSDialogFgListItemBean itemBean) {
                                Toast.makeText(MainActivity.this, "点击" + itemBean.getText(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public boolean onLongClickListItem(BaseQuickAdapter adapter, View view, int position, BSDialogFgListItemBean itemBean) {
                                Toast.makeText(MainActivity.this, "长按" + itemBean.getText(), Toast.LENGTH_SHORT).show();
                                return false;
                            }
                        })
                        .build();
                ListBSDialogFragment listFragment = ListBSDialogFragment.newInstance(this, listOption);
                listFragment.show(getSupportFragmentManager(), ListBSDialogFragment.class.getSimpleName());
                break;
            case R.id.btn_custom:
                BSDialogFgListOption customOption = new BSDialogFgListOption.Builder()
                        .setCancelColor(Color.BLUE)
                        .setConfirmColor(Color.CYAN)
                        .setTitleColor(Color.GREEN)
                        .setCancelVisible(false)
                        .setLayoutManager(new GridLayoutManager(this, 2))
                        .setAdapter(new CustomAdapter(getList()))
                        .setOnListBSDfgClickTextListener(new OnListBSDfgClickTextListener() {
                            @Override
                            public void onClickCancel(@NonNull ListBSDialogFragment fragment, BSDialogFgListItemBean itemBean) {
                                if (itemBean == null) {
                                    Toast.makeText(MainActivity.this, "自定义取消", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "自定义取消" + itemBean.getText(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onClickTitle(@NonNull ListBSDialogFragment fragment, BSDialogFgListItemBean itemBean) {
                                if (itemBean == null) {
                                    Toast.makeText(MainActivity.this, "自定义标题", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "自定义标题" + itemBean.getText(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onClickConfirm(@NonNull ListBSDialogFragment fragment, @NonNull BSDialogFgListItemBean itemBean) {
                                Toast.makeText(MainActivity.this, "自定义确认", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onClickConfirmWithoutSelecting(@NonNull ListBSDialogFragment fragment) {
                                Toast.makeText(MainActivity.this, "自定义未选择确认", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
                ListBSDialogFragment customFragment = ListBSDialogFragment.newInstance(this, customOption);
                customFragment.show(getSupportFragmentManager(), ListBSDialogFragment.class.getSimpleName());
                break;
            case R.id.btn_custom_header:
                BSDialogFgListOption headerOption = new BSDialogFgListOption.Builder()
                        .setCancelColor(Color.BLUE)
                        .setConfirmColor(Color.CYAN)
                        .setTitleColor(Color.GREEN)
                        .setLayoutManager(new GridLayoutManager(this, 2))
                        .setAdapter(new CustomAdapter(getList()))
                        .setOnListBSDfgClickTextListener(new OnListBSDfgClickTextListener() {
                            @Override
                            public void onClickCancel(@NonNull ListBSDialogFragment fragment, BSDialogFgListItemBean itemBean) {
                                if (itemBean == null) {
                                    Toast.makeText(MainActivity.this, "自定义取消", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "自定义取消" + itemBean.getText(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onClickTitle(@NonNull ListBSDialogFragment fragment, BSDialogFgListItemBean itemBean) {
                                if (itemBean == null) {
                                    Toast.makeText(MainActivity.this, "自定义标题", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "自定义标题" + itemBean.getText(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onClickConfirm(@NonNull ListBSDialogFragment fragment, @NonNull BSDialogFgListItemBean itemBean) {
                                Toast.makeText(MainActivity.this, "自定义确认", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onClickConfirmWithoutSelecting(@NonNull ListBSDialogFragment fragment) {
                                Toast.makeText(MainActivity.this, "自定义未选择确认", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
                ListBSDialogFragment headerFragment = ListBSDialogFragment.newInstance(
                        this, headerOption, new CustomHeaderView(this));
                headerFragment.show(getSupportFragmentManager(), ListBSDialogFragment.class.getSimpleName());
                break;
            default:
                break;
        }
    }

    private List<BSDialogFgListItemBean> getList() {
        List<BSDialogFgListItemBean> list = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            BSDialogFgListItemBean bean = new BSDialogFgListItemBean();
            bean.setText("第" + i + "项");
            list.add(bean);
        }
        return list;
    }
}
