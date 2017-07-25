package com.sion.myfirstrepository;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Sion Wang on 2017/7/25.
 * Paint example
 */

public class PaintFragment extends BaseFragment {
    private static final String TAG = "PaintFragment";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_paint, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
