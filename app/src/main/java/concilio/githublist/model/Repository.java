package concilio.githublist.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by concilio on 20/11/17.
 */

public class Repository implements Serializable {

    @SerializedName("name")
    private String repositoryName;

    @SerializedName("language")
    private String language;

    public Repository(String repositoryName, String language) {
        this.repositoryName = repositoryName;
        this.language = language;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
