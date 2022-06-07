package model;

/**
 * Interface for making new observers that handle an update in any double. We've got a simple toString that
 * is implemented in all extensions.
 */
public interface Observer {
    void update(double amount);

    public String totalToString();
}
