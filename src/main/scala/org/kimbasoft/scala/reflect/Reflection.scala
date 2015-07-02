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
    // Inspect Companion Object if existing
    if (sym.companion != ru.NoSymbol)
      inspect(sym.companion)
  }

  def inspectModule(sym: ru.ModuleSymbol, indent: String = ""): Unit = {
    println(indent + sym)
    for (member <- sym.info.decls)
      inspect(member, indent + "  ")
  }

  final protected def test(): Unit = {}

  def inspectMethod(sym: ru.MethodSymbol, indent: String = ""): Unit = {
    // Determine visibility
    var visible: String = ""
    sym match {
      case p if p.isPrivate   => visible = "private "
      case p if p.isProtected => visible = "protected "
      case _ => visible = ""
    }
    // Determine method type
    var prefix: String = ""
    sym match {
      case c if c.isConstructor => prefix = "constructor "
      case s if s.isSetter      => prefix = "setter "
      case g if g.isGetter      => prefix = "getter "
      case _ => prefix = "def "
    }
    // Determine name
    val name = sym.name.decodedName
    // Determine return value
    val retVal = sym.returnType

    println(indent + visible + prefix + name + ": " + retVal)
//    for (member <- sym.info.decls)
//      inspect(member, indent + "  ")
  }

  def inspectTerm(sym: ru.TermSymbol, indent: String = ""): Unit = {
    // Determine type
    var termType = ""
    sym match {
      case v if sym.isVal => termType = "val "
      case v if sym.isVar => termType = "var "
      case _ => termType = "??? "
    }
    // Determine name
    val name = sym.name.decodedName
    // Determine type
    val retVal = sym.typeSignature

    println(indent + termType + name + ": " + retVal)
  }

  def inspectType(sym: ru.TypeSymbol, indent: String = ""): Unit = {
    println(indent + sym)
  }
}
