package com.demo.aacdevfest;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Darshan on 27-10-2018.
 *
 * @author Darshan Parikh (parikhdarshan36@gmail.com)
 */
@Entity(tableName = "repository")
public class GithubRepo implements Serializable {
    /**
     * Will get error if primary key not defined
     * error: An entity must have at least 1 field annotated with @PrimaryKey
     */
    @PrimaryKey
    @ColumnInfo(name = "id") // For Room
    @SerializedName("id") // For Gson
    long id;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    String name;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    String description;

    @ColumnInfo(name = "created_at")
    @SerializedName("created_at")
    String createdAt;

    public GithubRepo(long id, String name, String description, String createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }
}
