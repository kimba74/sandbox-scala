package org.kimbasoft.scala.reflect

import scala.reflect.runtime.{universe => ru}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Reflection extends App {

  //---- Instantiation of a dynamically loaded class ----

  // Getting the runtime mirror for the provided ClassLoader
  val mirClassLoader = ru.runtimeMirror(getClass.getClassLoader)

  // Loading the ClassSymbol for a specified class based on the class' fully qualified name
  val symTestClass = mirClassLoader.staticClass("org.kimbasoft.scala.reflect.TestClass")

  // Getting the runtime mirror for the provided ClassSymbol
  val mirTestClass = mirClassLoader.reflectClass(symTestClass)

  // Getting the runtime class object for the provided ClassSymbol
  val clsTestClass = mirClassLoader.runtimeClass(symTestClass)

  // Loading the MethodSymbol for the primary constructor of the specified ClassSymbol
  val symConstruct = symTestClass.primaryConstructor.asMethod

  // Getting the runtime mirror for the provided MethodSymbol of the primary constructor
  val mirConstruct = mirTestClass.reflectConstructor(symConstruct)

  // Create instance of loaded class via runtime mirror of the primary constructor
  val objTestClass = mirConstruct("MyTest", 10)


  //---- Inspection of the previously instantiated class ----

  // Retrieving Java Runtime Class for Scala ClassSymbol
  println(s"Getting Java Class object for ClassSymbol")
  println(s"  > $clsTestClass")

  // Invoke toString() of the instance of the dynamically loaded class
  println("Calling toString()")
  println(s"  > $objTestClass")

  // Inspecting the parameter list of the MethodSymbol of the default constructor
  println("Inspecting constructor parameters")
  for(symList <- symConstruct.info.paramLists) {
    for(symbol <- symList)
    println(s"  > ${symbol.name}: ${symbol.info}")
  }

  // Inspecting the base classes of the ClassSymbol
  println("Inspecting base classes")
  for (tsym <- symTestClass.baseClasses)
    println(s"  > ${tsym.fullName}")

  // Check if dynamically loaded class is of type TestClass
  println("Checking if class is of type 'TestClass'")
  println(s"  > ${symTestClass.toType =:= ru.typeOf[TestClass]}")

  println("\n-- Inspection --------------------------------------------")
  // Using Inspector to inspect ClassSymbol
  Inspector.inspect(symTestClass)

  println("\n-- Analysis ----------------------------------------------")
  // Using Analyzer to analyze ClassSymbol
  Analyzer.analyze(symTestClass, "sym")

  println("\n-- Tests -------------------------------------------------")
  println(s"symTestClass =:= ru.typeOf[TestClass] = ${symTestClass.toType =:= ru.typeOf[TestClass]}")
  println(s"symTestClass =:= ru.typeOf[TestTrait] = ${symTestClass.toType =:= ru.typeOf[TestTrait]}")

  println(s"objTestClass isInstanceOf[TestClass]  = ${objTestClass.isInstanceOf[TestClass]}")
  println(s"objTestClass isInstanceOf[TestTrait]  = ${objTestClass.isInstanceOf[TestTrait]}")

  println("\n-- Types -------------------------------------------------")
  println(s"symTestClass.asType  = ${symTestClass.asType}")
  println(s"symTestClass.toType  = ${symTestClass.toType}")

  println("\n-- TypeTags ----------------------------------------------")
  println(s".asType.tpe = ${getTypeTag(symTestClass.asType).tpe}")
  println(s".toType.tpe = ${getTypeTag(symTestClass.toType).tpe}")

  //-----------------------------------------------------------------
  def getTypeTag[T : ru.TypeTag](obj: T) = ru.typeTag[T]

  def testTypeTag[T](implicit obj: ru.TypeTag[T]) = obj.tpe
}
