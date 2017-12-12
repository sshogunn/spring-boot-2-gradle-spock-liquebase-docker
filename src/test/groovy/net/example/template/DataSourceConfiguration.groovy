package net.example.template

import com.opentable.db.postgres.embedded.EmbeddedPostgres
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

import javax.sql.DataSource

@Configuration
class DataSourceConfiguration {

    @Bean
    @Profile("test")
    DataSource dataSource() throws IOException {
        EmbeddedPostgres postgres = EmbeddedPostgres.builder().start()
        return postgres.getPostgresDatabase()
    }
}
