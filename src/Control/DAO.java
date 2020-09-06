package Control;

public interface DAO<T> {
public void load();
public boolean delete(T t);
public boolean add(T t);
}
