package anton.potemkin.websocket.Chat

import akka.actor.ActorSystem
import anton.potemkin.websocket.Chat.room.ChatRoom
import akka.http.scaladsl.server.Directives._


class Router(implicit system: ActorSystem) {
  val chatRoom = new ChatRoom(system)

  val route = get {
    pathSingleSlash {
      getFromResource("public/index.html")
    } ~ path("chat") {
      parameter('name) { name =>
        handleWebSocketMessages(chatRoom.flowChat(name))
      }
    }
  }
}
