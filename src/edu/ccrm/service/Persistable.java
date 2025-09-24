package ccrm.service;

public interface Persistable<T> {
    void saveToFile(T obj, String file);
    T loadFromFile(String file);
}
