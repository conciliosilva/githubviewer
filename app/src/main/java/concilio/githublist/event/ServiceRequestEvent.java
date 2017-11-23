package concilio.githublist.event;

/**
 * Created by concilio on 21/11/17.
 */

public class ServiceRequestEvent {

    public enum Type {

        REQUEST_SUCCESS,
        REQUEST_FAILURE,

    }

    private Type type;
    private Object data;

    public ServiceRequestEvent(Type type, Object data) {
        this.type = type;
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}
