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

  val clsTestClass  = Testing.getClass.getClassLoader.loadClass(strTestClass)  // Load Java Class for name
  val clsTestTrait  = Testing.getClass.getClassLoader.loadClass(strTestTrait)  // Load Java Class for name
  val clsUTestTrait = Testing.getClass.getClassLoader.loadClass(strUTestTrait) // Load Java Class for name

  println(s"Java: ${clsTestTrait.getName} is assignable from ${clsTestClass.getName} = ${clsTestTrait.isAssignableFrom(clsTestClass)}")
  println(s"Java: ${clsUTestTrait.getName} is assignable from ${clsTestClass.getName} = ${clsUTestTrait.isAssignableFrom(clsTestClass)}")

  // Get Scala RuntimeMirror
  val rm = ru.runtimeMirror(ru.getClass.getClassLoader)

  val sc = rm.staticClass(strTestClass)   // Load Scala ClassSymbol for name
  val rc = rm.runtimeClass(sc)            // Load corresponding Java class for Scala ClassSymbol

  val st = rm.staticClass(strTestTrait)   // Load Scala ClassSymbol for name
  val rt = rm.runtimeClass(st)            // Load corresponding Java class for Scala ClassSymbol

  val sut = rm.staticClass(strUTestTrait) // Load Scala ClassSymbol for name
  val rut = rm.runtimeClass(sut)          // Load corresponding Java class for Scala ClassSymbol

  println(s"Scala: ${rt.getName} is assignable from ${rc.getName} = ${rt.isAssignableFrom(rc)}")
  println(s"Scala: ${rut.getName} is assignable from ${rc.getName} = ${rut.isAssignableFrom(rc)}")
}