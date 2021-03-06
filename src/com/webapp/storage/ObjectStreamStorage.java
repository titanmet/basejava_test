package com.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import com.webapp.storage.serializer.StreamSerializer;

import java.io.*;

public class ObjectStreamStorage implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(r);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try(ObjectInputStream ois = new ObjectInputStream(is)) {
            return (Resume) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw  new StorageException("Error read resume", null, e);
        }
    }
}
