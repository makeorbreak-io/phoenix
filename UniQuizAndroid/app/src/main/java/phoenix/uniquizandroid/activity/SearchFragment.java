package phoenix.uniquizandroid.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.SearchView;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import phoenix.uniquizandroid.R;
import phoenix.uniquizandroid.adapter.SearchAdapter;
import phoenix.uniquizandroid.dto.QuizDTO;
import phoenix.uniquizandroid.dto.SimplifiedQuizDTO;
import phoenix.uniquizandroid.restclient.RestProperties;

/**
 * Created by Luis Gouveia on 09/09/2017.
 */

public class SearchFragment extends Fragment{
    private View rootView;
    private String search;
    private RecyclerView.ItemDecoration categoryDivider;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_search, container, false);

        SearchView searchBar = (SearchView) rootView.findViewById(R.id.search_bar);

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search = query;
                new HttpRequestTask().execute();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search = newText;
                new HttpRequestTask().execute();
                return true;
            }
        });
        return rootView;
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, QuizDTO[]> {

        @Override
        protected QuizDTO[] doInBackground(Void... params) {
            RestProperties webProperties = new RestProperties(SearchFragment.this.getContext());
            final UriComponents uri = UriComponentsBuilder.newInstance().scheme(webProperties.getScheme())
                    .host(webProperties.getHost()).path(webProperties.getAppBaseUri()+ "/" + webProperties.getSearchUri()).queryParam("title", search).build();

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            ResponseEntity<QuizDTO[]> result = restTemplate.exchange(uri.toUri(), HttpMethod.GET, request, QuizDTO[].class);

            QuizDTO[] objects = result.getBody();

            return objects;
        }

        @Override
        protected void onPostExecute(final QuizDTO[] results) {


            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.content);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new SearchAdapter(SearchFragment.this.getContext(), results));
        }
    }
}
