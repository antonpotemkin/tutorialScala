package anton.potemkin.websocket.scalac

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.io.StdIn

object Server extends App {

  implicit val actorSystem = ActorSystem("ws")
  implicit val flowMaterialier = ActorMaterializer()

  val interface = "localhost"
  val port = 8888

  val route = new Router().route

  val binding = Http().bindAndHandle(route, interface, port)
  println(s"Server is now online at http://$interface:$port\nPress RETURN to stop...")
  StdIn.readLine()
  actorSystem.terminate()

}
