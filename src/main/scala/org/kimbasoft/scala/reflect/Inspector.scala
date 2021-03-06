package org.kimbasoft.scala.reflect

import scala.reflect.runtime.{universe => ru}

/**
 * Inspector class that takes a given Symbol and analyzes it recursively.
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Inspector {


  val increment = "  "

  /**
    * Facade method that determines which helper method will inspect
    * the provided Symbol.
    * @param sym The Symbol to inspect
    * @param indent The indent prefix to use for all printed output.
    */
  def inspect(sym: ru.Symbol, indent: String = "") = {
    // Determine type of Symbol
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
   * Helper method that inspects a ClassSymbol object.
   * @param sym The ClassSymbol to inspect
   * @param indent The indent prefix to use for all printed output.
   */
  private def inspectClass(sym: ru.ClassSymbol, indent: String = ""): Unit = {
    val nIndent = incrementIndent(indent)

    // Determine type of Class
    val classType: PartialFunction[ru.ClassSymbol, String] = {
      case c if c.isCaseClass         => "caseClass"
      case c if c.isDerivedValueClass => "customValueClass"
      case t if t.isTrait             => "trait"
      case _ => "class"
    }

    formatName(sym, classType, indent)

    formatTypeParams(sym.typeParams, nIndent)

    // Inspect ClassSymbol information
    println(s"${nIndent}alternatives        = ${sym.alternatives}")
    println(s"${nIndent}baseClasses         = ${sym.baseClasses}")
    println(s"${nIndent}isAbstract          = ${sym.isAbstract}")
    println(s"${nIndent}isAliasType         = ${sym.isAliasType}")
    println(s"${nIndent}isDerivedValueClass = ${sym.isDerivedValueClass}")
    println(s"${nIndent}isExistential       = ${sym.isExistential}")
    println(s"${nIndent}isFinal             = ${sym.isFinal}")
    println(s"${nIndent}isModuleClass       = ${sym.isModuleClass}")

    sym.info.decls foreach { member => inspect(member, nIndent) }

    println(s"$indent}")
    // Inspect Companion Object if existing
    if (sym.companion != ru.NoSymbol)
      inspect(sym.companion)
  }

  /**
   * Helper method that inspects a ModuleSymbol object.
   * @param sym The ModuleSymbol to inspect
   * @param indent The indent prefix to use for all printed output.
   */
  private def inspectModule(sym: ru.ModuleSymbol, indent: String = ""): Unit = {
    val nIndent = incrementIndent(indent)

    formatName(sym, PartialFunction[ru.ModuleSymbol,String](_ => "object"), indent)

    sym.info.decls foreach { member => inspect(member, nIndent) }

    println(s"$indent}")
  }

  /**
   * Helper method that inspects a MethodSymbol object.
   * @param sym The MethodSymbol to inspect
   * @param indent The indent prefix to use for all printed output.
   */
  private def inspectMethod(sym: ru.MethodSymbol, indent: String = ""): Unit = {
    val nIndent = incrementIndent(indent)

    // Determine type of Method
    val defType: PartialFunction[ru.MethodSymbol, String] = {
      // Check if method is a constructor
      case cst if cst.isConstructor =>
        cst match {
          case p if p.isPrimaryConstructor => "constructor.primary"
          case c if c.isConstructor => "constructor"
        }
      // Check if method is an accessor
      case acs if acs.isAccessor  =>
        // Check type of accessor
        acs match {
          case s if s.isSetter => "setter"
          case g if g.isGetter => "getter"
        }
      // Default check, method is just regular method
      case _ => "def"
    }

    formatName(sym, defType, indent)

    formatTypeParams(sym.typeParams, nIndent)

    // Inspect MethodSymbol information
    println(s"${nIndent}isAbstract         = ${sym.isAbstract}")
    println(s"${nIndent}isAbstractOverride = ${sym.isAbstractOverride}")
    println(s"${nIndent}param access       = ${sym.isParamAccessor}")
    println(s"${nIndent}overloaded         = ${sym.isOverloaded}")  // Does not seem to recognize an overloaded method
    println(s"${nIndent}synthetic          = ${sym.isSynthetic}")
    println(s"${nIndent}varargs            = ${sym.isVarargs}")
    println(s"${nIndent}returns            = ${sym.returnType}")

    // Print out alternatives to the method being inspected
    if (sym.alternatives.nonEmpty)
      println(s"${nIndent}alternatives       = ${sym.alternatives}")

    sym.info.paramLists foreach { pl =>
      println(s"${nIndent}parameter-list {")
      pl foreach { p => inspect(p, incrementIndent(nIndent)) }
      println(s"$nIndent}")
    }

    println(s"$indent}")
  }

  /**
   * Helper method that inspects a TermSymbol object.
   * @param sym The TermSymbol to inspect
   * @param indent The indent prefix to use for all printed output.
   */
  private def inspectTerm(sym: ru.TermSymbol, indent: String = ""): Unit = {
    val nIndent = incrementIndent(indent)

    // Determine type of Term
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
   * Helper method that inspects a standard TypeSymbol object.
   * @param sym The TypeSymbol to inspect
   * @param indent The indent prefix to use for all printed output.
   */
  private def inspectType(sym: ru.TypeSymbol, indent: String = ""): Unit = {
    val nIndent = incrementIndent(indent)

    formatName(sym, PartialFunction[ru.TypeSymbol, String](_ => "type"), indent)

    // Inspect key aspects of Type
    println(s"${nIndent}base type     = ${sym.info.resultType}")
    println(s"${nIndent}base classes  = ${sym.info.baseClasses}")
    println(s"${nIndent}alias type    = ${sym.isAliasType}")
    println(s"${nIndent}covariant     = ${sym.isCovariant}")
    println(s"${nIndent}contravariant = ${sym.isContravariant}")

    // Leaving this in for now since using inspect() will cause an infinite loop with type parameters
    //inspect(member, nIndent)
    sym.info.decls foreach { member => println(s"${nIndent}member.${member.name} : ${member.typeSignature}") }

    println(s"$indent}")
  }

  /**
    * Helper method that prepends a provided String with a second provided String. This method is
    * being used to increment the provided indent space with a pre-prepared amount of spaces. If
    * no 'increment' String is provided a default one will be used.
    * @param indent The String that is being prefixed
    * @param increment The String that is being prepended to the first String
    * @return The resulting String of the concatenation
    */
  private def incrementIndent(indent: String, increment: String = increment): String = indent + increment

  /**
   * Helper method resolving the visibility of the provided symbol and returns it as a String.
   * @param sym The Symbol for which the visibility is retrieved.
   * @return The Symbol's visibility as String.
   */
  private def resolveVisibility(sym: ru.Symbol): String = {
    // Determine Symbol's visibility
    sym match {
      case p if p.isPrivate   => "private"
      case p if p.isProtected => "protected"
      case p if p.isPublic    => "public"
      case _ => "unknown"
    }
  }

  /**
   * Helper method formatting the Symbol's name in a standard pattern
   * @param sym The symbol for which to format the name
   * @param typ The partial function used to determine the symbol's type
   * @param indent The indent to use for the formatted name print-out
   */
  private def formatName[A<:ru.Symbol](sym: A, typ: PartialFunction[A, String], indent: String): Unit = {
    val symType: PartialFunction[A, String] = typ orElse { case _ => "unknown" }
    println(s"$indent${symType(sym)}[${resolveVisibility(sym)}].${sym.name} {")
  }

  /**
   * Helper method formatting the list of Symbols in a standard pattern.
   * @param params The list of parameters (Symbol's) to format
   * @param indent The indent t use for the formatted print-out
   */
  private def formatTypeParams(params: List[ru.Symbol], indent: String = ""): Unit = {
    if (params.nonEmpty) {
      val nIndent = incrementIndent(indent)
      println(s"${indent}type.params {")
      params foreach { sym => inspect(sym.asType, nIndent) }
      println(s"$indent}")
    }
  }
}
