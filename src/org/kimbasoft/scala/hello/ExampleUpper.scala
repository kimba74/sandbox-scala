package org.kimbasoft.scala.hello

/**
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object ExampleUpper {

  def main(args: Array[String]) {
    println("upper1> " + ExampleUpper.upper1("This", "is", "a", "test", "sentence"))
    println("upper2> " + ExampleUpper.upper2("This", "is", "a", "test", "sentence"))
    println("upper3> " + ExampleUpper.upper3("This", "is", "a", "test", "sentence"))
  }

  /**
   * Fully qualified function (method with return type) declaration.
   * @param strings
   * @return
   */
  def upper1(strings:String*): Seq[String] = {
    strings.map((s:String) => s.toUpperCase)
  }

  /**
   * Return type and curly braces were removed since this function only has one line in its body,
   * which also determines the return type of the function.
   * @param strings
   * @return
   */
  def upper2(strings:String*) = strings.map((s:String) => s.toUpperCase)

  /**
   * The function parameter was removed since there only is one. To still be able to reference the
   * parameter the underscore is being used as a wildcard (?)
   * @param strings
   * @return
   */
  def upper3(strings:String*) = strings.map(_.toUpperCase)
}
