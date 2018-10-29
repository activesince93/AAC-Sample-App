package com.demo.aacdevfest;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Darshan on 27-10-2018.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */
@Dao
interface GithubRepoDao {
    @Insert
    void insertAll(List<GithubRepo> githubRepos);

    @Insert
    void insert(GithubRepo githubRepo);

    @Query("SELECT * FROM repository")
    LiveData<List<GithubRepo>> getAll();
}
