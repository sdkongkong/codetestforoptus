package com.kevinsong.optus.codetestoptus.mvp;

import com.kevinsong.optus.codetestoptus.model.Venue;
import com.kevinsong.optus.codetestoptus.network.VenuesApiService;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class ScenarioTwoPresenter implements ScenarioTwoContract.Presenter {
    private final String TAG = ScenarioTwoPresenter.this.getClass().getSimpleName();
    ScenarioTwoContract.View mView;
    VenuesApiService mVenuesApiService;
    private CompositeSubscription mSubScriptions;

    public ScenarioTwoPresenter(ScenarioTwoContract.View view, VenuesApiService apiService) {
        mSubScriptions = new CompositeSubscription();
        mView = view;
        mVenuesApiService = apiService;

    }

    @Override
    public void startPresenter() {
    }

    @Override
    public void stopPresenter() {
        mSubScriptions.clear();
    }

    @Override
    public void getVenues() {
        Subscription subscription = mVenuesApiService.getVenues()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Venue>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(List<Venue> venues) {

                        mView.refreshSpinner(venues);
                    }
                });
        mSubScriptions.add(subscription);
    }

    @Override
    public CompositeSubscription getSubscriptions() {
        return mSubScriptions;
    }

}
