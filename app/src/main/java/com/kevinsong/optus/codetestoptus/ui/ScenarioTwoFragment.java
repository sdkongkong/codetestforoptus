package com.kevinsong.optus.codetestoptus.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.kevinsong.optus.codetestoptus.R;
import com.kevinsong.optus.codetestoptus.model.Venue;
import com.kevinsong.optus.codetestoptus.mvp.ScenarioTwoContract;
import com.kevinsong.optus.codetestoptus.mvp.ScenarioTwoPresenter;
import com.kevinsong.optus.codetestoptus.network.ApiClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * fragment for scenario two, it loads data from server and inflate the spinner
 */
public class ScenarioTwoFragment extends Fragment implements ScenarioTwoContract.View, View.OnClickListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.sp_venues)
    AppCompatSpinner mSpinnerVenues;
    @BindView(R.id.tv_car_distance)
    TextView mTextCarDistance;
    @BindView(R.id.tv_train_distance)
    TextView mTextTrainDistance;
    @BindView(R.id.btn_nav)
    TextView mButtonNav;
    private ScenarioTwoContract.Presenter mPresenter;
    private List<Venue> mVenues = new ArrayList<>();
    private Venue mSelectedVenue;
    public ScenarioTwoFragment() {
    }

    public static ScenarioTwoFragment newInstance() {
        ScenarioTwoFragment fragment = new ScenarioTwoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ScenarioTwoPresenter(this, ApiClient.getInstance().getVenuesApiService());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scenario_two, container, false);
        ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getVenues();
    }


    @Override
    public void onDestroy() {
        mPresenter.stopPresenter();
        super.onDestroy();
    }


    private void initView() {
        mButtonNav.setOnClickListener(this);
    }


    @Override
    public void setPresenter(ScenarioTwoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_nav:
                if (mSelectedVenue == null) {
                    Toast.makeText(getActivity(), "you didn't seleted any venue", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), LocationActivity.class);
                    intent.putExtra(LocationActivity.LATITUDE, mSelectedVenue.location.latitude);
                    intent.putExtra(LocationActivity.LONGITUDE, mSelectedVenue.location.longitude);
                    intent.putExtra(LocationActivity.LOCATION_NAME, mSelectedVenue.name);
                    getActivity().startActivity(intent);
                }
                break;
        }
    }

    @Override
    public void refreshSpinner(List<Venue> venues) {
        if (venues == null) {
            return;
        }
        mVenues = venues;
        List<String> list = new ArrayList<>();
        for (Venue venue : mVenues) {
            list.add(venue.name);
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerVenues.setAdapter(dataAdapter);
        mSpinnerVenues.setOnItemSelectedListener(this);
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        mSelectedVenue = mVenues.get(pos);
        if (mSelectedVenue != null && mSelectedVenue.fromcentral != null && mSelectedVenue.fromcentral.car != null) {
            mTextCarDistance.setText("car distance: " + mSelectedVenue.fromcentral.car);
        } else {
            mTextCarDistance.setText("car distance: " + "unknown");
        }
        if (mSelectedVenue != null && mSelectedVenue.fromcentral != null && mSelectedVenue.fromcentral.train != null) {
            mTextTrainDistance.setText("train distance: " + mSelectedVenue.fromcentral.train);
        } else {
            mTextTrainDistance.setText("train distance: " + "unknown");
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
