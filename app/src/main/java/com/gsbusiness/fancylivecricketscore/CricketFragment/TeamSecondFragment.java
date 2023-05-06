package com.gsbusiness.fancylivecricketscore.CricketFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gsbusiness.fancylivecricketscore.CricketAdapter.TeamPlayer;
import com.gsbusiness.fancylivecricketscore.CricketModel.PlayerData;
import com.gsbusiness.fancylivecricketscore.R;

import java.util.ArrayList;

public class TeamSecondFragment extends MainCenterFragment {

    private TeamPlayer playerAdapter;
    private RecyclerView recyclerTeamB;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_team, viewGroup, false);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerTeamA);
        this.recyclerTeamB = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        this.recyclerTeamB.setItemAnimator(new DefaultItemAnimator());
        return inflate;
    }

    public void updatePlayerList(ArrayList<PlayerData.Playerslist> arrayList) {
        TeamPlayer criciplayeradapter = new TeamPlayer(getActivity(), arrayList, this.imageURL);
        this.playerAdapter = criciplayeradapter;
        this.recyclerTeamB.setAdapter(criciplayeradapter);
    }
}
