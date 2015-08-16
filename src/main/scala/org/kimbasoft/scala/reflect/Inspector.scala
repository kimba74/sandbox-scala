package org.kimbasoft.scala.reflect

import scala.reflect.runtime.{universe => ru}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Inspector {

  /**
   *
   * @param sym
   * @param indent
   */
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

  /**
   *
   * @param sym
   * @param indent
   */
  private def inspectClass(sym: ru.ClassSymbol, indent: String = ""): Unit = {
    println(s"${indent}class.${sym.name} {")
    for (member <- sym.info.decls) {
      inspect(member, indent + "  ")
    }
    println(s"$indent}")
    // Inspect Companion Object if existing
    if (sym.companion != ru.NoSymbol)
      inspect(sym.companion)
  }

  /**
   *
   * @param sym
   * @param indent
   */
  private def inspectModule(sym: ru.ModuleSymbol, indent: String = ""): Unit = {
    println(indent + sym)
    for (member <- sym.info.decls)
      inspect(member, indent + "  ")
  }

  /**
   *
   * @param sym
   * @param indent
   */
  private def inspectMethod(sym: ru.MethodSymbol, indent: String = ""): Unit = {
    val nIndent = indent + "  "
    println(s"${indent}method.${sym.name} {")

    // Determine visibility
    print(s"${nIndent}visibility = ")
    sym match {
      case p if p.isPrivate   => println("private")
      case p if p.isProtected => println("protected")
      case p if p.isPublic    => println("public")
      case _ => println("unknown")
    }

    // Determine method type
    print(s"${nIndent}type = ")
    sym match {
      case c if c.isConstructor => println("constructor ")
      case s if s.isSetter      => println("setter")
      case g if g.isGetter      => println("getter")
      case _ => println("def")
    }

    println(s"${nIndent}returns = ${sym.returnType}")

    for (paramList <- sym.info.paramLists) {
      println(s"${nIndent}inspecting parameter list {")
      for (param <- paramList)
        inspect(param, nIndent + "  ")
      println(s"$nIndent}")
    }
    println(s"$indent}")
  }

  /**
   *
   * @param sym
   * @param indent
   */
  private def inspectTerm(sym: ru.TermSymbol, indent: String = ""): Unit = {
    val nIndent = indent + "  "
    println(s"${indent}term.${sym.name} {")

    // Determine type
    print(s"${nIndent}writability = ")
    sym match {
      case v if sym.isVal => println("val")
      case v if sym.isVar => println("var")
      case _ => println("unknown")
    }

    // Determine type
    println(s"${nIndent}type = ${sym.typeSignature}")
    println(s"$indent}")
  }

  /**
   *
   * @param sym
   * @param indent
   */
  private def inspectType(sym: ru.TypeSymbol, indent: String = ""): Unit = {
    println(indent + sym)
  }
}
