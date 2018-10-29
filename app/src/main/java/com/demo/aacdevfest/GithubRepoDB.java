package com.demo.aacdevfest;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Darshan on 27-10-2018.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */
@Database(entities = {GithubRepo.class}, version = 1)
public abstract class GithubRepoDB extends RoomDatabase {
    public abstract GithubRepoDao githubRepoDao();

    private static volatile GithubRepoDB INSTANCE;

    static GithubRepoDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GithubRepoDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), GithubRepoDB.class, "GithubRepoDB")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}