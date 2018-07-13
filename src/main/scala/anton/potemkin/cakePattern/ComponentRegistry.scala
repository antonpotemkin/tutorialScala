package anton.potemkin.cakePattern

object ComponentRegistry extends SayHelloComponentImpl with NameProviderComponentImpl {
  val nameProvider = new NameProviderImpl
  val sayHelloService = new SayHelloServiceImpl
}
