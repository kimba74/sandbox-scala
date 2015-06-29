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

  inspect(classTest)

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


  def inspect(sym: ru.Symbol, indent: String = "") = {
    sym match {
      case c: ru.ClassSymbol  => inspectClass(c, indent)
      case m: ru.ModuleSymbol => inspectModule(m, indent)
      case m: ru.MethodSymbol => inspectMethod(m, indent)
      case t: ru.TermSymbol   => inspectTerm(t, indent)
      case t: ru.TypeSymbol   => inspectType(t, indent)
      case u                  => println(s"Unknown Symbol: $u")
    }
  }

  def inspectClass(sym: ru.ClassSymbol, indent: String = ""): Unit = {
    println(indent + sym)
    for (member <- sym.info.decls) {
      inspect(member, indent + "  ")
    }
  }

  def inspectModule(sym: ru.ModuleSymbol, indent: String = ""): Unit = {
    println(indent + sym)
    for (member <- sym.info.decls)
      inspect(member, indent + "  ")
  }

  def inspectMethod(sym: ru.MethodSymbol, indent: String = ""): Unit = {
    println(indent + sym)
    for (member <- sym.info.decls)
      inspect(member, indent + "  ")
  }

  def inspectTerm(sym: ru.TermSymbol, indent: String = ""): Unit = {
    println(indent + sym)
  }

  def inspectType(sym: ru.TypeSymbol, indent: String = ""): Unit = {
    println(indent + sym)
  }
}
