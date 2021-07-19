package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.List;


/**
 * Array based storage for Resumes
 */
public interface Storage {

    void clear();

    void update(Resume r);

    void save(Resume r);

    Resume get(String uuid) throws IllegalAccessException;

    void delete(String uuid);

    List<Resume> getAllSorted() throws IllegalAccessException;

    int size();
}
