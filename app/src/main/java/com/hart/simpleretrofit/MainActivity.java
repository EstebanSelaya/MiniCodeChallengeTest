package com.hart.simpleretrofit;

import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.hart.simpleretrofit.dto.GetRandomUserResponse;
import com.hart.simpleretrofit.dto.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements TestInterface{

    static API apiService;
    static List<Result> resultList;

    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.recycler)
    RecyclerView rv;

    MainRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initialize();
        getRandomUsers(100);
    }

    private void initialize(){
        // Initialize Retrofit / OkHttp
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        String BASE_URL = "https://randomuser.me/api/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(API.class);

        // Initialize Adapter
        adapter = new MainRecyclerAdapter();
        rv.setAdapter(new MainRecyclerAdapter());
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(ContextCompat.getDrawable(this, R.drawable.listitem_divider)));

        // Initialize SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRandomUsers(100);
            }
        });
    }

    private void getRandomUsers(int num){
        if (apiService != null) {
            Call<GetRandomUserResponse> call = apiService.getRandomUsers(num);
            call.enqueue(new Callback<GetRandomUserResponse>() {
                @Override
                public void onResponse(Call<GetRandomUserResponse> call, Response<GetRandomUserResponse> response) {
                    Log.i("User Get: ", response.body().toString());
                    resultList = response.body().resultList;
                    bindAdapter();
                }

                @Override
                public void onFailure(Call<GetRandomUserResponse> call, Throwable t) {
                    Log.i("User Get: ", "Failure");
                }
            });
        }
    }

    private void bindAdapter(){
        swipeRefreshLayout.setRefreshing(false);
        if (adapter != null){
            adapter.clear();
            adapter.addAll(resultList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public List<Result> sortByLastName() {
        if (resultList != null){
            Collections.sort(resultList, new Comparator<Result>() {
                @Override
                public int compare(Result lhs, Result rhs) {
                    return lhs.name.last.compareTo(rhs.name.last);
                }
            });

            return resultList;
        }
        return null;
    }

    @Override
    public Map<String, List<Result>> categorizeResults() {
        if (resultList != null){
            Map<String, List<Result>> map = prepareMap(resultList);
            for (Result r: resultList){
                if (map.containsKey(r.name.title)){
                    map.get(r.name.title).add(r);
                }
            }
            return map;
        }
        return null;
    }

    private Map<String, List<Result>> prepareMap(List<Result> list){
        Map<String, List<Result>> map = new HashMap<>();
        for (Result r: list){
            if (!map.containsKey(r.name.title)){
                map.put(r.name.title, new ArrayList<Result>());
            }
        }
        return map;
    }
}
