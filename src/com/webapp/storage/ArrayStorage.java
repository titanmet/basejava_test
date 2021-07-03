package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.List;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void fillDeletedElement(int index) {
        storage[index] = storage[size-1];
    }

    @Override
    protected void insertElement(Resume r, int index) {
        storage[size] = r;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    protected Integer getSearchKey(String uuid){
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())){
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<Resume> getAllSorted() {
        return null;
    }
}
