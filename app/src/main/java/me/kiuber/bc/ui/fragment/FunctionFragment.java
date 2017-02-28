package me.kiuber.bc.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import me.kiuber.bc.R;

/**
 * Created by Kiuber on 2017/2/27 0027.
 */

public class FunctionFragment extends Fragment implements View.OnClickListener {

    private View view;
    private String[] urls = {
            "http://huogeng.kuaizhan.com/44/96/p3719277241873c",
            "http://huogeng.kuaizhan.com/13/37/p37533544583579",
            "http://school.schoolfuwu.com/clubv2/forums/V4NiB1hO2kAxJXxe",
            "http://huogeng.kuaizhan.com/53/62/p389640129f6271",
            "http://huogeng.kuaizhan.com/shop/commodity/57c8d391882a2a79c4cd6dd4",
            "http://huogeng.kuaizhan.com/14/90/p38623723221ee9",
            "http://school.schoolfuwu.com/clubv2/forums/V4h5303NskRFZx7r",
            "http://huogeng.kuaizhan.com/66/8/p3839598725d4bd"
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_function, null);
        initView();
        return view;
    }

    private void initView() {
        GridView mGvMenu = (GridView) view.findViewById(R.id.gv_menu);
//        new String[]{"兼职", "外卖", "论坛", "快递", "出行", "BC课堂", "二手市场", "BC游戏"};
        view.findViewById(R.id.tv_jianzhi).setOnClickListener(this);
        view.findViewById(R.id.tv_waimai).setOnClickListener(this);
        view.findViewById(R.id.tv_luntan).setOnClickListener(this);
        view.findViewById(R.id.tv_kuaidi).setOnClickListener(this);
        view.findViewById(R.id.tv_chuxing).setOnClickListener(this);
        view.findViewById(R.id.tv_ketang).setOnClickListener(this);
        view.findViewById(R.id.tv_ershou).setOnClickListener(this);
        view.findViewById(R.id.tv_youxi).setOnClickListener(this);
        //mGvMenu.setAdapter(new MyGridViewMenuAdapter());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_jianzhi:
                openWeb(0);
                break;
            case R.id.tv_waimai:
                openWeb(1);
                break;
            case R.id.tv_luntan:
                openWeb(2);
                break;
            case R.id.tv_kuaidi:
                openWeb(3);
                break;
            case R.id.tv_chuxing:
                openWeb(4);
                break;
            case R.id.tv_ketang:
                openWeb(5);
                break;
            case R.id.tv_ershou:
                openWeb(6);
                break;
            case R.id.tv_youxi:
                openWeb(7);
                break;
        }
    }

    private void openWeb(int i) {
        Uri uri = Uri.parse(urls[i]);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private class MyGridViewMenuAdapter extends BaseAdapter {
        private String[] names = new String[]{"兼职", "外卖", "论坛", "快递", "出行", "BC课堂", "二手市场", "BC游戏"};
        private int[] pics = new int[]{R.drawable.ic_menu_jianzhi, R.drawable.ic_menu_waimai
                , R.drawable.ic_menu_luntan, R.drawable.ic_menu_kuaidi, R.drawable.ic_menu_chuxing
                , R.drawable.ic_menu_ketang, R.drawable.ic_menu_ershou, R.drawable.ic_menu_youxi};
        private String[] bgs = new String[]{"#ff9a00", "#ed4848", "#ced300", "#43d2d2", "#3b65f4", "#0097ff", "#06c704", "#ad2ef4"};


        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (viewHolder == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(getContext(), R.layout.item_menu, null);
                viewHolder.initView(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.initData(position);
            return convertView;
        }

        class ViewHolder {
            private TextView mTvName;
            private ImageView mIvPic;
            private CircleImageView mCivBg;

            private void initView(View view) {
                mTvName = (TextView) view.findViewById(R.id.tv_menu_name);
                mIvPic = (ImageView) view.findViewById(R.id.iv_menu_pic);
                mCivBg = (CircleImageView) view.findViewById(R.id.civ_bg);
            }

            private void initData(int pos) {
                mTvName.setText(names[pos]);
                mIvPic.setBackgroundResource(pics[pos]);
                int i = Color.parseColor(bgs[pos]);
                ColorDrawable colorDrawable = new ColorDrawable(i);
                mCivBg.setImageDrawable(colorDrawable);
            }
        }
    }
}
