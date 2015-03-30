package org.kimbasoft.scala.codingstyle.oo.traits

import scala.collection.mutable.ArrayBuffer

/**
 * Use Case for Stackable Trait Pattern
 *
 *                +-----------+
 *                |    Base   |
 *                +-----------+
 *                      |
 *              +-------+-------+
 *              |               |
 *        +-----------+   +-----------+
 *        |   Core    |   | Stackable |
 *        +-----------+   +-----------+
 *
 * Base: Defines the basic functionality as interface.
 *
 * Core: Defines a core implementation of the functionality outlined in the Base.
 *
 * Stackable: Defines full or partial functionality outlined in the Base. This Trait
 *       can call 'super()' in the overridden Base functions as long as it declares
 *       its own function 'abstract overridden'. A call to super() is only allowed in
 *       stackable Traits as long as it is mixed in AFTER another trait or class that
 *       provides a concrete implementation of the function.
 *
 * See article: http://www.artima.com/scalazine/articles/stackable_trait_pattern.html
 */
object UseCase extends App {

  /**
   * Base (Abstract Class or Trait)
   */
  abstract class QueueInt {
    def put(value: Int)
    def get(): Int
  }

  /**
   * Core (Concrete Class or Trait)
   */
  class BasicQueueInt extends QueueInt {
    private val buffer = new ArrayBuffer[Int]()
    override def put(value: Int) = buffer += value
    override def get(): Int = buffer remove 0
    override def toString: String = buffer.toString()
  }

  /**
   * Stackable (Trait)
   */
  trait EvenNumbersOnly extends QueueInt {
    abstract override def put(value: Int) = if(value % 2 == 0) super.put(value)
  }

  /**
   * Stackable (Trait)
   */
  trait OddNumbersOnly extends QueueInt {
    abstract override def put(value: Int) = if(value %2 == 1) super.put(value)
  }

  /**
   * Composed class
   */
  class OddQueueInt extends BasicQueueInt with OddNumbersOnly


  // Stacking Traits on-the-fly
  val even = new BasicQueueInt with EvenNumbersOnly
  for (i <- 1 to 10)
    even.put(i)
  println("Even Only: " + even)

  // Stacking Traits via Composed Class
  val odds = new OddQueueInt
  for (i <- 1 to 10)
    odds.put(i)
  println("Odds Only: " + odds)
}
