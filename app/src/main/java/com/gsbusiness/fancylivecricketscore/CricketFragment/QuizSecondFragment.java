package com.gsbusiness.fancylivecricketscore.CricketFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsbusiness.fancylivecricketscore.CricketAdapter.MatchesOneThree;
import com.gsbusiness.fancylivecricketscore.CricketModel.OddData;
import com.gsbusiness.fancylivecricketscore.R;

import java.util.ArrayList;

public class QuizSecondFragment extends MainCenterFragment {

    private MatchesOneThree playerAdapter;
    private RecyclerView recyclerTeamA;

    public void updateMatchOddsList(ArrayList<OddData.Matchst> arrayList) {
        MatchesOneThree cricimatchoddsadapter = new MatchesOneThree(getActivity(), arrayList);
        this.playerAdapter = cricimatchoddsadapter;
        this.recyclerTeamA.setAdapter(cricimatchoddsadapter);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_team, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerTeamA);
        this.recyclerTeamA = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        this.recyclerTeamA.setItemAnimator(new DefaultItemAnimator());
        return inflate;
    }
}
