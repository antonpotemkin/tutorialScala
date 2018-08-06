package anton.potemkin.websocket.Chat.room

import java.text.SimpleDateFormat
import java.util.Date

import akka.actor.{Actor, ActorRef}
import anton.potemkin.websocket.Chat._
import org.slf4j.{Logger, LoggerFactory}

class ChatRoomActor extends Actor {
  val logger: Logger = LoggerFactory.getLogger(this.getClass)

  private var users: Map[String, ActorRef] = Map.empty
  override def receive: Receive = {
    case UserConnected(sender, actorRef) =>
      users += sender -> actorRef
      broadcast(SystemMessage(s"User $sender joined channel").toChatMessage)
      logger.info(s"User $sender joined channel")
    case UserDisconnected(sender) =>
      users -= sender
      broadcast(SystemMessage(s"User with actorRef $sender left channel").toChatMessage)
      logger.info(s"User with actorRef $sender left channel")
    case msg: ReceivedMessage =>
      val message = msg.message
      val sender = msg.sender
      if (isCommand(message)) {
        logger.info(s"User $sender send command $message")
        val response = getCommandMessage(message)
        val actorRef = users(sender)
        sendMessage(SystemMessage(response).toChatMessage, actorRef)
      } else {
        logger.info(s"User $sender send message: $message")
        broadcast(msg.toChatMessage)
      }

  }

  private def broadcast(msq: ChatMessage):Unit = users.values.foreach(_ ! msq)
  private def sendMessage(msg: ChatMessage, actorRef: ActorRef): Unit = actorRef ! msg

  def isCommand(txt: String): Boolean = txt.startsWith("/")

  private def getCommandMessage(txt: String) = txt.substring(1) match {
    case "now" =>
      val dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm aa")
      val submittedDateConvert = new Date()
      dateFormatter.format(submittedDateConvert)
    case _ => "Unknow Command"
  }
}
