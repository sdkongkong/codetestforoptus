package com.kevinsong.optus.codetestoptus.mvp;

import com.kevinsong.optus.codetestoptus.model.Venue;

import java.util.List;

import rx.subscriptions.CompositeSubscription;


public interface ScenarioTwoContract {
    interface View extends BaseView<Presenter> {
        void refreshSpinner(List<Venue> venues);

        void showToast(String msg);
    }

    interface Presenter extends BasePresenter {

        void getVenues();

        CompositeSubscription getSubscriptions();
    }
}
