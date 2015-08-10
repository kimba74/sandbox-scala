package org.kimbasoft.scala.reflect

import scala.reflect.runtime.{universe => ru}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Testing extends App {

  val strTestClass  = "org.kimbasoft.scala.reflect.TestClass"
  val strTestTrait  = "org.kimbasoft.scala.reflect.TestTrait"
  val strUTestTrait = "org.kimbasoft.scala.reflect.UberTestTrait"

  val clsTestClass  = Testing.getClass.getClassLoader.loadClass(strTestClass )
  val clsTestTrait  = Testing.getClass.getClassLoader.loadClass(strTestTrait )
  val clsUTestTrait = Testing.getClass.getClassLoader.loadClass(strUTestTrait)

  println(s"Java: ${clsTestTrait.getName} is assignable from ${clsTestClass.getName} = ${clsTestTrait.isAssignableFrom(clsTestClass)}")
  println(s"Java: ${clsUTestTrait.getName} is assignable from ${clsTestClass.getName} = ${clsUTestTrait.isAssignableFrom(clsTestClass)}")

  // Get runtime mirror
  val rm = ru.runtimeMirror(ru.getClass.getClassLoader)

  val sc = rm.staticClass(strTestClass)
  val rc = rm.runtimeClass(sc)

  val st = rm.staticClass(strTestTrait)
  val rt = rm.runtimeClass(st)

  val sut = rm.staticClass(strUTestTrait)
  val rut = rm.runtimeClass(sut)

  println(s"Scala: ${rt.getName} is assignable from ${rc.getName} = ${rt.isAssignableFrom(rc)}")
  println(s"Scala: ${rut.getName} is assignable from ${rc.getName} = ${rut.isAssignableFrom(rc)}")
}