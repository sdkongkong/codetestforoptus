package com.kevinsong.optus.codetestoptus.ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kevinsong.optus.codetestoptus.R;
import com.kevinsong.optus.codetestoptus.R2;
import com.kevinsong.optus.codetestoptus.adapters.PlaceHolderPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;


public class ScenarioOneFragment extends Fragment {


    @BindView(R2.id.tv_item1)
    TextView mTextItem1;

    @BindView(R2.id.tv_item2)
    TextView mTextItem2;

    @BindView(R2.id.tv_item3)
    TextView mTextItem3;

    @BindView(R2.id.tv_item4)
    TextView mTextItem4;

    @BindView(R2.id.tv_item5)
    TextView mTextItem5;

    @BindView(R2.id.tv_changing_content)
    TextView mTextContentChanging;

    @BindView(R2.id.btn_color_red)
    Button mButtonRed;

    @BindView(R2.id.btn_color_blue)
    Button mButtonBlue;

    @BindView(R2.id.btn_color_green)
    Button mButtonGreen;

    @BindView(R2.id.ll_color_area)
    LinearLayout mLinerLayoutCorlor;

    @BindView(R2.id.vp_numbers)
    ViewPager mViewPagerNumbers;

    @BindView(R2.id.indicator)
    CircleIndicator mIndicator;

    public ScenarioOneFragment() {
    }


    public static ScenarioOneFragment newInstance() {
        ScenarioOneFragment fragment = new ScenarioOneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scenario_one, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }


    private void initView() {
        TextClickListener textListener = new TextClickListener();
        ButtonClickListener buttonListener = new ButtonClickListener();
        mTextItem1.setOnClickListener(textListener);
        mTextItem2.setOnClickListener(textListener);
        mTextItem3.setOnClickListener(textListener);
        mTextItem4.setOnClickListener(textListener);
        mTextItem5.setOnClickListener(textListener);
        mButtonBlue.setOnClickListener(buttonListener);
        mButtonGreen.setOnClickListener(buttonListener);
        mButtonRed.setOnClickListener(buttonListener);
        PlaceHolderPagerAdapter adapter = new PlaceHolderPagerAdapter(getActivity().getSupportFragmentManager());
        mViewPagerNumbers.setAdapter(adapter);
        mIndicator.setViewPager(mViewPagerNumbers);
    }


    class TextClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TextView tv = (TextView) view;
            String content = tv.getText().toString();
            mTextContentChanging.setText(content);
        }
    }

    class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_color_blue:
                    mLinerLayoutCorlor.setBackground(new ColorDrawable(Color.BLUE));
                    break;
                case R.id.btn_color_green:
                    mLinerLayoutCorlor.setBackground(new ColorDrawable(Color.GREEN));
                    break;
                case R.id.btn_color_red:
                    mLinerLayoutCorlor.setBackground(new ColorDrawable(Color.RED));
                    break;
            }
        }
    }
}
