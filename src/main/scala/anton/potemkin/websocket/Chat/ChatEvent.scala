package anton.potemkin.websocket.Chat

import akka.actor.ActorRef

case class ChatMessage(message: String) extends ChatEvent

sealed trait ChatEvent

case class UserConnected(sender: String, actor: ActorRef) extends ChatEvent

case class UserDisconnected(sender: String) extends ChatEvent

//todo добавить обработку имени
case class ReceivedMessage(sender: String, message: String) extends ChatEvent {
  def toChatMessage: ChatMessage = ChatMessage(s"[receive]: $message")
}

case class SystemMessage(message: String) extends ChatEvent {
  def toChatMessage: ChatMessage = ChatMessage(s"[System]: $message")
}

case class SentMessage(message: String) extends ChatEvent