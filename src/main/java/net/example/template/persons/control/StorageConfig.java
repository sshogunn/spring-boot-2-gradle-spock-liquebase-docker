package net.example.template.persons.control;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class StorageConfig {
    private final String storageAccount;
    private final String storageKey;

    public StorageConfig(
            @Value("${cloud.azure.storage.account}") String storageAccount,
            @Value("${cloud.azure.storage.key}") String storageKey) {
        this.storageAccount = storageAccount;
        this.storageKey = storageKey;
    }

    String buildConnectionString() {
        return String.format("DefaultEndpointsProtocol=http;AccountName=%s;AccountKey=%s", storageAccount, storageKey);
    }
}
