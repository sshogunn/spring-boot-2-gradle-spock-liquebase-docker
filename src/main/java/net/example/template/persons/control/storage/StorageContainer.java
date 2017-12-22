package net.example.template.persons.control.storage;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

public class StorageContainer {
    private final String connectionString;
    private CloudBlobContainer container;

    public StorageContainer(String connectionString) {
        this.connectionString = connectionString;
    }

    public StorageContainer init(String name) {
        try {
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(connectionString);
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
            container = blobClient.getContainerReference(name);
            container.createIfNotExists();
            return this;
        } catch (StorageException | URISyntaxException | InvalidKeyException e) {
            throw new AzureBlobStorageException("Container cannot be created", e);
        }
    }

    public StorageContainer makePublicAvailable() {
        BlobContainerPermissions containerPermissions = new BlobContainerPermissions();
        containerPermissions.setPublicAccess(BlobContainerPublicAccessType.CONTAINER);
        try {
            container.uploadPermissions(containerPermissions);
        } catch (StorageException e) {
            throw new AzureBlobStorageException("Container cannot be changed to public access mode", e);
        }
        return this;
    }

    public void uploadFile(String name, InputStream file, long size) {
        try {
            CloudBlockBlob blob = container.getBlockBlobReference(name);
            blob.getMetadata().put("size", String.valueOf(size));
            blob.upload(file, size);
        } catch (URISyntaxException | StorageException | IOException e) {
            throw new AzureBlobStorageException("File cannot be uploaded", e);
        }
    }

    private static class AzureBlobStorageException extends RuntimeException {
        AzureBlobStorageException(String s, Exception e) {
            super(s, e);
        }
    }
}
