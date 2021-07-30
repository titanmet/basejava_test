package com.webapp.storage;

import com.webapp.exception.NotExistStorageException;
import com.webapp.model.Resume;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());
    protected abstract SK getSearchKey(String uuid);
    protected abstract void doUpdate(Resume r, SK searchKey);
    protected abstract boolean isExist(SK searchKey);
    protected abstract void doSave(Resume r, SK searchKey);
    protected abstract Resume doGet(SK searchKey) throws IllegalAccessException;
    protected abstract void doDelete(SK searchKey);
    protected abstract List<Resume> doCopyAll() throws IllegalAccessException;

    @Override
    public void update(Resume r) {
        LOG.info("Update " + r);
        SK searchKey = getExistedSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    private SK getExistedSearchKey(String uuid){
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid){
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public void save(Resume r) {
        LOG.info("Save " + r);
        SK searchKey = getNotExistedSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public Resume get(String uuid) throws IllegalAccessException {
        LOG.info("Get " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = null;
        try {
            list = doCopyAll();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Collections.sort(list);
        return list;
    }
}
