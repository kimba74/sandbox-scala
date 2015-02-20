package org.kimbasoft.scala.codingstyle.oo.types

/**
 * Missing documentation
 *
 * @since 1.0
 */
object CompoundType {

  /**
   * Abstract base class. This class will be used as the basis of the
   * new compound type. This does not have to be an abstract class, it
   * can also be a concrete class or another trait.
   */
  abstract class World(val name: String = "World")

  /**
   * Mixin Trait extending the abstract base class 'World'. This extension
   * is not required for Compound Types but in this example the traits
   * make use of the 'name' field of the base class and therefore have to
   * extend the base class.
   */
  trait Greeting extends World {
    def sayHello() = println(s"Hello $name!")
  }

  /**
   * Mixin Trait extending the abstract base class 'World'. This extension
   * is not required for Compound Types but in this example the traits
   * make use of the 'name' field of the base class and therefore have to
   * extend the base class.
   */
  trait GoodBye extends World {
    def sayGoodBye() = println(s"Good-Bye, $name!")
  }


  /**
   * Regular type extending another type and mixing in some traits.
   */
  class PoliteWorld(override val name: String) extends World(name) with Greeting with GoodBye

  def main(args: Array[String]) {
    val polite1 = new PoliteWorld("Bob")
    polite1.sayHello()
    polite1.sayGoodBye()

    /* Compound type creating a new type on the fly by instantiating an existing type and mixing
     * in the traits of the fly.*/
    val polite2 = new World with Greeting with GoodBye
    polite2.sayHello()
    polite2.sayGoodBye()
  }

}
