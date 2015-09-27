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
    val nIndent = indent + "  "
    print(s"$indent")
    sym match {
      case c if c.isCaseClass         => print("caseClass")
      case c if c.isDerivedValueClass => print("customValueClass")
      case t if t.isTrait             => print("trait")
      case _ => print("class")
    }
    println(s".${sym.name} {")

    println(s"${nIndent}alternatives        = ${sym.alternatives}")
    println(s"${nIndent}baseClasses         = ${sym.baseClasses}")
    println(s"${nIndent}isAbstract          = ${sym.isAbstract}")
    println(s"${nIndent}isAliasType         = ${sym.isAliasType}")
    println(s"${nIndent}isDerivedValueClass = ${sym.isDerivedValueClass}")
    println(s"${nIndent}isExistential       = ${sym.isExistential}")
    println(s"${nIndent}isFinal             = ${sym.isFinal}")
    println(s"${nIndent}isModuleClass       = ${sym.isModuleClass}")
    for (member <- sym.info.decls) {
      inspect(member, nIndent)
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
    println(s"${indent}object.${sym.name} {")
    for (member <- sym.info.decls)
      inspect(member, indent + "  ")
    println(s"$indent}")
  }

  /**
   *
   * @param sym
   * @param indent
   */
  private def inspectMethod(sym: ru.MethodSymbol, indent: String = ""): Unit = {
    val nIndent = indent + "  "
    print(s"${indent}")

    // Determine method type
    sym match {
      // Check if method is a constructor
      case cst if cst.isConstructor => {
        cst match {
          case p if p.isPrimaryConstructor => print("constructor.primary")
          case c if c.isConstructor => print("constructor")
        }
      }
      // Check if method is an accessor
      case acs if acs.isAccessor  => {
        // Check type of accessor
        acs match {
          case s if s.isSetter => print("setter")
          case g if g.isGetter => print("getter")
        }
        // Check field accessed
//        println(s"${nIndent}accessed           = ${sym.accessed}")
      }
      // Default check, method is just regular method
      case _ => print("def")
    }
    println(s".${sym.name} {")

    // Determine visibility
    print(s"${nIndent}visibility         = ")
    sym match {
      case p if p.isPrivate   => println("private")
      case p if p.isProtected => println("protected")
      case p if p.isPublic    => println("public")
      case _ => println("unknown")
    }

    // Inspect MethodSymbol information
    println(s"${nIndent}isAbstract         = ${sym.isAbstract}")
    println(s"${nIndent}isAbstractOverride = ${sym.isAbstractOverride}")
    println(s"${nIndent}param access       = ${sym.isParamAccessor}")
    println(s"${nIndent}overloaded         = ${sym.isOverloaded}")  // Does not seem to recognize an overloaded method
    println(s"${nIndent}synthetic          = ${sym.isSynthetic}")
    println(s"${nIndent}varargs            = ${sym.isVarargs}")
    println(s"${nIndent}returns            = ${sym.returnType}")

    if (sym.alternatives.nonEmpty)
      println(s"${nIndent}alternatives       = ${sym.alternatives}")

    for (paramList <- sym.info.paramLists) {
      println(s"${nIndent}parameter-list {")
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

    // Determine type
    print(s"$indent")
    sym match {
      case v if sym.isVal => print("val")
      case v if sym.isVar => print("var")
      case _ => print("term")
    }

    // Determine visibility
    print(s"[")
    sym match {
      case p if p.isPrivate   => print("private")
      case p if p.isProtected => print("protected")
      case p if p.isPublic    => print("public")
      case _ => print("unknown")
    }
    println(s"].${sym.name} {")

    // Inspect type descriptors
    println(s"${nIndent}signature    = ${sym.typeSignature}")
    println(s"${nIndent}stable       = ${sym.isStable}")
    println(s"${nIndent}lazy         = ${sym.isLazy}")
    println(s"${nIndent}parameter    = ${sym.isParameter}")
    println(s"${nIndent}by name      = ${sym.isByNameParam}")
    println(s"${nIndent}with default = ${sym.isParamWithDefault}")
    println(s"$indent}")
  }

  /**
   *
   * @param sym
   * @param indent
   */
  private def inspectType(sym: ru.TypeSymbol, indent: String = ""): Unit = {
    println(s"${indent}type.${sym.name}")
  }
}
