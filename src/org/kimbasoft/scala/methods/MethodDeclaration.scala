package org.kimbasoft.scala.methods

/**
 * Missing documentation
 *
 * @since 1.0
 */
object MethodDeclaration {

  /**
   * Fully fledged function declaration with return type (: String), body ({...}), and return statement.
   */
  def functionA1(name:String): String = {
    return "Hello " + name
  }

  /**
   * Return statements are redundant as Scala determines the last value to be the return value.
   * So let's drop the return statement
   */
  def functionA2(name:String): String = {
      "Hello " + name
  }

  /**
   * Since this function is very simple and consists only of a single expression rather than
   * a complex body we are not required to hold the implementation body in a {...} block.
   * Removing the block and making a one-liner out of it.
   */
  def functionA3(name:String): String = "Hello " + name

  /**
   * Since the last (and only statement) is a String the return type of this function is inferred
   * to be of type String. So we can omit the declaration of a return type in the function's signature.
   *
   * NOTE:
   * For APIs it is not recommended to drop the return type declaration as different types of return
   * types could be inferred by future implementations, which could lead to broken clients utilizing
   * our API.
   */
  def functionA4(name:String) = "Hello " + name

  /**
   * Shortened declaration of a function that does not have any parameters. When declaring a function
   * with empty parentheses the caller can choose between calling the function with or without parentheses
   * as well:
   *    functionB1()   or   functionB1
   */
  def functionB1() = "Hello world"

  /**
   * Since the function does not have any parameters Scala does not require the developers to provide
   * empty braces. We therefore drop the braces to further simplify the function definition. When declaring
   * a function without parentheses the caller can only call the function without specifying parentheses:
   *    functionB2
   */
  def functionB2 = "Hello world"

  /**
   * A standard way of providing a parameter default value for a function is to implement the most
   * elaborate method taking the required parameter first...
   */
  def functionC1(name:String) = "Hello " + name

  /**
   * ...then implement a second function, "overloading" the first but not accepting any parameters.
   * This function will then call the first, more elaborate function with a default value as parameter.
   * (Example where an overloaded function needs to specify a result type since it cannot safely be
   * inferred by the overloading function call)
   */
  def functionC1: String = functionC1("world")

  /**
   * Scala lets the developer specify sensitive default values for parameter, which will be used in case
   * the user of the method does not specify the parameter.
   */
  def functionC2(name:String = "world") = "Hello " + name

  /**
   * Scala also knows the notion of "named parameter". This allows the API user to specify the parameter
   * in any order as long as they specify the name of the parameter (see. main). This in combination with
   * the afore mentioned "default values" permits for the specification of anyone (or combination) of the
   * parameter, in any order.
   */
  def functionD(name:String = "unknown", age:Int = -1, location:String = "unknown"): Unit = {
    println(name + "(age:" + age + ") located at " + location)
  }

  def main(args: Array[String]) {
    /* Function is being called solely with default values */
    functionD()
    /* Function is called with only the name, the other parameters use default values */
    functionD("John Doe")
    /* Function is called with name and age, location is using default value */
    functionD("John Doe", 36)
    /* Function is being called with all three parameter, no default value is being applied */
    functionD("John Doe", 36, "Baltimore, MD")
    /* Function is called with age only, name and location will fall back to default values */
    functionD(age = 36)
    /* Function is called with location only, name and age will fall back to default values */
    functionD(location = "San Francisco, CA")
    /* function is being called will all parameter but in a different order than suggested by function signature */
    functionD(location = "Miami, FL", name = "Simon Something", age = 52)

  }
}
