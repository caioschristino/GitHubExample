package com.example.caios.desafio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.caios.desafio.Adapter.GitListAdapter;
import com.example.caios.desafio.Model.GitHubResponse;
import com.example.caios.desafio.Service.GithubService;
import com.example.caios.desafio.Service.ServiceFactory;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements IInfiniteScrollListener {
    protected InfiniteScrollListView listView;
    private GitListAdapter adapter;
    private InfiniteScrollOnScrollListener scrollListener;
    private int page = 1;
    private boolean executing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (InfiniteScrollListView) findViewById(R.id.list_view);
        scrollListener = new InfiniteScrollOnScrollListener(this);
        listView.setListener(scrollListener);
        adapter = new GitListAdapter(this);
        listView.setAdapter(adapter);
        executeRequest();
    }

    @Override
    public void endIsNear() {
        if (!executing) {
            executing = true;
            executeRequest();
        }
    }

    private void executeRequest() {
        GithubService service = ServiceFactory
                .createRetrofitService(GithubService.class,
                        GithubService.SERVICE_ENDPOINT);

        service.getAllGitHub(page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GitHubResponse>() {
                    @Override
                    public final void onCompleted() {
                        executing = false;
                        page++;
                    }

                    @Override
                    public final void onError(Throwable e) {
                        Log.e("error>>>", e.getMessage());
                    }

                    @Override
                    public final void onNext(final GitHubResponse response) {
                        listView.appendItems(response.getItems());
                    }
                });
    }

    @Override
    public void onScrollCalled(int firstVisibleItem, int visibleItemCount, int totalItemCount) {

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