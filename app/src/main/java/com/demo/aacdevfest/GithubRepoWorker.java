package com.demo.aacdevfest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * Created by Darshan on 27-10-2018.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */
public class GithubRepoWorker extends Worker {

    private static final String TAG = GithubRepoWorker.class.getSimpleName();

    public GithubRepoWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e(TAG, "Worker class called");

        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL("https://api.github.com/users/activesince93/repos");
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();

            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";

            Type listType = new TypeToken<List<GithubRepo>>() {}.getType();
            List<GithubRepo> githubRepos = new Gson().fromJson(result, listType);

            // Get the room database instance
            GithubRepoDB database = GithubRepoDB.getDatabase(getApplicationContext());

            // Insert list of repos into database
            database.githubRepoDao().insertAll(githubRepos);
            return Result.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Result.FAILURE;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
