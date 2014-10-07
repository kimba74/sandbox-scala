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
  
  def upper1(strings:String*): Seq[String] = {
    strings.map((s:String) => s.toUpperCase)
  }
  
  def upper2(strings:String*) = strings.map((s:String) => s.toUpperCase)

  def upper3(strings:String*) = strings.map(_.toUpperCase)
}
