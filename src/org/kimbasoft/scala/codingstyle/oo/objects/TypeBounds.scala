package org.kimbasoft.scala.codingstyle.oo.objects

import scala.language.implicitConversions

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object TypeBounds {

  class MySuper
  class MyClass extends MySuper
  class MySubCl extends MyClass

  abstract class Decorator[T] {
    def decorate(obj: T): String
  }

  implicit object MySuperDecorator extends Decorator[MySuper] {
    def decorate(obj: MySuper): String = "Decorated MySuper: " + obj.toString
  }

  implicit object MyClassDecorator extends Decorator[MyClass] {
    def decorate(obj: MyClass): String = "Decorated MyClass: " + obj.toString
  }

  implicit object MySubDecorator extends Decorator[MySubCl] {
    def decorate(obj: MySubCl): String = "Decorated MySubCl: " + obj.toString
  }

  case class Wrapper(value: Any) {
    def process: String = "--> " + value + " <--"
  }

  implicit def fromMySuper(my: MySuper): Wrapper = Wrapper(my)

  implicit def fromMyClass(my: MyClass): Wrapper = Wrapper(my)

  implicit def fromMySubCl(my: MySubCl): Wrapper = Wrapper(my)


  class Boundaries[P1] {
    /**
     *
     */
    def upperBound[A<:P1](param: A) = println("Upper Bound: " + param.getClass)

    /**
     *
     */
    def lowerBound[B>:P1](param: B): B = { println("Lower Bound: " + param.getClass); param }

    /**
     * see Implicitly
     */
    def contextBound[C <: P1 : Decorator](obj: C): Unit = println(implicitly[Decorator[C]].decorate(obj))

    /**
     * see ImplicitConversions
     */
    def viewBound[D <: P1 <% Wrapper](param: D): Unit = println(param.process)
  }


  def main(args: Array[String]) {
    val mySuper = new MySuper
    val myClass = new MyClass
    val mySubCl = new MySubCl

    val bounds: Boundaries[MyClass] = new Boundaries[MyClass]

    println("-- Upper Bounds ------------------------------")

    /* Will Work:
     * */
    bounds.upperBound(mySubCl)

    /* Will Work:
     * */
    bounds.upperBound(myClass)

    /* Won't Work:
     * */
//  bounds.upperBound(mySuper)

    println("-- Lower Bounds ------------------------------")

    /* Will Work:
     * */
    val boundSuper: MySuper = bounds.lowerBound(mySuper)

    /* Will Work:
     * */
    val boundClass: MyClass = bounds.lowerBound(myClass)

    /* Won't Work:
     * */
//  val boundSubCl: MySubCl = bounds.lowerBound(mySubCl)

    println("-- Context Bounds ----------------------------")

    /* Will Work:
     * */
    bounds.contextBound(mySubCl)

    /* Will Work:
     * */
    bounds.contextBound(myClass)

    /* Won't Work:
     * */
//  bounds.contextBound(mySuper)

    println("-- View Bounds -------------------------------")

    /* Will Work:
     * */
    bounds.viewBound(mySubCl)

    /* Will Work:
     * */
    bounds.viewBound(myClass)

    /* Won't Work:
     * */
//  bounds.viewBound(mySuper)
  }
}
