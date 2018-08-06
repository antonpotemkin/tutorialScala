package anton.potemkin.websocket.Chat

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

import scala.util.{Failure, Success}

object WebSocket extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val config = system.settings.config
  val interface = config.getString("app.interface")
  val port = config.getInt("app.port")
  val router = new Router()

  val binding = Http().bindAndHandle(router.route, interface, port)
  binding.onComplete {
    case Success(serverBinding) ⇒
      val localAddress = serverBinding.localAddress
      println(s"Server is listening on ${localAddress.getHostName}:${localAddress.getPort}")
      println(s"Chat is here ${localAddress.getHostName}:${localAddress.getPort}/chat")
    case Failure(e) ⇒
      println(s"Binding failed with ${e.getMessage}")
      system.terminate()
  }
}
