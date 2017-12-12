package net.example.template

import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigurationPackage
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.transaction.TestTransaction

/**
 * Uses auto configured data source. By default it is H2 embedded database.
 * Although it allows to run very tests very fast, it does not allow to check
 * some specific database(f.e PostgresSQl, MySQL, SQL Server) aspects.
 */
@DataJpaTest
@AutoConfigurationPackage
class AbstractAutoDSRepositorySpec extends AbstractContextRequiredSpec {
    @Autowired
    protected TestEntityManager em
    protected Sql sql

    def setup() {
        sql = new Sql(dataSource)
    }

    def <T> void executeAndCommit(T tr, closure) {
        closure(tr)
        TestTransaction.flagForCommit()
        TestTransaction.end()
    }

    def executeAndCommit(Closure closure) {
        closure()
        TestTransaction.flagForCommit()
        TestTransaction.end()
    }
}