package com.company.hibernate.storage;

import java.util.List;

public interface CrudService <T,I,S> {

    void create(T t) throws Exception;
    T getById(I id);
    void modify(I id, S s) throws Exception;
    void deleteById(I id);
    List<T> getAll();
}
