package concilio.githublist.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by concilio on 20/11/17.
 */


public class User implements Serializable {

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String userPhoto;


    public User(String username, String userPhoto, Repository[] repositories) {
        this.username = username;
        this.userPhoto = userPhoto;
    }

    public String getUsername() {
        return username;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

}
