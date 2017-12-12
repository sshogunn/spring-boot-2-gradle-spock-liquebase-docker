package net.example.template

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.ContextConfiguration

/**
 * Uses the  data source configured in special configuration class. In our case
 * we use Embedded PostgresSQL database. Although tests are slower than with embedded H2
 * database, they allow to check DB specific aspects.
 */
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
@ContextConfiguration(classes = [DataSourceConfiguration])
class AbstractManualDSRepositorySpec extends AbstractAutoDSRepositorySpec {}