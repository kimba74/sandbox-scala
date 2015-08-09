package org.kimbasoft.scala.reflect

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Testing extends App {

  val testClass  = Testing.getClass.getClassLoader.loadClass("org.kimbasoft.scala.reflect.TestClass")
  val testTrait  = Testing.getClass.getClassLoader.loadClass("org.kimbasoft.scala.reflect.TestTrait")
  val uTestTrait = Testing.getClass.getClassLoader.loadClass("org.kimbasoft.scala.reflect.UberTestTrait")

  println(s"${testTrait.getName} is assignable from ${testClass.getName} = ${testTrait.isAssignableFrom(testClass)}")
  println(s"${uTestTrait.getName} is assignable from ${testClass.getName} = ${uTestTrait.isAssignableFrom(testClass)}")

}