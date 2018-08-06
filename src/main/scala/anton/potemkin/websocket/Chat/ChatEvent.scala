package anton.potemkin.websocket.Chat

import akka.actor.ActorRef

case class ChatMessage(message: String) extends ChatEvent

sealed trait ChatEvent

case class UserConnected(name: String, actor: ActorRef) extends ChatEvent

case class UserDisconnected(name: String) extends ChatEvent

case class ReceivedMessage(sender: String, message: String) extends ChatEvent {
  def toChatMessage: ChatMessage = ChatMessage(s"[$sender]: $message")
}

case class SystemMessage(message: String) extends ChatEvent {
  def toChatMessage: ChatMessage = ChatMessage(s"[System]: $message")
}
