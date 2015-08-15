package org.kimbasoft.scala.reflect

import scala.reflect.runtime.{universe => ru}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Testing extends App {

  val strTC = "org.kimbasoft.scala.reflect.TestClass"
  val strTT = "org.kimbasoft.scala.reflect.TestTrait"
  val strUT = "org.kimbasoft.scala.reflect.UberTestTrait"

  // Java Classes
  val jcTC = Testing.getClass.getClassLoader.loadClass(strTC) // Load Java Class for name
  val jcTT = Testing.getClass.getClassLoader.loadClass(strTT) // Load Java Class for name
  val jcUT = Testing.getClass.getClassLoader.loadClass(strUT) // Load Java Class for name

  println(s"Java : ${jcTT.getName} is assignable from ${jcTC.getName} = ${jcTT.isAssignableFrom(jcTC)}")
  println(s"Java : ${jcUT.getName} is assignable from ${jcTC.getName} = ${jcUT.isAssignableFrom(jcTC)}")

  // Get Scala RuntimeMirror
  val rm = ru.runtimeMirror(ru.getClass.getClassLoader)

  // Scala ClassSymbol and RuntimeClasses
  val scTC = rm.staticClass(strTC) // Load Scala ClassSymbol for name
  val rcTC = rm.runtimeClass(scTC) // Load corresponding Java class for Scala ClassSymbol

  val scTT = rm.staticClass(strTT) // Load Scala ClassSymbol for name
  val rcTT = rm.runtimeClass(scTT) // Load corresponding Java class for Scala ClassSymbol

  val scUT = rm.staticClass(strUT) // Load Scala ClassSymbol for name
  val rcUT = rm.runtimeClass(scUT) // Load corresponding Java class for Scala ClassSymbol

  println(s"Scala: ${rcTT.getName} is assignable from ${rcTC.getName} = ${rcTT.isAssignableFrom(rcTC)}")
  println(s"Scala: ${rcUT.getName} is assignable from ${rcTC.getName} = ${rcUT.isAssignableFrom(rcTC)}")
}