package com.webapp.storage;

import com.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> map = new HashMap<>();

    @Override
    protected Object getSearchKey(String uuid) {
      return uuid;
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    @Override
    protected void doDelete(Object searchKey) {
    }


    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Resume[] getAll() {
        return null;
    }

    @Override
    public int size() {
        return map.size();
    }
}
