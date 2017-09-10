package phoenix.uniquizandroid.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import phoenix.uniquizandroid.R;
import phoenix.uniquizandroid.adapter.CoursesAdapter;
import phoenix.uniquizandroid.dto.CourseDTO;
import phoenix.uniquizandroid.dto.FieldDTO;
import phoenix.uniquizandroid.restclient.RestProperties;

public class CoursesActivity extends AppCompatActivity {

    private FieldDTO field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        field = (FieldDTO) getIntent().getSerializableExtra("field");

        new GetCoursesActivity().execute();
    }

    public class GetCoursesActivity extends AsyncTask<Void, Void, CourseDTO[]> {

        @Override
        protected CourseDTO[] doInBackground(Void... params) {


            RestProperties webProperties = new RestProperties(CoursesActivity.this);
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost())
                    .path(webProperties.getAppBaseUri()
                            + "/" + webProperties.getCourseUri()).queryParam("fieldPk", field.getPk()).build();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<CourseDTO[]> result = restTemplate.getForEntity(uri.toUri(), CourseDTO[].class);

            CourseDTO[] response = result.getBody();

            return response;
        }

        @Override
        protected void onPostExecute(final CourseDTO[] courses) {
            if (courses != null) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(CoursesActivity.this, LinearLayoutManager.VERTICAL, false);
                RecyclerView subjectsView = (RecyclerView) findViewById(R.id.content);
                subjectsView.setLayoutManager(layoutManager);
                subjectsView.setHasFixedSize(true);
                subjectsView.setNestedScrollingEnabled(false);
                subjectsView.setAdapter(new CoursesAdapter(CoursesActivity.this, courses));
            } else {

            }
        }
    }
}
