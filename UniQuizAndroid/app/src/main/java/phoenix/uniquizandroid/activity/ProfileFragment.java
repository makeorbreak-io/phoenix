package phoenix.uniquizandroid.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import phoenix.uniquizandroid.R;
import phoenix.uniquizandroid.dto.UserStatistics;
import phoenix.uniquizandroid.restclient.AppSession;

/**
 * Created by fabiolourenco on 09/09/17.
 */

public class ProfileFragment extends Fragment {

    private View rootView;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProfileFragment() {
    }


    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        UserStatistics stats = AppSession.loggedUser.getUserStatistics();

        PieChart answersChart = (PieChart) rootView.findViewById(R.id.answers_chart);
        answersChart.stopNestedScroll();
        answersChart.setDrawEntryLabels(false);
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(Float.valueOf(String.valueOf(stats.getTotalRightAnswers())), "Right"));
        entries.add(new PieEntry(Float.valueOf(String.valueOf(stats.getTotalWrongAnswers())), "Wrong"));
        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(ColorTemplate.MATERIAL_COLORS);
        answersChart.animateY(2000);
        PieData data = new PieData(set);
        answersChart.setData(data);
        answersChart.invalidate(); // refresh

        PieChart quizzesChart = (PieChart) rootView.findViewById(R.id.quizzes_chart);
        quizzesChart.setContentDescription("");
        quizzesChart.stopNestedScroll();
        List<PieEntry> entries2 = new ArrayList<>();
        entries2.add(new PieEntry(Float.valueOf(String.valueOf(stats.getTotalQuizzesPassed())), "Approved"));
        entries2.add(new PieEntry(Float.valueOf(String.valueOf(stats.getTotalQuizzesSolved()-stats.getTotalQuizzesPassed())), "Failed"));
        PieDataSet set2 = new PieDataSet(entries2, "");
        set2.setColors(ColorTemplate.MATERIAL_COLORS);
        quizzesChart.animateY(2000);
        data = new PieData(set2);
        quizzesChart.setData(data);
        quizzesChart.invalidate();

        return rootView;
    }
}
