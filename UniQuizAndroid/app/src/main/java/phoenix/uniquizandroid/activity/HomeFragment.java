package phoenix.uniquizandroid.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import phoenix.uniquizandroid.R;

/**
 * Created by fabiolourenco on 09/09/17.
 */

public class HomeFragment extends Fragment {

    private View rootView;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public HomeFragment() {
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        PieChart pieChart = (PieChart) rootView.findViewById(R.id.chart1);
        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(80.5f, "Right"));
        entries.add(new PieEntry(19.5f, "Wrong"));

        PieDataSet set = new PieDataSet(entries, "Answers Statistics");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        pieChart.animateY(2000);
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh

        return rootView;
    }
}
