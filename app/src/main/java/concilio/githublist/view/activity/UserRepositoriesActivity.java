package concilio.githublist.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import concilio.githublist.R;
import concilio.githublist.model.UserRepository;
import concilio.githublist.view.adapter.RepositoryAdapter;
import concilio.githublist.view.utils.BorderedCircleTransform;

/**
 * Created by concilio on 16/11/17.
 */

@EActivity(R.layout.activity_list_user)
public class UserRepositoriesActivity extends BaseActivity {

    @ViewById
    protected ImageView imageViewUser;

    @ViewById
    protected TextView textViewUserId;

    @ViewById
    protected TextView emptyView;

    @ViewById
    protected RecyclerView recyclerViewRepositories;

    @Bean
    protected RepositoryAdapter adapter;

    @Extra
    public UserRepository userRepository;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @AfterViews
    public void afterViews() {

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerViewRepositories.getContext(),
                LinearLayoutManager.VERTICAL);

        recyclerViewRepositories.addItemDecoration(dividerItemDecoration);

        bind(userRepository);

    }

    private void bind(UserRepository userRepository) {

        Picasso.with(getBaseContext())
                .load(userRepository.getUser().getUserPhoto())
                .placeholder(R.drawable.ic_placeholder)
                .transform(new BorderedCircleTransform())
                .into(imageViewUser);

        textViewUserId.setText(userRepository.getUser().getUsername());

        recyclerViewRepositories.setAdapter(adapter);

        adapter.setRepositories(userRepository.getRepositories());
        if (userRepository.getRepositories().isEmpty()) {
            emptyView.setVisibility(View.VISIBLE);
        }

    }

    @Click(R.id.home_button)
    public void onClick() {

        super.onBackPressed();

    }

}
