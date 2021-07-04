package com.webapp.storage;

import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {
    protected abstract SK getSearchKey(String uuid);
    protected abstract void doUpdate(Resume r, SK searchKey);
    protected abstract boolean isExist(SK searchKey);
    protected abstract void doSave(Resume r, SK searchKey);
    protected abstract Resume doGet(SK searchKey);
    protected abstract void doDelete(SK searchKey);
    protected abstract List<Resume> doCopyAll();

    @Override
    public void update(Resume r) {
        SK searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    private SK getExistedSearchKey(String uuid){
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid){
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public void save(Resume r) {
        SK searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        SK searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public void delete(String uuid) {
        SK searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }
}
