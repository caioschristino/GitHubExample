package com.example.caios.desafio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.caios.desafio.Adapter.GitListAdapter;
import com.example.caios.desafio.Adapter.GitDetailAdapter;
import com.example.caios.desafio.Adapter.RecycleViewAdapter;
import com.example.caios.desafio.Model.Pulls;
import com.example.caios.desafio.Service.GithubService;
import com.example.caios.desafio.Service.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by caios on 6/7/16.
 */
public class DetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        adapter = new RecycleViewAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getDetails();
    }

    private void getDetails() {
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        String login = i.getStringExtra("login");

        if (!login.isEmpty() && !name.isEmpty()) {
            executeRequest(login, name);
            i.removeExtra("name");
            i.removeExtra("login");
        }
    }

    private void executeRequest(String login, String name) {
        GithubService service = ServiceFactory
                .createRetrofitService(GithubService.class,
                        GithubService.SERVICE_ENDPOINT);

        service.getDetailGitHub(login, name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<Pulls>>() {
                    @Override
                    public final void onCompleted() {

                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("error>>>", e.getMessage());
                    }

                    @Override
                    public final void onNext(final ArrayList<Pulls> response) {
                        adapter.appendItems(response);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}