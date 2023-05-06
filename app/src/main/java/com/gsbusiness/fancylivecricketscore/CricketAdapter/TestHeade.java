package com.gsbusiness.fancylivecricketscore.CricketAdapter;

import static com.gsbusiness.fancylivecricketscore.Comman.TestTeamApiData;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.gsbusiness.fancylivecricketscore.CricketModel.PlayerData;
import com.gsbusiness.fancylivecricketscore.R;
import com.mindorks.placeholderview.LoadMoreCallbackBinder;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipeDirection;
import com.mindorks.placeholderview.SwipeDirectionalView;
import com.mindorks.placeholderview.SwipeDirectionalViewBinder;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import java.text.DecimalFormat;

public class TestHeade {

    ImageView img_player;
    int mChildPosition;
    Context mContext;
    PlayerData.Playerslist mInfo;
    int mParentPosition;
    TextView txtPlayerBalls, txtPlayerFours, txtPlayerName, txtPlayerSR, txtPlayerScore, txtPlayerSix;

    public class LoadMoreViewBinder extends ViewBinder implements LoadMoreCallbackBinder<TestHeade> {
        public void bindLoadMore(TestHeade cricitestteaminfoview) {
        }

        public LoadMoreViewBinder(TestHeade cricitestteaminfoview) {
            super(cricitestteaminfoview);
        }
    }

    public class ViewBinder extends com.mindorks.placeholderview.ViewBinder<TestHeade, View> {

        public void bindClick(TestHeade cricitestteaminfoview, View view) {
        }


        public void bindLongClick(TestHeade cricitestteaminfoview, View view) {
        }


        public void bindViewPosition(TestHeade cricitestteaminfoview, int i) {
        }

        public ViewBinder(TestHeade cricitestteaminfoview) {
            super(cricitestteaminfoview, R.layout.ic_similliar_adapter, false);
        }


        public void resolveView(TestHeade cricitestteaminfoview) {
            cricitestteaminfoview.onResolved();
        }


        @Override
        public void recycleView() {
            TestHeade cricitestteaminfoview = (TestHeade) getResolver();
        }


        @Override
        public void unbind() {
            TestHeade cricitestteaminfoview = (TestHeade) getResolver();
            boolean isNullable = isNullable();
            if (cricitestteaminfoview != null && isNullable) {
                cricitestteaminfoview.img_player = null;
                cricitestteaminfoview.mContext = null;
                cricitestteaminfoview.mInfo = null;
                cricitestteaminfoview.txtPlayerBalls = null;
                cricitestteaminfoview.txtPlayerFours = null;
                cricitestteaminfoview.txtPlayerName = null;
                cricitestteaminfoview.txtPlayerSR = null;
                cricitestteaminfoview.txtPlayerScore = null;
                cricitestteaminfoview.txtPlayerSix = null;
                setResolver(null);
                setAnimationResolver(null);
            }
        }


        public void bindViews(TestHeade cricitestteaminfoview, View view) {
            cricitestteaminfoview.img_player = view.findViewById(R.id.img_player);
            cricitestteaminfoview.txtPlayerBalls = view.findViewById(R.id.txtPlayerBalls);
            cricitestteaminfoview.txtPlayerFours = view.findViewById(R.id.txtPlayerFours);
            cricitestteaminfoview.txtPlayerName = view.findViewById(R.id.txtPlayerName);
            cricitestteaminfoview.txtPlayerSR = view.findViewById(R.id.txtPlayerSR);
            cricitestteaminfoview.txtPlayerScore = view.findViewById(R.id.txtPlayerScore);
            cricitestteaminfoview.txtPlayerSix = view.findViewById(R.id.txtPlayerSix);
        }
    }

    public class ExpandableViewBinder extends com.mindorks.placeholderview.ExpandableViewBinder<TestHeade, View> {

        @Override
        @Deprecated
        public void bindAnimation(int i, int i2, View view) {
        }


        public void bindClick(TestHeade cricitestteaminfoview, View view) {
        }


        public void bindCollapse(TestHeade cricitestteaminfoview) {
        }


        public void bindExpand(TestHeade cricitestteaminfoview) {
        }


        public void bindLongClick(TestHeade cricitestteaminfoview, View view) {
        }


        public void bindViewPosition(TestHeade cricitestteaminfoview, int i) {
        }


        @Override
        @Deprecated
        public void unbind() {
        }

        public ExpandableViewBinder(TestHeade cricitestteaminfoview) {
            super(cricitestteaminfoview, R.layout.ic_similliar_adapter, false, false, false);
        }


        public void resolveView(TestHeade cricitestteaminfoview) {
            cricitestteaminfoview.onResolved();
        }


        @Override
        public void recycleView() {
            TestHeade cricitestteaminfoview = (TestHeade) getResolver();
        }


        public void bindViews(TestHeade cricitestteaminfoview, View view) {
            cricitestteaminfoview.img_player = view.findViewById(R.id.img_player);
            cricitestteaminfoview.txtPlayerBalls = view.findViewById(R.id.txtPlayerBalls);
            cricitestteaminfoview.txtPlayerFours = view.findViewById(R.id.txtPlayerFours);
            cricitestteaminfoview.txtPlayerName = view.findViewById(R.id.txtPlayerName);
            cricitestteaminfoview.txtPlayerSR = view.findViewById(R.id.txtPlayerSR);
            cricitestteaminfoview.txtPlayerScore = view.findViewById(R.id.txtPlayerScore);
            cricitestteaminfoview.txtPlayerSix = view.findViewById(R.id.txtPlayerSix);
        }


        @Override
        public void bindParentPosition(int i) {
            ((TestHeade) getResolver()).mParentPosition = i;
            setParentPosition(i);
        }


        @Override
        public void bindChildPosition(int i) {
            ((TestHeade) getResolver()).mChildPosition = i;
            setChildPosition(i);
        }


        public void bindToggle(TestHeade cricitestteaminfoview, View view) {
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

    public class SwipeViewBinder extends com.mindorks.placeholderview.SwipeViewBinder<TestHeade, SwipePlaceHolderView.FrameView, SwipePlaceHolderView.SwipeOption, SwipeDecor> {

        public void bindClick(TestHeade cricitestteaminfoview, SwipePlaceHolderView.FrameView frameView) {
        }


        public void bindLongClick(TestHeade cricitestteaminfoview, SwipePlaceHolderView.FrameView frameView) {
        }


        @Override
        public void bindSwipeCancelState() {
        }


        public void bindSwipeHead(TestHeade cricitestteaminfoview) {
        }


        public void bindSwipeIn(TestHeade cricitestteaminfoview) {
        }


        @Override
        public void bindSwipeInState() {
        }


        public void bindSwipeOut(TestHeade cricitestteaminfoview) {
        }


        @Override
        public void bindSwipeOutState() {
        }


        @Override
        public void bindSwipeView(SwipePlaceHolderView.FrameView frameView) {
        }


        public void bindViewPosition(TestHeade cricitestteaminfoview, int i) {
        }

        public SwipeViewBinder(TestHeade cricitestteaminfoview) {
            super(cricitestteaminfoview, R.layout.ic_similliar_adapter, false);
        }


        public void resolveView(TestHeade cricitestteaminfoview) {
            cricitestteaminfoview.onResolved();
        }


        @Override
        public void recycleView() {
            TestHeade cricitestteaminfoview = (TestHeade) getResolver();
        }


        @Override
        public void unbind() {
            TestHeade cricitestteaminfoview = (TestHeade) getResolver();
            boolean isNullable = isNullable();
            if (cricitestteaminfoview != null && isNullable) {
                cricitestteaminfoview.img_player = null;
                cricitestteaminfoview.mContext = null;
                cricitestteaminfoview.mInfo = null;
                cricitestteaminfoview.txtPlayerBalls = null;
                cricitestteaminfoview.txtPlayerFours = null;
                cricitestteaminfoview.txtPlayerName = null;
                cricitestteaminfoview.txtPlayerSR = null;
                cricitestteaminfoview.txtPlayerScore = null;
                cricitestteaminfoview.txtPlayerSix = null;
                setResolver(null);
                setAnimationResolver(null);
            }
        }


        public void bindViews(TestHeade cricitestteaminfoview, SwipePlaceHolderView.FrameView frameView) {
            cricitestteaminfoview.img_player = frameView.findViewById(R.id.img_player);
            cricitestteaminfoview.txtPlayerBalls = frameView.findViewById(R.id.txtPlayerBalls);
            cricitestteaminfoview.txtPlayerFours = frameView.findViewById(R.id.txtPlayerFours);
            cricitestteaminfoview.txtPlayerName = frameView.findViewById(R.id.txtPlayerName);
            cricitestteaminfoview.txtPlayerSR = frameView.findViewById(R.id.txtPlayerSR);
            cricitestteaminfoview.txtPlayerScore = frameView.findViewById(R.id.txtPlayerScore);
            cricitestteaminfoview.txtPlayerSix = frameView.findViewById(R.id.txtPlayerSix);
        }
    }

    public class DirectionalViewBinder extends SwipeDirectionalViewBinder<TestHeade, SwipePlaceHolderView.FrameView, SwipeDirectionalView.SwipeDirectionalOption, SwipeDecor> {

        public void bindClick(TestHeade cricitestteaminfoview, SwipePlaceHolderView.FrameView frameView) {
        }


        public void bindLongClick(TestHeade cricitestteaminfoview, SwipePlaceHolderView.FrameView frameView) {
        }


        @Override
        public void bindSwipeCancelState() {
        }


        public void bindSwipeHead(TestHeade cricitestteaminfoview) {
        }


        public void bindSwipeIn(TestHeade cricitestteaminfoview) {
        }


        @Override
        public void bindSwipeInDirectional(SwipeDirection swipeDirection) {
        }


        @Override
        public void bindSwipeInState() {
        }


        public void bindSwipeOut(TestHeade cricitestteaminfoview) {
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


        public void bindViewPosition(TestHeade cricitestteaminfoview, int i) {
        }

        public DirectionalViewBinder(TestHeade cricitestteaminfoview) {
            super(cricitestteaminfoview, R.layout.ic_similliar_adapter, false);
        }


        public void resolveView(TestHeade cricitestteaminfoview) {
            cricitestteaminfoview.onResolved();
        }


        @Override
        public void recycleView() {
            TestHeade cricitestteaminfoview = (TestHeade) getResolver();
        }


        @Override
        public void unbind() {
            TestHeade cricitestteaminfoview = (TestHeade) getResolver();
            boolean isNullable = isNullable();
            if (cricitestteaminfoview != null && isNullable) {
                cricitestteaminfoview.img_player = null;
                cricitestteaminfoview.mContext = null;
                cricitestteaminfoview.mInfo = null;
                cricitestteaminfoview.txtPlayerBalls = null;
                cricitestteaminfoview.txtPlayerFours = null;
                cricitestteaminfoview.txtPlayerName = null;
                cricitestteaminfoview.txtPlayerSR = null;
                cricitestteaminfoview.txtPlayerScore = null;
                cricitestteaminfoview.txtPlayerSix = null;
                setResolver(null);
                setAnimationResolver(null);
            }
        }


        public void bindViews(TestHeade cricitestteaminfoview, SwipePlaceHolderView.FrameView frameView) {
            cricitestteaminfoview.img_player = frameView.findViewById(R.id.img_player);
            cricitestteaminfoview.txtPlayerBalls = frameView.findViewById(R.id.txtPlayerBalls);
            cricitestteaminfoview.txtPlayerFours = frameView.findViewById(R.id.txtPlayerFours);
            cricitestteaminfoview.txtPlayerName = frameView.findViewById(R.id.txtPlayerName);
            cricitestteaminfoview.txtPlayerSR = frameView.findViewById(R.id.txtPlayerSR);
            cricitestteaminfoview.txtPlayerScore = frameView.findViewById(R.id.txtPlayerScore);
            cricitestteaminfoview.txtPlayerSix = frameView.findViewById(R.id.txtPlayerSix);
        }
    }

    public TestHeade(Context context, PlayerData.Playerslist playerslist) {
        this.mContext = context;
        this.mInfo = playerslist;
    }

    public void onResolved() {
        try {
            TextView textView = this.txtPlayerBalls;
            textView.setText(this.mInfo.getBalls() + "");
            TextView textView2 = this.txtPlayerScore;
            textView2.setText(this.mInfo.getRuns() + "");
            this.txtPlayerName.setText(this.mInfo.getPlayerName());
            TextView textView3 = this.txtPlayerFours;
            textView3.setText(this.mInfo.getFour() + "");
            TextView textView4 = this.txtPlayerSix;
            textView4.setText(this.mInfo.getSix() + "");
            if (this.mInfo.getRuns().intValue() != 0) {
                TextView textView5 = this.txtPlayerSR;
                StringBuilder sb = new StringBuilder();
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                double intValue = (double) this.mInfo.getRuns().intValue();
                double intValue2 = (double) this.mInfo.getBalls().intValue();
                Double.isNaN(intValue);
                Double.isNaN(intValue2);
                sb.append(decimalFormat.format((intValue / intValue2) * 100.0d));
                sb.append("");
                textView5.setText(sb.toString());
            } else {
                this.txtPlayerSR.setText("0.00");
            }
            if (this.mInfo.getPlayerImage() != null) {
                RequestManager with = Glide.with(this.mContext);
                with.load(TestTeamApiData + this.mInfo.getPlayerImage()).apply((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) ((RequestOptions) new RequestOptions().placeholder(R.mipmap.ic_launcher)).centerInside()).error(R.mipmap.ic_launcher))).into(this.img_player);
            }
        } catch (Exception e) {

        }
    }
}
