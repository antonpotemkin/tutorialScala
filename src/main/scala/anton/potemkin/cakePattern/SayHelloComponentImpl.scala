package anton.potemkin.cakePattern

trait SayHelloComponentImpl extends SayHelloComponent {
  this: SayHelloComponentImpl with NameProviderComponent =>

  class SayHelloServiceImpl extends SayHelloService {
    def sayHello: Unit = println(s"Hello, ${nameProvider.getName}")
  }

}
