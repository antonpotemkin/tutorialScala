package anton.potemkin.cakePattern

trait NameProviderComponentImpl extends NameProviderComponent{
  class NameProviderImpl extends NameProvider {
    def getName: String = "World"
  }
}
