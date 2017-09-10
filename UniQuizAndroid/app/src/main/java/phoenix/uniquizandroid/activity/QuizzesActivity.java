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
import phoenix.uniquizandroid.adapter.ExploreQuizAdapter;
import phoenix.uniquizandroid.dto.CourseDTO;
import phoenix.uniquizandroid.dto.QuizDTO;
import phoenix.uniquizandroid.dto.SubjectDTO;
import phoenix.uniquizandroid.restclient.RestProperties;

public class QuizzesActivity extends AppCompatActivity {

    private SubjectDTO subject;
    private CourseDTO course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

        if(getIntent().hasExtra("subject")){
            subject = (SubjectDTO) getIntent().getSerializableExtra("subject");
        }else{
            course = (CourseDTO) getIntent().getSerializableExtra("course");
        }

        new GetQuizzesTask().execute();
    }

    public class GetQuizzesTask extends AsyncTask<Void, Void, QuizDTO[]> {

        @Override
        protected QuizDTO[] doInBackground(Void... params) {


            RestProperties webProperties = new RestProperties(QuizzesActivity.this);
            final UriComponents uri;
            if(subject == null){
                uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                        .host(webProperties.getHost())
                        .path(webProperties.getAppBaseUri()
                                +"/" + webProperties.getQuizUri()).queryParam("subjectPk", subject.getPk()).build();
            }else{
                uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                        .host(webProperties.getHost())
                        .path(webProperties.getAppBaseUri()
                                +"/" + webProperties.getQuizUri()).queryParam("coursePk", course.getPk()).build();
            }


            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<QuizDTO[]> result = restTemplate.getForEntity(uri.toUri(), QuizDTO[].class);

            QuizDTO[] response = result.getBody();

            return response;
        }

        @Override
        protected void onPostExecute(final QuizDTO[] subjects) {
            if (subjects != null) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(QuizzesActivity.this,LinearLayoutManager.VERTICAL, false);
                RecyclerView subjectsView = (RecyclerView) findViewById(R.id.content);
                subjectsView.setLayoutManager(layoutManager);
                subjectsView.setHasFixedSize(true);
                subjectsView.setNestedScrollingEnabled(false);
                subjectsView.setAdapter(new ExploreQuizAdapter(QuizzesActivity.this, subjects));
            } else {

            }
        }
    }
}


