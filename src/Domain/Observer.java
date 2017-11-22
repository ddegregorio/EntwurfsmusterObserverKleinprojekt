package Domain;

/**
 * Represents an interface that Observers
 * have to implement if they want to be observers.
 */
public interface Observer {

    void notify(Article a);
}
