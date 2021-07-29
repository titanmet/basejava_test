package com.webapp;

import com.webapp.storage.SqlStorage;
import com.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();
    protected static final File PROPS = new File(".\\storage\\");
    private final File storageDir;
    private final Storage storage;

    private Config() {
        try (InputStream is = new FileInputStream("./config/resumes.properties")) {
            Properties props = new Properties();
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
            storage = new SqlStorage(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
        } catch (IOException e) {
           throw  new IllegalStateException("Invalid config file " + PROPS.getAbsolutePath());
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public static Config get() {
        return INSTANCE;
    }

    public File getStorageDir() {
        return storageDir;
    }
}