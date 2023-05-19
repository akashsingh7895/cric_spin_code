package com.avssolution.fancylivecricketscore.CricketAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.avssolution.fancylivecricketscore.CricketModel.PlayerData;
import com.avssolution.fancylivecricketscore.R;
import com.mindorks.placeholderview.LoadMoreCallbackBinder;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipeDirection;
import com.mindorks.placeholderview.SwipeDirectionalView;
import com.mindorks.placeholderview.SwipeDirectionalViewBinder;
import com.mindorks.placeholderview.SwipePlaceHolderView;

public class TeamHeading {
     TextView headingTeamScore, headingTxt;
     Context mContext;
     PlayerData.Playerslist mHeading;
     int mParentPosition;
     ImageView toggleIcon;
     LinearLayout toggleView;

    public class LoadMoreViewBinder extends ViewBinder implements LoadMoreCallbackBinder<TeamHeading> {
        public void bindLoadMore(TeamHeading criciteamplayerheadingview) {
        }

        public LoadMoreViewBinder(TeamHeading criciteamplayerheadingview) {
            super(criciteamplayerheadingview);
        }
    }

    public class ViewBinder extends com.mindorks.placeholderview.ViewBinder<TeamHeading, View> {
        
        public void bindClick(TeamHeading criciteamplayerheadingview, View view) {
        }

        
        public void bindLongClick(TeamHeading criciteamplayerheadingview, View view) {
        }

        
        public void bindViewPosition(TeamHeading criciteamplayerheadingview, int i) {
        }

        public ViewBinder(TeamHeading criciteamplayerheadingview) {
            super(criciteamplayerheadingview, R.layout.ic_match, false);
        }

        
        public void resolveView(TeamHeading criciteamplayerheadingview) {
            criciteamplayerheadingview.onResolved();
        }

        
        @Override 
        public void recycleView() {
            TeamHeading criciteamplayerheadingview = (TeamHeading) getResolver();
        }

        
        @Override 
        public void unbind() {
            TeamHeading criciteamplayerheadingview = (TeamHeading) getResolver();
            boolean isNullable = isNullable();
            if (criciteamplayerheadingview != null && isNullable) {
                criciteamplayerheadingview.headingTeamScore = null;
                criciteamplayerheadingview.headingTxt = null;
                criciteamplayerheadingview.mContext = null;
                criciteamplayerheadingview.mHeading = null;
                criciteamplayerheadingview.toggleIcon = null;
                criciteamplayerheadingview.toggleView = null;
                setResolver(null);
                setAnimationResolver(null);
            }
        }

        
        public void bindViews(TeamHeading criciteamplayerheadingview, View view) {
            criciteamplayerheadingview.headingTeamScore =  view.findViewById(R.id.headingTeamScore);
            criciteamplayerheadingview.headingTxt =  view.findViewById(R.id.headingTxt);
            criciteamplayerheadingview.toggleIcon =  view.findViewById(R.id.toggleIcon);
        }
    }

    public class ExpandableViewBinder extends com.mindorks.placeholderview.ExpandableViewBinder<TeamHeading, View> {
        
        @Override 
        @Deprecated
        public void bindAnimation(int i, int i2, View view) {
        }

        
        @Override 
        public void bindChildPosition(int i) {
        }

        
        public void bindClick(TeamHeading criciteamplayerheadingview, View view) {
        }

        
        public void bindLongClick(TeamHeading criciteamplayerheadingview, View view) {
        }

        
        public void bindViewPosition(TeamHeading criciteamplayerheadingview, int i) {
        }

        
        @Override
        @Deprecated
        public void unbind() {
        }

        public ExpandableViewBinder(TeamHeading criciteamplayerheadingview) {
            super(criciteamplayerheadingview, R.layout.ic_match, false, true, true);
        }

        
        public void resolveView(TeamHeading criciteamplayerheadingview) {
            criciteamplayerheadingview.onResolved();
        }

        
        @Override 
        public void recycleView() {
            TeamHeading criciteamplayerheadingview = (TeamHeading) getResolver();
        }

        
        public void bindViews(TeamHeading criciteamplayerheadingview, View view) {
            criciteamplayerheadingview.headingTeamScore =  view.findViewById(R.id.headingTeamScore);
            criciteamplayerheadingview.headingTxt =  view.findViewById(R.id.headingTxt);
            criciteamplayerheadingview.toggleIcon =  view.findViewById(R.id.toggleIcon);
        }

        
        @Override 
        public void bindParentPosition(int i) {
            ((TeamHeading) getResolver()).mParentPosition = i;
            setParentPosition(i);
        }

        
        public void bindToggle(TeamHeading criciteamplayerheadingview, View view) {
            view.findViewById(R.id.toggleView).setOnClickListener(new View.OnClickListener() {
             

                public void onClick(View view) {
                    if (ExpandableViewBinder.this.isExpanded()) {
                        ExpandableViewBinder.this.collapse();
                    } else {
                        ExpandableViewBinder.this.expand();
                    }
                }
            });
        }

        
        public void bindExpand(TeamHeading criciteamplayerheadingview) {
            criciteamplayerheadingview.onExpand();
        }

        
        public void bindCollapse(TeamHeading criciteamplayerheadingview) {
            criciteamplayerheadingview.onCollapse();
        }
    }

    public class SwipeViewBinder extends com.mindorks.placeholderview.SwipeViewBinder<TeamHeading, SwipePlaceHolderView.FrameView, SwipePlaceHolderView.SwipeOption, SwipeDecor> {
        
        public void bindClick(TeamHeading criciteamplayerheadingview, SwipePlaceHolderView.FrameView frameView) {
        }

        
        public void bindLongClick(TeamHeading criciteamplayerheadingview, SwipePlaceHolderView.FrameView frameView) {
        }

        
        @Override 
        public void bindSwipeCancelState() {
        }

        
        public void bindSwipeHead(TeamHeading criciteamplayerheadingview) {
        }

        
        public void bindSwipeIn(TeamHeading criciteamplayerheadingview) {
        }

        
        @Override 
        public void bindSwipeInState() {
        }

        
        public void bindSwipeOut(TeamHeading criciteamplayerheadingview) {
        }

        
        @Override 
        public void bindSwipeOutState() {
        }

        
        @Override 
        public void bindSwipeView(SwipePlaceHolderView.FrameView frameView) {
        }

        
        public void bindViewPosition(TeamHeading criciteamplayerheadingview, int i) {
        }

        public SwipeViewBinder(TeamHeading criciteamplayerheadingview) {
            super(criciteamplayerheadingview, R.layout.ic_match, false);
        }

        
        public void resolveView(TeamHeading criciteamplayerheadingview) {
            criciteamplayerheadingview.onResolved();
        }

        
        @Override 
        public void recycleView() {
            TeamHeading criciteamplayerheadingview = (TeamHeading) getResolver();
        }

        
        @Override 
        public void unbind() {
            TeamHeading criciteamplayerheadingview = (TeamHeading) getResolver();
            boolean isNullable = isNullable();
            if (criciteamplayerheadingview != null && isNullable) {
                criciteamplayerheadingview.headingTeamScore = null;
                criciteamplayerheadingview.headingTxt = null;
                criciteamplayerheadingview.mContext = null;
                criciteamplayerheadingview.mHeading = null;
                criciteamplayerheadingview.toggleIcon = null;
                criciteamplayerheadingview.toggleView = null;
                setResolver(null);
                setAnimationResolver(null);
            }
        }

        
        public void bindViews(TeamHeading criciteamplayerheadingview, SwipePlaceHolderView.FrameView frameView) {
            criciteamplayerheadingview.headingTeamScore =  frameView.findViewById(R.id.headingTeamScore);
            criciteamplayerheadingview.headingTxt =  frameView.findViewById(R.id.headingTxt);
            criciteamplayerheadingview.toggleIcon =  frameView.findViewById(R.id.toggleIcon);
        }
    }

    public class DirectionalViewBinder extends SwipeDirectionalViewBinder<TeamHeading, SwipePlaceHolderView.FrameView, SwipeDirectionalView.SwipeDirectionalOption, SwipeDecor> {
        
        public void bindClick(TeamHeading criciteamplayerheadingview, SwipePlaceHolderView.FrameView frameView) {
        }

        
        public void bindLongClick(TeamHeading criciteamplayerheadingview, SwipePlaceHolderView.FrameView frameView) {
        }

        
        @Override 
        public void bindSwipeCancelState() {
        }

        
        public void bindSwipeHead(TeamHeading criciteamplayerheadingview) {
        }

        
        public void bindSwipeIn(TeamHeading criciteamplayerheadingview) {
        }

        
        @Override 
        public void bindSwipeInDirectional(SwipeDirection swipeDirection) {
        }

        
        @Override 
        public void bindSwipeInState() {
        }

        
        public void bindSwipeOut(TeamHeading criciteamplayerheadingview) {
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

        
        public void bindViewPosition(TeamHeading criciteamplayerheadingview, int i) {
        }

        public DirectionalViewBinder(TeamHeading criciteamplayerheadingview) {
            super(criciteamplayerheadingview, R.layout.ic_match, false);
        }

        
        public void resolveView(TeamHeading criciteamplayerheadingview) {
            criciteamplayerheadingview.onResolved();
        }

        
        @Override 
        public void recycleView() {
            TeamHeading criciteamplayerheadingview = (TeamHeading) getResolver();
        }

        
        @Override 
        public void unbind() {
            TeamHeading criciteamplayerheadingview = (TeamHeading) getResolver();
            boolean isNullable = isNullable();
            if (criciteamplayerheadingview != null && isNullable) {
                criciteamplayerheadingview.headingTeamScore = null;
                criciteamplayerheadingview.headingTxt = null;
                criciteamplayerheadingview.mContext = null;
                criciteamplayerheadingview.mHeading = null;
                criciteamplayerheadingview.toggleIcon = null;
                criciteamplayerheadingview.toggleView = null;
                setResolver(null);
                setAnimationResolver(null);
            }
        }

        
        public void bindViews(TeamHeading criciteamplayerheadingview, SwipePlaceHolderView.FrameView frameView) {
            criciteamplayerheadingview.headingTeamScore =  frameView.findViewById(R.id.headingTeamScore);
            criciteamplayerheadingview.headingTxt =  frameView.findViewById(R.id.headingTxt);
            criciteamplayerheadingview.toggleIcon =  frameView.findViewById(R.id.toggleIcon);
        }
    }

    public TeamHeading(Context context, PlayerData.Playerslist playerslist) {
        this.mContext = context;
        this.mHeading = playerslist;
    }

    public void onResolved() {
        try {
            this.toggleIcon.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.ic_white_down));
            if (this.mHeading.getInning().intValue() == 1) {
                TextView textView = this.headingTxt;
                textView.setText(this.mHeading.getTeamName() + " (" + this.mHeading.getInning() + "st Innings)");
            } else {
                TextView textView2 = this.headingTxt;
                textView2.setText(this.mHeading.getTeamName() + " (" + this.mHeading.getInning() + "nd Innings)");
            }
            TextView textView3 = this.headingTeamScore;
            textView3.setText(" ( " + this.mHeading.getTeamRuns() + " )");
        } catch (Exception unused) {
        }
    }

    public void onExpand() {
        this.toggleIcon.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.ic_white_down));
    }

    public void onCollapse() {
        this.toggleIcon.setImageDrawable(this.mContext.getResources().getDrawable(R.drawable.ic_white_up_arrow));
    }
}
