package com.demo.aacdevfest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Darshan on 25-10-2018.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */
public class RepoDetailsFragment extends Fragment {

    @BindView(R.id.txtCreatedAt)
    TextView txtCreatedAt;

    @BindView(R.id.txtName)
    TextView txtName;

    @BindView(R.id.txtDescription)
    TextView txtDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_details, container, false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        if(bundle != null) {
            GithubRepo githubRepo = (GithubRepo) bundle.getSerializable("REPOSITORY");
            if(githubRepo != null) {
                txtName.setText(githubRepo.name);
                txtDescription.setText(githubRepo.description);
                txtCreatedAt.setText(githubRepo.createdAt);
            }
        }
        return view;
    }
}
