package anton.potemkin.websocket.Chat.room

import akka.NotUsed
import akka.actor.{ActorSystem, PoisonPill, Props}
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.stream.OverflowStrategy
import akka.stream.scaladsl.{Flow, Sink, Source}
import anton.potemkin.websocket.Chat._

class ChatRoom(system: ActorSystem) {

  private val chatRoomActor = system.actorOf(Props(classOf[ChatRoomActor]), "Main")

  def flowChat(sender: String): Flow[Message, Message, NotUsed] = {
    val fromWebsocket: Sink[Message, NotUsed] = Flow[Message]
      .map {
        case TextMessage.Strict(txt) => ReceivedMessage(txt)
      }
      .to(Sink.actorRef[ChatEvent](chatRoomActor, UserDisconnected(sender)))

    val toWebSocket: Source[Message, NotUsed] =
      Source.actorRef[ChatMessage](5, OverflowStrategy.fail)
        .mapMaterializedValue { outActor =>
          chatRoomActor ! UserConnected(sender, outActor)
          NotUsed
        }.map{
        case ChatMessage(text) => TextMessage(text)
      }

    Flow.fromSinkAndSource(fromWebsocket, toWebSocket)
  }

}
