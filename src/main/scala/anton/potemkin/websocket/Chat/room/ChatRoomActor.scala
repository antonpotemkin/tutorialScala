package anton.potemkin.websocket.Chat.room

import akka.actor.{Actor, ActorRef, Terminated}
import anton.potemkin.websocket.Chat._

class ChatRoomActor extends Actor {

  private var users: Map[String, ActorRef] = Map.empty
  override def receive: Receive = {
    case UserConnected(sender, actorRef) =>
      users += sender -> actorRef
      broadcast(SystemMessage(s"User $sender joined channel...").toChatMessage)
      println(s"User $sender joined channel...")
    case UserDisconnected(sender) =>
      users -= sender
      broadcast(SystemMessage(s"User with actorRef $sender left channel...").toChatMessage)
      println(s"User with actorRef $sender left channel...")
    case msg: ReceivedMessage =>
      println(s"User ${msg.sender} receive message")
      broadcast(msg.toChatMessage)

  }

  private def broadcast(msq: ChatMessage):Unit = users.values.foreach(_ ! msq)
}
