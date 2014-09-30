package org.kimbasoft.scala.hello

/**
 * Missing documentation
 *
 * @since 1.0
 */
object Hello {

  val helloJ = new HelloJava()
  val helloS = new HelloScala()

  def main(args: Array[String]): Unit = {
    println("Hello, world! This is the Main class")
    println(helloJ)
    println(helloS)
  }

}
