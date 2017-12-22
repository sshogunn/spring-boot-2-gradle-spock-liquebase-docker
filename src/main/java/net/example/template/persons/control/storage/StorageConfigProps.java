package net.example.template.persons.control.storage;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class StorageConfigProps {
    private final String storageAccount;
    private final String storageKey;

    public StorageConfigProps(
            @Value("${cloud.azure.storage.account}") String storageAccount,
            @Value("${cloud.azure.storage.key}") String storageKey) {
        this.storageAccount = storageAccount;
        this.storageKey = storageKey;
    }

    public String buildConnectionString() {
        return String.format("DefaultEndpointsProtocol=http;AccountName=%s;AccountKey=%s", storageAccount, storageKey);
    }
}
