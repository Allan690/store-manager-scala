package server

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import config.StoreManagerConfig

import scala.concurrent.{ExecutionContext, Future}
import scala.io.StdIn

trait HttpServer extends StoreManagerConfig {
  implicit val system: ActorSystem
  implicit val ec: ExecutionContext
  def routes: Route
  val bind: Future[ServerBinding] = Http().bindAndHandle(routes, httpHost, httpPort)
  println(s"Server online at http://localhost:${httpPort}/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bind
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done
}

