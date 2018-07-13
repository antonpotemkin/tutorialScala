package anton.potemkin.cakePattern

trait SayHelloComponent {
  val sayHelloService: SayHelloService

  trait SayHelloService {
    def sayHello: Unit
  }
}
