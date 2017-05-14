package id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.adapter.WLAdapter;
import id.sch.smktelkom_mlg.privateassignment.xirpl101.movieku.model.WatchList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WLFragment extends Fragment {

    WLAdapter mAdapter;
    ArrayList<WatchList> mList = new ArrayList<>();

    public WLFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wl, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerViewWL);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new WLAdapter(this.getActivity(), mList);
        recyclerView.setAdapter(mAdapter);

        refreshData(null);
    }

    private void refreshData(String query) {
        mList.clear();

        if (query == null || query.isEmpty())
            mList.addAll(WatchList.listAll(WatchList.class));

        mAdapter.notifyDataSetChanged();
    }

}
