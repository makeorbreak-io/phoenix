package phoenix.uniquizandroid.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import phoenix.uniquizandroid.R;
import phoenix.uniquizandroid.activity.dummy.DummyContent;
import phoenix.uniquizandroid.activity.dummy.DummyContent.DummyItem;
import phoenix.uniquizandroid.adapter.FieldCardAdapter;
import phoenix.uniquizandroid.dto.FieldDTO;
import phoenix.uniquizandroid.dto.SubjectDTO;
import phoenix.uniquizandroid.restclient.RestProperties;

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

    public class GetFieldsTask extends AsyncTask<Void, Void, FieldDTO[]> {

        @Override
        protected FieldDTO[] doInBackground(Void... params) {


            RestProperties webProperties = new RestProperties(ExploreFragment.this.getContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost())
                    .path(webProperties.getAppBaseUri()
                            +"/" + webProperties.getFieldUri()) .build();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<FieldDTO[]> result = restTemplate.getForEntity(uri.toUri(), FieldDTO[].class);

            FieldDTO[] response = result.getBody();

            return response;
        }

        @Override
        protected void onPostExecute(final FieldDTO[] field) {
            if (field != null) {

            } else {

            }
        }
    }

    public class GetSubjectsTask extends AsyncTask<Void, Void, SubjectDTO[]> {

        @Override
        protected SubjectDTO[] doInBackground(Void... params) {


            RestProperties webProperties = new RestProperties(ExploreFragment.this.getContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost())
                    .path(webProperties.getAppBaseUri()
                            +"/" + webProperties.getFieldUri()) .build();

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<SubjectDTO[]> result = restTemplate.getForEntity(uri.toUri(), SubjectDTO[].class);

            SubjectDTO[] response = result.getBody();

            return response;
        }

        @Override
        protected void onPostExecute(final SubjectDTO[] field) {
            if (field != null) {

            } else {

            }
        }

        //TODO most popular quizzes
    }
}
