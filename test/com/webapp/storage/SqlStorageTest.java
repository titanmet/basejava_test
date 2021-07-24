package com.webapp.storage;

import com.webapp.Config;
import com.webapp.storage.serializer.XmlStreamSerializer;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(Config.get().getStorage());
    }
}