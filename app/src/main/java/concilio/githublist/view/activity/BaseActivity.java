package concilio.githublist.view.activity;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.EventBusException;

import concilio.githublist.R;

/**
 * Created by concilio on 21/11/17.
 */

public class BaseActivity extends AppCompatActivity {


    private static final String TAG = "BaseActivity";

    @Override
    protected void onStart() {
        super.onStart();

        try {
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this);
            }
        } catch (EventBusException e) {
            Log.e(TAG, "onStart: ", e);
        }

    }

    @Override
    protected void onStop() {
        super.onStop();

        try {
            if (EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().unregister(this);
            }
        } catch (EventBusException e) {
            Log.e(TAG, "onStart: ", e);
        }
    }

    public MaterialDialog showMaterialDialog(@StringRes int msg) {

        return new MaterialDialog.Builder(this)
                .content(msg)
                .positiveText(R.string.ok)
                .show();

    }

}
