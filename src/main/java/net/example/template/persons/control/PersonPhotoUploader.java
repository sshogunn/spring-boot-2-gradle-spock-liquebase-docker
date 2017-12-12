package net.example.template.persons.control;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;


@Service
@AllArgsConstructor
public class PersonPhotoUploader {
    private final StorageConfig storageConfig;

    public void upload(String personId, InputStream content, long size, String name) {
        StorageContainer container = new StorageContainer(storageConfig.buildConnectionString());
        container.init(personId)
                .makePublicAvailable()
                .uploadFile(name, content, size);
    }
}
