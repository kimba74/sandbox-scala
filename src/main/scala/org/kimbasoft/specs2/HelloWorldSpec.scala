package org.kimbasoft.specs2

import org.specs2.Specification

/**
 * Missing documentation
 *
 * @since 1.0
 */
class HelloWorldSpec extends Specification {
  def is = s2"""
  This is a Specification to check the 'Hello World' String

  The 'Hello World' String should
    contain 11 characters         $e1
    start with 'Hello'            $e2
    end with 'World'              $e3
                                  """
  val string = "Hello World"
  def e1 = string must have size 11
  def e2 = string must startWith("Hello")
  def e3 = string must endWith("World")
}
