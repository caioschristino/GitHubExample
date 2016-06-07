package com.example.caios.desafio.Service;


import com.example.caios.desafio.Model.GitHubResponse;
import com.example.caios.desafio.Model.Pulls;
import com.example.caios.desafio.Model.PullsResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by caios on 6/7/16.
 */
public interface GithubService {
    String SERVICE_ENDPOINT = "https://api.github.com";

    @GET("/repos/{login}/{name}/pulls")
    Observable<ArrayList<Pulls>> getDetailGitHub(@Path("login") String login, @Path("name") String name);

    @GET("/search/repositories?q=language:Java&sort=stars")
    Observable<GitHubResponse> getAllGitHub(@Query("page") int page);
}