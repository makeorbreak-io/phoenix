package phoenix.uniquizandroid.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import phoenix.uniquizandroid.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    ExploreFragment explore = new ExploreFragment();
    //HomeFragment home = new HomeFragment();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.app.FragmentManager manager = getFragmentManager();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    // manager.beginTransaction().replace(R.id.content, home).commit();
                    return true;
                case R.id.navigation_explore:
                    mTextMessage.setText("Explore");
                    manager.beginTransaction().replace(R.id.content, explore).commit();
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText("Profile");
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}