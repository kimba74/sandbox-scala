package org.kimbasoft.scala.codingstyle.functional

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object CallBy {

  /**
   * The 'amount' argument passed to this method will be evaluated
   * before being passed to the method, thus appearing "static" to
   * the method. It is called a Call-by-Value parameter and the
   * default behavior.
   */
  def callByValue(amount: Int): Unit = {
    println("Call-by-Value: " + amount)
  }

  /**
   * The 'amount' argument of this method on the other hand will be
   * evaluated inside the method's body when it is being accessed.
   * therefore being "dynamic" to the method. These types of arguments
   * are referred to as Call-by-Name parameter.
   */
  def callByName(amount: => Int): Unit = {
    println("Call-by-Name : " + amount)
  }

  /**
   * While the difference between the two styles is not quite clear in
   * the above two methods consider the following custom implementation
   * of a while loop. The following method is defined as a Tail Call and
   * therefore calls itself. It passes the 'condition' argument to itself.
   * If we were to drop the arrow in the 'condition' declaration the value
   * of 'condition' would be evaluated BEFORE being passed into the method
   * and will never be evaluated again even if it was changed within the
   * function 'f' passed to the method. A loop implemented using this
   * custom method would therefore never finish (if condition evaluated to
   * 'true') or would never start (if condition evaluated to 'false')
   *
   * NOTE: Try removing the '=>' and run example again.
   */
  def myWhile(condition: => Boolean)(f: => Unit): Unit = {
    if (condition) {
      f
      myWhile(condition)(f)
    }
  }

  def main(args: Array[String]) {
    println("-- Call by Name / Call by Value ----")
    callByValue(12)
    callByName(24)

    println("-- Custom While Loop ---------------")
    var count = 0
    myWhile(count < 5) {  // Also notice how the curried function is passed via {} and not ()
      println("Iteration " + count + ": Still looping")
      count += 1
    }
  }
}
