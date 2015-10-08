package org.kimbasoft.scala.reflect

import scala.reflect.runtime.{universe => ru}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Inspector {


  val increment = "  "

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
    val nIndent = incrementIndent(indent)

    val classType: PartialFunction[ru.ClassSymbol, String] = {
      case c if c.isCaseClass         => "caseClass"
      case c if c.isDerivedValueClass => "customValueClass"
      case t if t.isTrait             => "trait"
      case _ => "class"
    }

    formatName(sym, classType, indent)

    println(s"${nIndent}alternatives        = ${sym.alternatives}")
    println(s"${nIndent}baseClasses         = ${sym.baseClasses}")
    println(s"${nIndent}isAbstract          = ${sym.isAbstract}")
    println(s"${nIndent}isAliasType         = ${sym.isAliasType}")
    println(s"${nIndent}isDerivedValueClass = ${sym.isDerivedValueClass}")
    println(s"${nIndent}isExistential       = ${sym.isExistential}")
    println(s"${nIndent}isFinal             = ${sym.isFinal}")
    println(s"${nIndent}isModuleClass       = ${sym.isModuleClass}")

    for (member <- sym.info.decls) inspect(member, nIndent)

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
    val nIndent = incrementIndent(indent)

    formatName(sym, PartialFunction[ru.ModuleSymbol,String](_ => "object"), indent)

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
    val nIndent = incrementIndent(indent)

    // Determine method type
    val defType: PartialFunction[ru.MethodSymbol, String] = {
      // Check if method is a constructor
      case cst if cst.isConstructor => {
        cst match {
          case p if p.isPrimaryConstructor => "constructor.primary"
          case c if c.isConstructor => "constructor"
        }
      }
      // Check if method is an accessor
      case acs if acs.isAccessor  => {
        // Check type of accessor
        acs match {
          case s if s.isSetter => "setter"
          case g if g.isGetter => "getter"
        }
      }
      // Default check, method is just regular method
      case _ => "def"
    }
    formatName(sym, defType, indent)

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
    val nIndent = incrementIndent(indent)

    // Determine type
    val termType: PartialFunction[ru.TermSymbol, String] = {
      case v if sym.isVal => "val"
      case v if sym.isVar => "var"
      case _ => "term"
    }

    formatName(sym, termType, indent)

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
    val nIndent = incrementIndent(indent)

    formatName(sym, PartialFunction[ru.TypeSymbol, String](_ => "type"), indent)

    for(member <- sym.info.decls) inspect(member, nIndent)

    println(s"$indent}")
  }

  /**
   *
   * @param indent
   * @param increment
   * @return
   */
  private def incrementIndent(indent: String, increment: String = increment): String = indent + increment

  /**
   *
   * @param sym
   * @return
   */
  private def resolveVisibility(sym: ru.Symbol): String = {
    sym match {
      case p if p.isPrivate   => "private"
      case p if p.isProtected => "protected"
      case p if p.isPublic    => "public"
      case _ => "unknown"
    }
  }

  private def formatName[A<:ru.Symbol](sym: A, typ: PartialFunction[A, String], indent: String): Unit = {
    val symType: PartialFunction[A, String] = typ orElse { case _ => "unknown" }
    println(s"$indent${symType(sym)}[${resolveVisibility(sym)}].${sym.name} {")
  }
}
