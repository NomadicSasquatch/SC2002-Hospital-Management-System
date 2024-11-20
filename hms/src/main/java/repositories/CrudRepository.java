package abstract_class;

import interfaces.ICrudRepository;
import utils.CSVReader;
import utils.CSVWriter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public abstract class CrudRepository<T, ID> implements ICrudRepository<T, ID> {
    protected String filePath;
    protected List<T> items;

    public CrudRepository(String filePath) {
        this.filePath = filePath;
        this.items = loadRepository();
    }

    @Override
    public List<T> loadRepository() {
        List<String[]> records = CSVReader.readCSV(filePath);
        List<T> itemList = new ArrayList<>();
        for (String[] record : records) {
            T item = fromCSV(record);
            itemList.add(item);
        }
        return itemList;
    }
    @Override
    public void saveRepository() {
        List<String[]> records = new ArrayList<>();
        for (T item : items) {
            records.add(toCSV(item));
        }
        CSVWriter.writeCSV(filePath, records);
    }

    @Override
    public void addItem(T item) {
        items.add(item);
        saveRepository();
    }

    @Override
    public void removeItem(ID item) {
        items.removeIf(i -> getId(i).equals(item));
        saveRepository();
    }

    @Override
    public List<T> getDataById(ID id) {
        return items.stream()
                .filter(i -> getId(i).equals(id))
                .collect(Collectors.toList());
    }

    @Override
    public List<T> getAllData() {
        return new ArrayList<>(items);
    }

    protected abstract T fromCSV(String[] record);

    protected abstract String[] toCSV(T item);

    protected abstract ID getId(T item);
}
