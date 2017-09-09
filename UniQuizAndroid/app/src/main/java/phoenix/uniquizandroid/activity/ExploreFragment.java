package phoenix.uniquizandroid.activity;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import phoenix.uniquizandroid.R;
import phoenix.uniquizandroid.activity.dummy.DummyContent;
import phoenix.uniquizandroid.activity.dummy.DummyContent.DummyItem;
import phoenix.uniquizandroid.adapter.FieldCardAdapter;

import java.util.List;


public class ExploreFragment extends Fragment {



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ExploreFragment() {
    }


    public static ExploreFragment newInstance() {
        ExploreFragment fragment = new ExploreFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fields_cards_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(new FieldCardAdapter( null));

        RecyclerView recyclerView2 = (RecyclerView) view.findViewById(R.id.subjects_cards_view);
        recyclerView2.setLayoutManager(layoutManager);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setNestedScrollingEnabled(false);
        recyclerView2.setAdapter(new FieldCardAdapter( null));


        return view;
    }

}
