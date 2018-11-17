# ListBSDialogFragmentHelper
实现自定义底部列表对话框
因为需要，为了方便，构建了一个可以自定义扩展的底部列表对话框，可以应付大部分场景。
效果图如下：
1.默认实现：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181117142518867.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NsMjAxOGdvZA==,size_16,color_FFFFFF,t_70)
2.自定义列表实现
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181117143811476.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NsMjAxOGdvZA==,size_16,color_FFFFFF,t_70)
3.自定义头部和列表实现
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181117171338834.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3NsMjAxOGdvZA==,size_16,color_FFFFFF,t_70)
## 一.可实现功能
1.默认可实现通用列表对话框，上方HeaderView包括取消、标题、确认三个文本按钮，下方为RecyclerView，实现各种布局显示。
2.头部HeaderView支持自定义，通过实现HeaderViewable接口，进行可扩展。
3.下方RecyclerView支持自定义LayoutManager和适配器，实现自己想要的效果。
4.支持取消、标题、确认view的点击事件，传入继承BRVAH的BaseQuickAdapter，可以直接获取库实现的点击和长按事件回调，否则需要自己设置点击和长按事件监听。
## 二.添加依赖
1.在项目的build.gradle中添加

```
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```
2.在module的build.gradle中添加

```
dependencies {
	//必须，继承BottomSheetDialogFragment
	implementation 'com.android.support:design:28.0.0'
	//必须，库默认使用BRVAH的适配器，可以选择传入自定义的adapter
   	implementation 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.40'
   	
	implementation 'com.github.MingYueChunQiu:ListBSDialogFragmentHelper:0.1.1'
	}
```
## 三.功能使用
1.创建BSDialogFgListOption，存储底部列表对话框的相关配置信息
2.如果需要自定义头部view，创建实现HeaderViewable接口的实例
3.调用ListBSDialogFragment.newInstance，获取底部列表对话框实例并显示。
4.简单demo示例部分片段，详细信息可在底部GitHub中进行查看

```
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
                        .setConfirmText("自定义确认")
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
```
## 四.相关类介绍
### 1.BSDialogFgListItemBean
库默认使用的BRVAH的adapter，列表实现的每项item数据类型都为BSDialogFgListItemBean，里面包含text（显示文本）和extra（用于用户传递的一些额外数据）

```
List<BSDialogFgListItemBean> list = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            BSDialogFgListItemBean bean = new BSDialogFgListItemBean();
            bean.setText("第" + i + "项");
            list.add(bean);
        }
        return list;
```

### 2.BSDialogFgListOption、
底部列表对话框设置的配置信息类，采用链式调用，可自行配置列表布局及显示效果。
相关属性如下：

```
private String titleText;//标题文本
    private String cancelText;//取消文本
    private String confirmText;//确认文本
    private int titleColor;//标题文本颜色
    private int cancelColor;//取消文本颜色
    private int confirmColor;//确认文本颜色
    private boolean isCancelVisible = true;//取消文本是否可见
    private boolean isTitleVisible = true;//标题是否可见
    private boolean isConfirmVisible = true;//确认文本是否可见
    private List<BSDialogFgListItemBean> list;//列表数据
    private RecyclerView.LayoutManager layoutManager;//列表布局管理器
    private RecyclerView.Adapter adapter;//列表适配器
    private OnListBSDfgClickTextListener textListener;//列表对话框文本监听器
    private OnListBSDfgClickItemListener itemListener;//列表对话框item监听器
```
### 3.OnListBSDfgClickHeaderListener
底部列表对话框头部view的取消、标题、确认view的点击事件监听器

```
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
```
### 4.OnListBSDfgClickItemListener
列表item的点击监听器，如果是继承自BaseQuickAdapter的适配器，默认会回调此监听器，如果不是，需要用户自己实现点击事件监听

```
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
```
### 5.BSDialogFragmentListAdapter
库默认实现的适配器，继承自BRVAH的BaseQuickAdapter，实现默认布局的点击和长按事件监听
### 6.BaseBSDialogFragment
所有BottomSheet对话框的父类，提供可继承的showToast和资源初始化和销毁方法，及回调接口

```
private Toast mToast;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mToast = null;
        release();
    }

    /**
     * 初始化控件资源
     *
     * @param inflater  布局填充器
     * @param container 父布局容器
     * @return 返回填充布局
     */
    protected abstract View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    /**
     * 释放资源
     */
    protected abstract void release();

    /**
     * 显示提示信息
     *
     * @param hint 提示文本
     */
    protected void showToast(Context context, String hint) {
        if (mToast == null) {
            mToast = Toast.makeText(context, hint, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(hint);
        }
        mToast.show();
    }

    /**
     * 根据资源id显示提示信息
     *
     * @param stringResourceId 提示文本资源id
     */
    protected void showToast(Context context, int stringResourceId) {
        showToast(context,getString(stringResourceId));
    }

    public interface Callback {

        /**
         * 由Activity实现的回调方法
         *
         * @param fragment 回调的fragment
         * @param bundle   传递的参数值
         */
        void onCall(BottomSheetDialogFragment fragment, Bundle bundle);

    }
```
### 7.ListBSDialogFragment
继承自BaseBSDialogFragment，真正实现底部列表对话框功能
通过以下两个静态方法，获取对话框实例并使用
```
/**
     * 创建列表底部对话框实例，选用默认头部view
     *
     * @param context 上下文
     * @param option  列表选项
     * @return 返回创建的对话框实例
     */
    public static ListBSDialogFragment newInstance(Context context, BSDialogFgListOption option) {
        return newInstance(context, option, null);
    }

    /**
     * 创建列表底部对话框实例
     *
     * @param context        上下文
     * @param option         列表选项
     * @param headerViewable 自定义的头部view
     * @return 返回创建的对话框实例
     */
    public static ListBSDialogFragment newInstance(Context context,
                                                   BSDialogFgListOption option,
                                                   HeaderViewable headerViewable) {
        ListBSDialogFragment fragment = new ListBSDialogFragment();
        fragment.mOption = option;
        if (headerViewable == null) {
            headerViewable = DefaultHeaderView.getInstance(context);
        }
        fragment.vHeaderViewable = headerViewable;
        return fragment;
    }
```
## 总结
上述功能基本实现了各种底部列表对话框所需要的功能，更具体的代码信息请到GitHub或码云上查阅，GitHub地址https://github.com/MingYueChunQiu/ListBSDialogFragmentHelper.git，欢迎大家审阅，如果有什么更好的建议欢迎反馈，如果觉得还可以帮忙GitHub点个star，谢谢！
