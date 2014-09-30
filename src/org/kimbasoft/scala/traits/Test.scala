package org.kimbasoft.scala.traits

/**
 * Missing documentation
 *
 * @since 1.0
 */
trait Test {
  def <  (that:Any): Boolean
  def <= (that:Any): Boolean = this < that || this == that
  def >  (that:Any): Boolean = !(this <= that)
  def >= (that:Any): Boolean = !(this < that)
}
