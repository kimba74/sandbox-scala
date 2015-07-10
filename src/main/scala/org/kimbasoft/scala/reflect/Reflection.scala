package org.kimbasoft.scala.reflect

import scala.reflect.runtime.{universe => ru}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Reflection extends App {

  // Getting the runtime mirror for the provided ClassLoader
  val runMirror = ru.runtimeMirror(getClass.getClassLoader)

  // Loading a specified class based on the class' fully qualified name
  val classTest   = runMirror.staticClass("org.kimbasoft.scala.reflect.TestClass")
  val classMirror = runMirror.reflectClass(classTest)

  // Loading the primary constructor of the loaded class
  val constTest   = classTest.primaryConstructor.asMethod
  val constMirror = classMirror.reflectConstructor(constTest)

  // Inspecting the default constructor
  println("Inspecting constructor parameters")
  for(symList <- constTest.info.paramLists) {
    for(symbol <- symList)
      println(s"  > ${symbol.name}: ${symbol.info}")
  }

  // Create instance of loaded class via primary constructor
  val instTest = constMirror("MyTest")

  // Invoke toString() on dynamically loaded class
  println(instTest)

  // Using Inspector to inspect class
  Inspector.inspect(classTest)

  val tpe = getTypeTag(instTest)
  println(s"tpe  = ${tpe.tpe}")

  val ttpe = testTypeTag[String]
  println(s"ttpe = $ttpe")

  def getTypeTag[T : ru.TypeTag](obj: T) = ru.typeTag[T]

  def testTypeTag[T](implicit obj: ru.TypeTag[T]) = obj.tpe
}
