package concilio.githublist.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by concilio on 21/11/17.
 */

public class UserRepository implements Serializable {

    private User user;

    private List<Repository> repositories;

    public UserRepository(User user, List<Repository> repositories) {
        this.user = user;
        this.repositories = repositories;
    }

    public User getUser() {
        return user;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

}
