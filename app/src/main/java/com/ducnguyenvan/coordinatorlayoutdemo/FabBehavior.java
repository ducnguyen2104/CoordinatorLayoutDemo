package com.ducnguyenvan.coordinatorlayoutdemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class FabBehavior extends CoordinatorLayout.Behavior {
    public FabBehavior() {
        super();
    }

    public FabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof RecyclerView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        if(child instanceof FloatingActionButton && dependency instanceof RecyclerView) {
            final FloatingActionButton fab = (FloatingActionButton)child;
            RecyclerView rv = (RecyclerView)dependency;

            rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    if( dy > 0) {
                        //scroll up
                        fab.setVisibility(View.GONE);
                    }
                    else {
                        fab.setVisibility(View.VISIBLE);
                    }
                    super.onScrolled(recyclerView, dx, dy);
                }
            });
        }
        return false;
    }
}
