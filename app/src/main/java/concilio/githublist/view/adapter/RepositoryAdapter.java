package concilio.githublist.view.adapter;

import android.content.Context;
import android.view.ViewGroup;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;

import concilio.githublist.model.Repository;
import concilio.githublist.view.item.RepositoryItemView;
import concilio.githublist.view.item.RepositoryItemView_;

/**
 * Created by concilio on 14/11/17.
 */

@EBean
public class RepositoryAdapter extends RecyclerViewAdapterBase<Repository, RepositoryItemView> {

    @RootContext
    Context context;


    public void setRepositories(List<Repository> repositories) {

        items.clear();
        items.addAll(repositories);
        notifyDataSetChanged();

    }

    @Override
    protected RepositoryItemView onCreateItemView(ViewGroup parent, int viewType) {

        return RepositoryItemView_.build(context);

    }

    @Override
    public void onBindViewHolder(ViewWrapper<RepositoryItemView> holder, int position) {

        Repository repository = items.get(position);

        holder.getView().bind(repository);

    }
}
