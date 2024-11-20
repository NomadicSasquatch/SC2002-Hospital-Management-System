package interfaces;

import java.util.List;
public interface ICrudRepository<T, ID> {
    List<T> loadRepository();
    void saveRepository();
    void addItem(T data);
    void removeItem(ID id);
    List<T> getDataById(ID id);
    List<T> getAllData();
}
