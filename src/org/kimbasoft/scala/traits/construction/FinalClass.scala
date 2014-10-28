package org.kimbasoft.scala.traits.construction

/**
 * Missing documentation
 *
 * @since 1.0
 */
class FinalClass extends BaseClass with Trait1 with Trait2 {
  println( "   in FinalClass: a = " + a )
  val a = "BaseClass"
  println( "   in FinalClass: a = " + a )
}
