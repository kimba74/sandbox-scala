package org.kimbasoft.scala.codingstyle.oo.types

/**
 * Missing documentation
 *
 * @since 1.0
 */
object CompoundType {

  trait Greeting extends World {
    def sayHello() = println(s"Hello $name!")
  }

  trait GoodBye extends World {
    def sayGoodBye() = println(s"Good-Bye, $name!")
  }

  class World(val name: String = "World")

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
