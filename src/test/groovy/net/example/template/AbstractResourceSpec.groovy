package net.example.template

import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(classes = [DataSourceConfiguration.class, TemplateApplication.class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AbstractResourceSpec extends AbstractContextRequiredSpec {
    @Autowired
    TestRestTemplate restTemplate
    def sql

    def setup() {
        sql = new Sql(dataSource)
    }
}