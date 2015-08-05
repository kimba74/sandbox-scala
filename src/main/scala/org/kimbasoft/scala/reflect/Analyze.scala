package org.kimbasoft.scala.reflect

import scala.reflect.runtime.{universe => ru}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Analyze {

  def analyze(sym: ru.ClassSymbol, prefix: String = ""): Unit = {
    println(s"$prefix                            = $sym")
    println(s"$prefix [alternatives]             = ${sym.alternatives}")
    println(s"$prefix [annotations]              = ${sym.annotations}")
    println(s"$prefix [fullName]                 = ${sym.fullName}")
    println(s"$prefix [companion]                = ${sym.companion}")
    infos(sym.info, s"$prefix [info] ")
    symbolIs(sym, s"$prefix[identity] ")
    println(s"$prefix [knownDirectSubclasses]    = ${sym.knownDirectSubclasses}")
    println(s"$prefix [module]                   = ${sym.module}")
    println(s"$prefix [name]                     = ${sym.name}")
    println(s"$prefix [overrides]                = ${sym.overrides}")
    println(s"$prefix [owner]                    = ${sym.owner}")
    println(s"$prefix [pos]                      = ${sym.pos}")
    println(s"$prefix [selfType]                 = ${sym.selfType}")
    println(s"$prefix [thisPrefix]               = ${sym.thisPrefix}")
    println(s"$prefix [toType]                   = ${sym.toType}")
    println(s"$prefix [toTypeConstructor]        = ${sym.toTypeConstructor}")
    println(s"$prefix [typeParams]               = ${sym.typeParams}")
    println(s"$prefix [typeSignature]            = ${sym.typeSignature}")
  }

  def infos(typ: ru.Type, prefix: String = ""): Unit = {
    println(s"$prefix               = $typ")
    println(s"$prefix - baseClasses = ${typ.baseClasses}")
    println(s"$prefix - dealias     = ${typ.dealias}")
    println(s"$prefix - decls       = ${typ.decls}")
    println(s"$prefix - erasure     = ${typ.erasure}")
    println(s"$prefix - etaExpand   = ${typ.etaExpand}")
  }

  /**
   * Prints descriptor information of the Class Symbol
   * @param sym
   * @param prefix
   */
  def symbolIs(sym: ru.ClassSymbol, prefix: String = ""): Unit = {
    println(s"$prefix [isAbstract]               = ${sym.isAbstract}")
    println(s"$prefix [isAbstractOverride]       = ${sym.isAbstractOverride}")
    println(s"$prefix [isAliasType]              = ${sym.isAliasType}")
    println(s"$prefix [isCaseClass]              = ${sym.isCaseClass}")
    println(s"$prefix [isClass]                  = ${sym.isClass}")
    println(s"$prefix [isConstructor]            = ${sym.isConstructor}")
    println(s"$prefix [isContravariant]          = ${sym.isContravariant}")
    println(s"$prefix [isCovariant]              = ${sym.isCovariant}")
    println(s"$prefix [isDerivedValueClass]      = ${sym.isDerivedValueClass}")
    println(s"$prefix [isExistential]            = ${sym.isExistential}")
    println(s"$prefix [isFinal]                  = ${sym.isFinal}")
    println(s"$prefix [isImplementationArtifact] = ${sym.isImplementationArtifact}")
    println(s"$prefix [isImplicit]               = ${sym.isImplicit}")
    println(s"$prefix [isJava]                   = ${sym.isJava}")
    println(s"$prefix [isMacro]                  = ${sym.isMacro}")
    println(s"$prefix [isMethod]                 = ${sym.isMethod}")
    println(s"$prefix [isModule]                 = ${sym.isModule}")
    println(s"$prefix [isModuleClass]            = ${sym.isModuleClass}")
    println(s"$prefix [isNumeric]                = ${sym.isNumeric}")
    println(s"$prefix [isPackage]                = ${sym.isPackage}")
    println(s"$prefix [isPackageClass]           = ${sym.isPackageClass}")
    println(s"$prefix [isParameter]              = ${sym.isParameter}")
    println(s"$prefix [isPrimitive]              = ${sym.isPrimitive}")
    println(s"$prefix [isPrivate]                = ${sym.isPrivate}")
    println(s"$prefix [isPrivateThis]            = ${sym.isPrivateThis}")
    println(s"$prefix [isProtected]              = ${sym.isProtected}")
    println(s"$prefix [isProtectedThis]          = ${sym.isProtectedThis}")
    println(s"$prefix [isPublic]                 = ${sym.isPublic}")
    println(s"$prefix [isSealed]                 = ${sym.isSealed}")
    println(s"$prefix [isSpecialized]            = ${sym.isSpecialized}")
    println(s"$prefix [isSynthetic]              = ${sym.isSynthetic}")
    println(s"$prefix [isTerm]                   = ${sym.isTerm}")
    println(s"$prefix [isTrait]                  = ${sym.isTrait}")
    println(s"$prefix [isType]                   = ${sym.isType}")
  }
}
