package anton.potemkin.cakePattern

trait NameProviderComponent {
  val nameProvider: NameProvider

  trait NameProvider {
    def getName: String
  }
}

