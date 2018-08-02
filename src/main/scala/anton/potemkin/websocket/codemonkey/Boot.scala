package anton.potemkin.websocket.codemonkey

import akka.NotUsed
import akka.actor.{ActorSystem, PoisonPill, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.stream.{ActorMaterializer, OverflowStrategy}
import akka.stream.scaladsl.{Flow, Sink, Source}
import akka.http.scaladsl.server.Directives._

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.io.StdIn

object Boot extends App {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  val chatRoom = system.actorOf(Props(new ChatRoom), "chat")

  def newUser(): Flow[Message, Message, NotUsed] = {
    val userActor = system.actorOf(Props(new User(chatRoom)))
    val incomingMessage: Sink[Message, NotUsed] =
      Flow[Message].map {
        // transform websocket message to domain message
        case TextMessage.Strict(text) => User.IncomingMessage(text)
      }.to(Sink.actorRef[User.IncomingMessage](userActor, PoisonPill))

    val outgoingMessage: Source[Message, NotUsed] =
      Source.actorRef[User.OutgoingMessage](10, OverflowStrategy.fail)
      .mapMaterializedValue { outActor =>
        // give the user actor a way to send messages out
      userActor ! User.Connected(outActor)
        NotUsed
      }.map((outMsg: User.OutgoingMessage) => TextMessage(outMsg.text))
    // then combine both to a flow

    Flow.fromSinkAndSource(incomingMessage, outgoingMessage)
  }

  val route =
    path("chat") {
      get {
        handleWebSocketMessages(newUser())
      }
    }

  val binding = Await.result(Http().bindAndHandle(route, "127.0.0.1", 8080), 3.seconds)


  // the rest of the sample code will go here
  println("Started server at 127.0.0.1:8080, press enter to kill server")
  StdIn.readLine()
  system.terminate()
}
