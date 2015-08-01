package org.kimbasoft.scala.reflect

import scala.reflect.runtime.{universe => ru}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Analyze {

  def analyze(sym: ru.ClassSymbol): Unit = {
    println(s"sym                            = $sym")
    println(s"sym [alternatives]             = ${sym.alternatives}")
    println(s"sym [annotations]              = ${sym.annotations}")
    println(s"sym [fullName]                 = ${sym.fullName}")
    println(s"sym [companion]                = ${sym.companion}")
    analyze(sym.info, "sym [info] ")
    symbolIs(sym, "sym")
    println(s"sym [knownDirectSubclasses]    = ${sym.knownDirectSubclasses}")
    println(s"sym [module]                   = ${sym.module}")
    println(s"sym [name]                     = ${sym.name}")
    println(s"sym [overrides]                = ${sym.overrides}")
    println(s"sym [owner]                    = ${sym.owner}")
    println(s"sym [pos]                      = ${sym.pos}")
    println(s"sym [selfType]                 = ${sym.selfType}")
    println(s"sym [thisPrefix]               = ${sym.thisPrefix}")
    println(s"sym [toType]                   = ${sym.toType}")
    println(s"sym [toTypeConstructor]        = ${sym.toTypeConstructor}")
    println(s"sym [typeParams]               = ${sym.typeParams}")
    println(s"sym [typeSignature]            = ${sym.typeSignature}")
  }

  def analyze(typ: ru.Type, prefix: String = ""): Unit = {
    println(s"$prefix               = $typ")
    println(s"$prefix - baseClasses = ${typ.baseClasses}")
    println(s"$prefix - dealias     = ${typ.dealias}")
    println(s"$prefix - decls       = ${typ.decls}")
    println(s"$prefix - erasure     = ${typ.erasure}")
    println(s"$prefix - etaExpand   = ${typ.etaExpand}")
  }
  
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
