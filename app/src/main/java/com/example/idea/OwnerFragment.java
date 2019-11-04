package com.example.idea;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

public class OwnerFragment extends Fragment implements View.OnClickListener{

    private SwipePlaceHolderView mSwipeView;

    private View v;
    Context context = getContext();

    public OwnerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_home, container, false);
        mSwipeView = v.findViewById(R.id.swipeView);

        int bottomMargin = Utils.dpToPx(160);
        WindowManager windowManager = getActivity().getWindowManager();
        Point windowSize = Utils.getDisplaySize(windowManager);

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth(windowSize.x)
                        .setViewHeight(windowSize.y - bottomMargin)
                        .setViewGravity(Gravity.TOP)
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_right_view)   
                        .setSwipeOutMsgLayoutId(R.layout.swipe_left_view));

        for(Design design : Utils.loadDesigns(getActivity())){
            mSwipeView.addView(new DesignCard(getActivity(), design, mSwipeView));
        }

        v.findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        v.findViewById(R.id.addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });

        return v;
    }

    public interface OnNextClickListener {
        void OnOwnerFragmentNextClick(OwnerFragment fragment);
    }

    OnNextClickListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnNextClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement OnOwnerFragmentNextClick");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void onClick(View view) {
        if (listener != null) {
            listener.OnOwnerFragmentNextClick(this);
        }
    }

}