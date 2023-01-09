package ba.unsa.etf.rpr.domain;

/**
 * Interface that forces POJO beans to have ID field
 */
public interface Idable {
    void setId(int id);

    int getId();
}
