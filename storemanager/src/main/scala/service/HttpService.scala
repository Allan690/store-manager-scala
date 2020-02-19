package service

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import data.persistence.{DB, UserComponent}
import routes.UserRoutes

import scala.concurrent.ExecutionContext

trait HttpService extends UserComponent with DB {
  implicit lazy val system: ActorSystem = ActorSystem()
  implicit lazy val ec: ExecutionContext = system.dispatcher

  lazy val userRepo: UserRepository = new UserRepository
  lazy val routes: Route = new UserRoutes(userRepo).routes
}
