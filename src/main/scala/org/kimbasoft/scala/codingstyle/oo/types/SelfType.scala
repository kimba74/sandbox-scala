package org.kimbasoft.scala.codingstyle.oo.types

/**
 * Missing documentation
 *
 * @since 1.0
 */
object SelfType {

  trait Persistence { def startPersistence(): Unit }
  trait MidTier { def startMidTier(): Unit }
  trait UI { def startUI(): Unit }

  /**
   * Concrete (as Trait) implementation of the Persistence trait
   */
  trait Database extends Persistence {
    def startPersistence(): Unit = println("Starting Database")
  }

  /**
   * Concrete (as Trait) implementation of the MidTier trait
   */
  trait BizLogic extends MidTier {
    def startMidTier(): Unit = println("Starting BizLogic")
  }

  /**
   * Concrete (as Trait) implementation of the UI trait
   */
  trait WebUI extends UI {
    def startUI(): Unit = println("Starting WebUI")
  }

  /**
   * Declaration of an Application (App) trait that loosely ties together
   * the Persistence, MidTier, and UI traits.
   */
  trait App {
    /* This is the self-type declaration that permits the trait to call
     * the start methods of the other traits by enforcing that every
     * implementation of the "App" trait must also implement the "Persistence",
     * "MidTier", amd "UI" trait. The type can be named anything as long
     * as it 'points' to the implementation (=>). */
    self: Persistence with MidTier with UI =>
    def run() = {
      startPersistence()
      startMidTier()
      startUI()
    }
  }

  /**
   * Declares an implementation of the App trait with concrete implementations
   * of the Persistence, MidTier, and UI traits. If one of them is left out the
   * example won't compile.
   */
  object MyApp extends App with Database with BizLogic with WebUI

  /**
   * Simple example of how self-typed annotation can be used to alias 'this' and
   * make it available to sub-classes.
   */
  class AliasA {
    /* Simple self-type of (aliasing) 'this'. The self-type will make a direct reference
     * to the superclass available to its sub-classes. The self-type name is arbitrary.
     * Every sub-class can also define a self-type of itself to grant access to itself
     * and its (potentially shadowed) members to its sub-classes. */
    selfA =>

    /**
     * Simple talk() method in the main class AliasA
     */
    def talk(message: String): Unit = println("AliasA.talk: " + message)

    class AliasB {
      class AliasC {
        /**
         * Simple talk() method in the sub-class AliasC with the same signature
         * as the method in class AliasA. This method "shadows" the talk() method
         * in AliasA and therefore would not be able to invoke it. The way this
         * addressed is by "aliasing" 'this' in AliasA via self-typed annotation
         * so it can be referenced in shadowed methods.
         */
        def talk(message: String): Unit = selfA.talk("AliasC.talk: " + message)
      }
      val aliasC = new AliasC
    }
    val aliasB = new AliasB
  }


  def main(args: Array[String]) {
    println("-- Cake Pattern (via self-type) --------------")
    MyApp.run()

    println("-- Aliasing 'this' (via self-type) -----------")
    val aliasA = new AliasA
    aliasA.talk("Hello")
    aliasA.aliasB.aliasC.talk("World")
  }
}
