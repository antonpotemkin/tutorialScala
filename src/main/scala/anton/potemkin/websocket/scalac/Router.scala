package anton.potemkin.websocket.scalac

import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.stream.scaladsl.Flow
import akka.http.scaladsl.server.Directives._


class Router {

  val echoService: Flow[Message, Message, _] = Flow[Message].map {
    case TextMessage.Strict(txt) => TextMessage("ECHO " + txt)
    case _ => TextMessage("Message type unsupported")
  }

  val route = get {
    pathEndOrSingleSlash {
      complete("Wellcome")
    } ~ path("ws-echo") {
      handleWebSocketMessages(echoService)
    }
  }
}
