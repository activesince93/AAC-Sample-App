package com.demo.aacdevfest;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Darshan on 28-10-2018.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */
public class GithubReposAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<GithubRepo> githubRepos;

    public GithubReposAdapter(Context context, List<GithubRepo> githubRepos) {
        this.context = context;
        this.githubRepos = githubRepos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_github_repo, viewGroup, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(viewHolder instanceof ItemHolder) {
            ItemHolder holder = (ItemHolder) viewHolder;
            GithubRepo githubRepo = githubRepos.get(i);

            holder.txtCreatedAt.setText(githubRepo.createdAt);
            holder.txtName.setText(githubRepo.name);
            holder.txtDescription.setText(githubRepo.description);
        }
    }

    @Override
    public int getItemCount() {
        return githubRepos.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.rootLayout)
        ConstraintLayout rootLayout;

        @BindView(R.id.txtCreatedAt)
        TextView txtCreatedAt;

        @BindView(R.id.txtName)
        TextView txtName;

        @BindView(R.id.txtDescription)
        TextView txtDescription;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            rootLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("REPOSITORY", githubRepos.get(getAdapterPosition()));
                    Navigation.findNavController(view).navigate(R.id.action_repoListFragment_to_repoDetailsFragment, bundle);
                }
            });
        }
    }
}
