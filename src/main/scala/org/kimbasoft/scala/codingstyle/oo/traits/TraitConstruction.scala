package org.kimbasoft.scala.codingstyle.oo.traits

/**
 * Missing documentation
 *
 * @since 1.0
 */
object TraitConstruction extends App {

  /**
   * Base Class
   */
  class BaseClass {
    println( "   in BaseClass: z = " + z )
    val z = "BaseClass"
    println( "   in BaseClass: z = " + z )
  }

  /**
   * Extension Trait
   */
  trait Trait1 {
    println( "   in Trait1: x = " + x )
    val x = 1
    println( "   in Trait1: x = " + x )
  }

  /**
   * Extension Trait
   */
  trait Trait2 {
    println( "   in Trait2: y = " + y )
    val y = "T2"
    println( "   in Trait2: y = " + y )
  }

  /**
   * Final Class extending Base Class and Traits
   */
  class FinalClass extends BaseClass with Trait1 with Trait2 {
    println( "   in FinalClass: a = " + a )
    val a = "BaseClass"
    println( "   in FinalClass: a = " + a )
  }


  println("Pre-Creation FinalClass:")
  val fc = new FinalClass
  println("Post-Creation FinalClass:")
}
