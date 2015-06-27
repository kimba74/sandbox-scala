package org.kimbasoft.scala.reflect

import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.universe.ClassSymbol
import scala.reflect.runtime.universe.TypeTag

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

  inspectClass(classTest)

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


  def getTypeOf[T: TypeTag](obj: T) = ru.typeOf[T]

  def inspectClass(clazz: ClassSymbol) = {
    println(clazz)
    for (member <- clazz.info.decls) {
      println(s" > $member")
    }
  }
}
