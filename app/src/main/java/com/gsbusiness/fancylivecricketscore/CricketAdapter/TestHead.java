package com.gsbusiness.fancylivecricketscore.CricketAdapter;

import android.content.Context;
import android.view.View;

import com.gsbusiness.fancylivecricketscore.R;
import com.mindorks.placeholderview.LoadMoreCallbackBinder;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipeDirection;
import com.mindorks.placeholderview.SwipeDirectionalView;
import com.mindorks.placeholderview.SwipeDirectionalViewBinder;
import com.mindorks.placeholderview.SwipePlaceHolderView;

public class TestHead {
    public Context mContext;

    public void onResolved() {
    }

    public class LoadMoreViewBinder extends ViewBinder implements LoadMoreCallbackBinder<TestHead> {
        public void bindLoadMore(TestHead cricitestheadview) {
        }

        public LoadMoreViewBinder(TestHead cricitestheadview) {
            super(cricitestheadview);
        }
    }

    public class ViewBinder extends com.mindorks.placeholderview.ViewBinder<TestHead, View> {

        public void bindClick(TestHead cricitestheadview, View view) {
        }


        public void bindLongClick(TestHead cricitestheadview, View view) {
        }


        public void bindViewPosition(TestHead cricitestheadview, int i) {
        }


        public void bindViews(TestHead cricitestheadview, View view) {
        }

        public ViewBinder(TestHead cricitestheadview) {
            super(cricitestheadview, R.layout.ic_player, false);
        }


        public void resolveView(TestHead cricitestheadview) {
            cricitestheadview.onResolved();
        }


        @Override
        public void recycleView() {
            TestHead cricitestheadview = (TestHead) getResolver();
        }


        @Override
        public void unbind() {
            TestHead cricitestheadview = (TestHead) getResolver();
            boolean isNullable = isNullable();
            if (cricitestheadview != null && isNullable) {
                cricitestheadview.mContext = null;
                setResolver(null);
                setAnimationResolver(null);
            }
        }
    }

    public class ExpandableViewBinder extends com.mindorks.placeholderview.ExpandableViewBinder<TestHead, View> {

        @Override
        @Deprecated
        public void bindAnimation(int i, int i2, View view) {
        }


        @Override
        public void bindChildPosition(int i) {
        }


        public void bindClick(TestHead cricitestheadview, View view) {
        }


        public void bindCollapse(TestHead cricitestheadview) {
        }


        public void bindExpand(TestHead cricitestheadview) {
        }


        public void bindLongClick(TestHead cricitestheadview, View view) {
        }


        @Override
        public void bindParentPosition(int i) {
        }


        public void bindViewPosition(TestHead cricitestheadview, int i) {
        }


        public void bindViews(TestHead cricitestheadview, View view) {
        }


        @Override
        @Deprecated
        public void unbind() {
        }

        public ExpandableViewBinder(TestHead cricitestheadview) {
            super(cricitestheadview, R.layout.ic_player, false, false, false);
        }


        public void resolveView(TestHead cricitestheadview) {
            cricitestheadview.onResolved();
        }


        @Override
        public void recycleView() {
            TestHead cricitestheadview = (TestHead) getResolver();
        }


        public void bindToggle(TestHead cricitestheadview, View view) {
            view.setOnClickListener(new View.OnClickListener() {


                public void onClick(View view) {
                    if (ExpandableViewBinder.this.isExpanded()) {
                        ExpandableViewBinder.this.collapse();
                    } else {
                        ExpandableViewBinder.this.expand();
                    }
                }
            });
        }
    }

    public class SwipeViewBinder extends com.mindorks.placeholderview.SwipeViewBinder<TestHead, SwipePlaceHolderView.FrameView, SwipePlaceHolderView.SwipeOption, SwipeDecor> {

        public void bindClick(TestHead cricitestheadview, SwipePlaceHolderView.FrameView frameView) {
        }


        public void bindLongClick(TestHead cricitestheadview, SwipePlaceHolderView.FrameView frameView) {
        }


        @Override
        public void bindSwipeCancelState() {
        }


        public void bindSwipeHead(TestHead cricitestheadview) {
        }


        public void bindSwipeIn(TestHead cricitestheadview) {
        }


        @Override
        public void bindSwipeInState() {
        }


        public void bindSwipeOut(TestHead cricitestheadview) {
        }


        @Override
        public void bindSwipeOutState() {
        }


        @Override
        public void bindSwipeView(SwipePlaceHolderView.FrameView frameView) {
        }


        public void bindViewPosition(TestHead cricitestheadview, int i) {
        }


        public void bindViews(TestHead cricitestheadview, SwipePlaceHolderView.FrameView frameView) {
        }

        public SwipeViewBinder(TestHead cricitestheadview) {
            super(cricitestheadview, R.layout.ic_player, false);
        }


        public void resolveView(TestHead cricitestheadview) {
            cricitestheadview.onResolved();
        }


        @Override
        public void recycleView() {
            TestHead cricitestheadview = (TestHead) getResolver();
        }


        @Override
        public void unbind() {
            TestHead cricitestheadview = (TestHead) getResolver();
            boolean isNullable = isNullable();
            if (cricitestheadview != null && isNullable) {
                cricitestheadview.mContext = null;
                setResolver(null);
                setAnimationResolver(null);
            }
        }
    }

    public class DirectionalViewBinder extends SwipeDirectionalViewBinder<TestHead, SwipePlaceHolderView.FrameView, SwipeDirectionalView.SwipeDirectionalOption, SwipeDecor> {

        public void bindClick(TestHead cricitestheadview, SwipePlaceHolderView.FrameView frameView) {
        }


        public void bindLongClick(TestHead cricitestheadview, SwipePlaceHolderView.FrameView frameView) {
        }


        @Override
        public void bindSwipeCancelState() {
        }


        public void bindSwipeHead(TestHead cricitestheadview) {
        }


        public void bindSwipeIn(TestHead cricitestheadview) {
        }


        @Override
        public void bindSwipeInDirectional(SwipeDirection swipeDirection) {
        }


        @Override
        public void bindSwipeInState() {
        }


        public void bindSwipeOut(TestHead cricitestheadview) {
        }


        @Override
        public void bindSwipeOutDirectional(SwipeDirection swipeDirection) {
        }


        @Override
        public void bindSwipeOutState() {
        }


        @Override
        public void bindSwipeTouch(float f, float f2, float f3, float f4) {
        }


        @Override
        public void bindSwipeView(SwipePlaceHolderView.FrameView frameView) {
        }


        @Override
        public void bindSwipingDirection(SwipeDirection swipeDirection) {
        }


        public void bindViewPosition(TestHead cricitestheadview, int i) {
        }


        public void bindViews(TestHead cricitestheadview, SwipePlaceHolderView.FrameView frameView) {
        }

        public DirectionalViewBinder(TestHead cricitestheadview) {
            super(cricitestheadview, R.layout.ic_player, false);
        }


        public void resolveView(TestHead cricitestheadview) {
            cricitestheadview.onResolved();
        }


        @Override
        public void recycleView() {
            TestHead cricitestheadview = (TestHead) getResolver();
        }


        @Override
        public void unbind() {
            TestHead cricitestheadview = (TestHead) getResolver();
            boolean isNullable = isNullable();
            if (cricitestheadview != null && isNullable) {
                cricitestheadview.mContext = null;
                setResolver(null);
                setAnimationResolver(null);
            }
        }
    }

    public TestHead(Context context) {
        this.mContext = context;
    }
}
