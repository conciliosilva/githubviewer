package concilio.githublist.view.item;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import concilio.githublist.R;
import concilio.githublist.model.Repository;

/**
 * Created by concilio on 14/11/17.
 */

@EViewGroup(R.layout.item_user)
public class RepositoryItemView extends RelativeLayout {

    @ViewById
    TextView textViewRepositoryName;

    @ViewById
    TextView textViewLanguageName;

    public RepositoryItemView(Context context) {
        super(context);
    }

    public void bind(Repository repository) {

        textViewRepositoryName.setText(repository.getRepositoryName());

        textViewLanguageName.setText(repository.getLanguage());

    }
}
