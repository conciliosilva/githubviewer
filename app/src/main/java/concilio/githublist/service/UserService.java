package concilio.githublist.service;

import org.androidannotations.annotations.EBean;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

import concilio.githublist.R;
import concilio.githublist.event.ServiceRequestEvent;
import concilio.githublist.model.Repository;
import concilio.githublist.model.User;
import concilio.githublist.model.UserRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by concilio on 14/11/17.
 */

@EBean
public class UserService {

    private API api;

    public UserService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(API.class);

    }

    public void getUser(final String username) {

        api.getGitHubUser(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                    getUserRepositories(response.body());
                } else if (response.code() == 404) {
                    EventBus.getDefault().post(new ServiceRequestEvent(ServiceRequestEvent.Type.REQUEST_FAILURE, R.string.text_user_not_found));
                } else {
                    EventBus.getDefault().post(new ServiceRequestEvent(ServiceRequestEvent.Type.REQUEST_FAILURE, R.string.text_network_error));
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                EventBus.getDefault().post(new ServiceRequestEvent(ServiceRequestEvent.Type.REQUEST_FAILURE, R.string.text_network_error));
            }
        });

    }

    private void getUserRepositories(final User user) {

        api.getGitHubUserRepositories(user.getUsername()).enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {

                if (response.isSuccessful()) {

                    UserRepository userRepository = new UserRepository(user, response.body());

                    EventBus.getDefault().post(new ServiceRequestEvent(ServiceRequestEvent.Type.REQUEST_SUCCESS, userRepository));

                } else {
                    EventBus.getDefault().post(new ServiceRequestEvent(ServiceRequestEvent.Type.REQUEST_FAILURE, R.string.text_network_error));
                }

            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                EventBus.getDefault().post(new ServiceRequestEvent(ServiceRequestEvent.Type.REQUEST_FAILURE, R.string.text_network_error));
            }
        });

    }

}
