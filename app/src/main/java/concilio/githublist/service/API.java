package concilio.githublist.service;

import java.util.List;

import concilio.githublist.model.Repository;
import concilio.githublist.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by concilio on 14/11/17.
 */

public interface API {

    String BASE_URL = "https://api.github.com/";

    @GET("users/{username}/repos")
    Call<List<Repository>> getGitHubUserRepositories(@Path("username") String username);

    @GET("users/{username}")
    Call<User> getGitHubUser(@Path("username") String username);

}
