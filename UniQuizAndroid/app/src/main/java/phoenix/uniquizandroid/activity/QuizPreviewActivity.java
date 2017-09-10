package phoenix.uniquizandroid.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import phoenix.uniquizandroid.R;
import phoenix.uniquizandroid.dto.QuizDTO;
import phoenix.uniquizandroid.dto.SolutionDTO;
import phoenix.uniquizandroid.restclient.AppSession;
import phoenix.uniquizandroid.restclient.RestProperties;

public class QuizPreviewActivity extends AppCompatActivity {

    private QuizDTO quiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_preview);
        quiz = (QuizDTO) getIntent().getSerializableExtra("quiz");
        TextView name = (TextView) findViewById(R.id.quiz_name);
        name.setText(quiz.getTitle());

        TextView difficulty = (TextView) findViewById(R.id.quiz_difficulty);
        difficulty.setText(quiz.getDifficulty());

        new GetResultsTask().execute();
        Button begin = (Button) findViewById(R.id.begin_button);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizPreviewActivity.this, QuizActivity.class).putExtras(getIntent().getExtras()));
            }
        });

    }

    public class GetResultsTask extends AsyncTask<Void, Void, SolutionDTO> {

        @Override
        protected SolutionDTO doInBackground(Void... params) {


            RestProperties webProperties = new RestProperties(QuizPreviewActivity.this);
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                        .host(webProperties.getHost())
                        .path(webProperties.getAppBaseUri()
                                +"/" + webProperties.getSolutionsUri() + "/latest")
                    .queryParam("quiz", quiz.getPk())
                    .queryParam("email", AppSession.loggedUser.getEmail()).build();



            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<SolutionDTO> result = restTemplate.getForEntity(uri.toUri(), SolutionDTO.class);

            SolutionDTO response = result.getBody();

            return response;
        }

        @Override
        protected void onPostExecute(final SolutionDTO solution) {
            TextView right = (TextView) findViewById(R.id.right_answers);
            right.setText(String.valueOf(solution.getRightAnswers()));

            TextView wrong = (TextView) findViewById(R.id.wrong_answers);
            wrong.setText(String.valueOf(solution.getWrongAnswers()));
        }
    }
}
