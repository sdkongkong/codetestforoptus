package com.kevinsong.optus.codetestoptus.mvp;

import com.kevinsong.optus.codetestoptus.model.Venue;
import com.kevinsong.optus.codetestoptus.network.VenuesApiService;
import com.kevinsong.optus.codetestoptus.utils.RxUnitTestTools;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by Kevin Song on 28/12/2016.
 */
public class ScenarioTwoPresenterTest {

    @Mock
    ScenarioTwoContract.View mView;

    @Mock
    VenuesApiService mVenuesApiService;

    ScenarioTwoContract.Presenter mPresenter;

    @Captor
    ArgumentCaptor<List<Venue>> mCaptor;

    @Before
    public void setUp() {
        initMocks(this);

        mPresenter = new ScenarioTwoPresenter(mView, mVenuesApiService);

    }

    @Test
    public void startPresenter() throws Exception {

    }

    @Test
    public void stopPresenter() throws Exception {
        mPresenter.stopPresenter();
        assertEquals(false, mPresenter.getSubscriptions().hasSubscriptions());
    }

    @Test
    public void getVenues() throws Exception {
        List<Venue> venues = new ArrayList<>();
        Venue venue = new Venue();
        venue.name = "sydney";
        venue.id = 1;
        venues.add(venue);
        RxUnitTestTools.openRxTools();
        when(mVenuesApiService.getVenues()).thenReturn(Observable.just(venues));
        mPresenter.getVenues();
        verify(mView).refreshSpinner(mCaptor.capture());
        List<Venue> mCaptorValue = mCaptor.getValue();
        assertEquals("sydney", mCaptorValue.get(0).name);
    }

    @After
    public void clearUp() {

    }

}