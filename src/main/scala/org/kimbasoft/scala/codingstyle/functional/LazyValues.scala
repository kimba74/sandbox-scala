package org.kimbasoft.scala.codingstyle.functional

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object LazyValues {

  trait AbstractTrait {
    println("In AbstractTrait:")
    val value: Int
    // Will be initialized during initialization of Trait
    val stat_inverse = { println("  initializing stat_inverse!"); 1.0/value }
    // Will be initialized during first usage
    lazy val lazy_inverse = { println("  initializing lazy_inverse!"); 1.0/value }
  }

  def main(args: Array[String]) {
    val con = new AbstractTrait {
      val value: Int = 10
    }
    println("In con: ")
    println("  con.value = " + con.value + ", con.stat_inverse=" + con.stat_inverse)
    println("  con.value = " + con.value + ", con.lazy_inverse=" + con.lazy_inverse)
  }
}
