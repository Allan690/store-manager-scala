package routes
import data.persistence.{JsonProtocol, UserComponent}
import data.model.User
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContext


class UserRoutes(repo: UserComponent#UserRepository)(implicit ex: ExecutionContext)  extends JsonProtocol{

  val routes: Route = pathPrefix("user"){
    pathEndOrSingleSlash {
      get {
        println(repo.all)
        complete(repo.all)
      } ~ post {
        entity(as[User]) { u =>
          complete(repo.insert(u))
        }
      }
    }
  }
}
