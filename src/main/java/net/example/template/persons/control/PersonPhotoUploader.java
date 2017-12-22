package net.example.template.persons.control;

import lombok.AllArgsConstructor;
import net.example.template.persons.control.storage.StorageConfigProps;
import net.example.template.persons.control.storage.StorageContainer;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@AllArgsConstructor
public class PersonPhotoUploader {
    private final StorageConfigProps storageConfigProps;
    private final PersonsNotifier personsNotifier;

    public void upload(String personId, InputStream content, long size, String name) {
        StorageContainer container = new StorageContainer(storageConfigProps.buildConnectionString());
        container.init(personId)
                .makePublicAvailable()
                .uploadFile(name, content, size);
        personsNotifier.notifyAboutPhoto();
    }
}
