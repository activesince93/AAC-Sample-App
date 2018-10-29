package com.demo.aacdevfest;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Darshan on 25-10-2018.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */
public class RepoListFragment extends Fragment {

    @BindView(R.id.recyclerViewGithubRepos)
    RecyclerView recyclerViewGithubRepos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        ButterKnife.bind(this, view);

        recyclerViewGithubRepos.setLayoutManager(new LinearLayoutManager(getContext()));

        // Get the room database instance
        GithubRepoDB database = GithubRepoDB.getDatabase(getContext());

        // Getting the List<GithubRepo> from room database
        LiveData<List<GithubRepo>> githubReposLiveData = database.githubRepoDao().getAll();

        // Observe the liveData to receive database updates
        githubReposLiveData.observe(getViewLifecycleOwner(), new Observer<List<GithubRepo>>() {
            @Override
            public void onChanged(@Nullable List<GithubRepo> githubRepos) {
                if (isListEmptyOrNull(githubRepos)) {
                    // Creating one time work request
                    OneTimeWorkRequest otwr = new OneTimeWorkRequest.Builder(GithubRepoWorker.class)
                            .build();

                    // Enqueue work
                    WorkManager.getInstance()
                            .enqueue(otwr);
                }
                GithubReposAdapter adapter = new GithubReposAdapter(getContext(), githubRepos);
                recyclerViewGithubRepos.setAdapter(adapter);
            }
        });
        return view;
    }

    private boolean isListEmptyOrNull(List<GithubRepo> githubRepos) {
        return githubRepos == null || githubRepos.isEmpty();
    }
}
