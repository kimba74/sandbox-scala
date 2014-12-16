package org.kimbasoft.scala.codingstyle.functional.implicits

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object UseCase4 {

  implicit class CustStringContext(sc: StringContext) {
    def cust(values: Any*) = "Custom: " + sc.parts
  }

  def main(args: Array[String]) {
    val fname = "John"
    val lname = "Doe"
    println(cust"My first name is $fname, my last is $lname")
  }
}
