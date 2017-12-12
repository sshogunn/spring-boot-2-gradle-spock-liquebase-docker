package net.example.template

import com.ninja_squad.dbsetup.DbSetupTracker
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import javax.sql.DataSource

@SpringBootTest
@ActiveProfiles("test")
abstract class AbstractContextRequiredSpec extends Specification {
    static def dbSetupTracker = new DbSetupTracker()
    @Autowired
    protected DataSource dataSource
}