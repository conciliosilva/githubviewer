package concilio.githublist.view.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.greenrobot.eventbus.Subscribe;

import concilio.githublist.R;
import concilio.githublist.event.ServiceRequestEvent;
import concilio.githublist.model.UserRepository;
import concilio.githublist.service.UserService;

@EActivity(R.layout.activity_search)
public class SearchActivity extends BaseActivity {

    @ViewById
    protected MaterialEditText searchUser;

    @ViewById
    protected Button buttonSearch;

    @ViewById
    protected ProgressBar progressSearch;

    @Bean
    protected UserService service;

    private AlertDialog alertDialog;

    private long mLastClickTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        service = new UserService();
    }

    @AfterViews
    void afterViews() {

    }

    @Click(R.id.button_search)
    void clickButton() {

        String name = searchUser.getText().toString();

        if (!name.isEmpty() &&
                SystemClock.elapsedRealtime() - mLastClickTime > 1000) {

            mLastClickTime = SystemClock.elapsedRealtime();
            showProgressBar();

            service.getUser(name);

        }

    }

    @Subscribe
    public void onEvent(ServiceRequestEvent event) {

        hideProgressBar();

        if (event.getType().equals(ServiceRequestEvent.Type.REQUEST_SUCCESS)) {

            UserRepository userRepository = (UserRepository) event.getData();

            UserRepositoriesActivity_.intent(this).extra("userRepository", userRepository).start();

        } else {

            showMaterialDialog((Integer) event.getData());

        }

    }

    private void showProgressBar() {

        View view = getLayoutInflater().inflate(R.layout.view_progress_dialog, null);

        alertDialog = new AlertDialog.Builder(this).create();

        alertDialog.setView(view);
        alertDialog.setCancelable(true);
        alertDialog.show();

    }

    private void hideProgressBar() {

        alertDialog.dismiss();

    }

}
