package data.flyway

import config.StoreManagerConfig
import org.flywaydb.core.Flyway

import scala.util.Try


class FlywayService(jdbcUrl: String, dbUser: String, dbPassword: String) {
  private [this] val flyway = new Flyway()
  flyway.setDataSource(jdbcUrl, dbUser, dbPassword)


  def migrateDatabaseSchema(): Int = Try (flyway.migrate()).getOrElse {
    flyway.repair()
    flyway.migrate()
  }

  def dropDatabase(): Unit = flyway.clean()
}

trait FlywayIntegration extends StoreManagerConfig {
  val flywayService = new FlywayService(jdbcUrl, dbUser, dbPassword)
  flywayService.migrateDatabaseSchema()
}
