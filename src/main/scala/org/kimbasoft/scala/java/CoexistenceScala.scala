package org.kimbasoft.scala.java

/**
 * Missing documentation
 *
 * @since 1.0
 */
object CoexistenceScala extends App {
  val java = new HelloWorldJava
  val scala = new HelloWorldScala

  /* Without parameter */
  println("-- Without Parameter ---------------")
  println(java.helloWorld())
  println(scala.helloWorld())

  /* With parameter */
  println("-- With Parameter ------------------")
  println(java.helloWorld("John Doe"))
  println(scala.helloWorld("John Doe"))
}
