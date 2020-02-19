import data.flyway.FlywayIntegration
import data.persistence.PG
import server.HttpServer
import service.HttpService

/**
 * main application to run
 */
object StoreMain extends App with HttpService with HttpServer with FlywayIntegration with PG
