package phoenix.uniquizandroid.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import phoenix.uniquizandroid.R;
import phoenix.uniquizandroid.dto.UserDTO;
import phoenix.uniquizandroid.restclient.RestProperties;

public class SignUpActivity extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText passwordRepeat;
    private Button confirmButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        passwordRepeat = (EditText) findViewById(R.id.password_confirmation);

        confirmButton = (Button) findViewById(R.id.confirm_sign_up);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag = true;

                if(firstName.getText().toString().isEmpty()){
                    flag = false;
                    firstName.setError("Field Required");
                }
                if(lastName.getText().toString().isEmpty()){
                    flag = false;
                    lastName.setError("Field Required");
                }
                if(email.getText().toString().isEmpty()){
                    flag = false;
                    email.setError("Field Required");
                }
                if(username.getText().toString().isEmpty()){
                    flag = false;
                    username.setError("Field Required");
                }
                if(password.getText().toString().isEmpty()){
                    flag = false;
                    password.setError("Field Required");
                }
                if(passwordRepeat.getText().toString().isEmpty()){
                    flag = false;
                    passwordRepeat.setError("Field Required");
                }
                if(!password.getText().toString().equals(passwordRepeat.getText().toString())){
                    flag = false;
                    passwordRepeat.setError("Passwords do not match");
                }

                if(flag){
                    new UserSignUpTask(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(),
                            username.getText().toString(), password.getText().toString()).execute();
                }
            }
        });
    }

    public class UserSignUpTask extends AsyncTask<Void, Void, HttpStatus> {

        private final String firstName;
        private final String lastName;
        private final String email;
        private final String username;
        private final String password;

        public UserSignUpTask(String firstName, String lastName, String email, String username, String password) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.username = username;
            this.password = password;
        }

        @Override
        protected HttpStatus doInBackground(Void... params) {

            RestProperties webProperties = new RestProperties(SignUpActivity.this);
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).port(webProperties.getPort())
                    .path(webProperties.getAppBaseUri() +"/" + webProperties.getSignUpUri())
                    .build();

            Map<String, Object> body = new HashMap<>();
            body.put("name", firstName+" "+lastName);
            body.put("username", username);
            body.put("email", email);
            body.put("password", password);
            String[] roles = new String[1];
            roles[0] = "STUDENT";
            body.put("roles", roles);

            HttpEntity<Map<String, Object>> request = new HttpEntity<>(body);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<UserDTO> result = restTemplate.exchange(uri.toUri(), HttpMethod.POST, request, UserDTO.class);

            return result.getStatusCode();
        }

        @Override
        protected void onPostExecute(final HttpStatus status) {
            if (status.equals(HttpStatus.CREATED)) {
                Toast.makeText(SignUpActivity.this, "Sign-Up Successful", Toast.LENGTH_SHORT).show();
                onBackPressed();
            } else if(status.equals(HttpStatus.CONFLICT)){
                Toast.makeText(SignUpActivity.this, "User with this username/email already exists", Toast.LENGTH_SHORT).show();
            }
        }
    }
}