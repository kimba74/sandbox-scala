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
    println(s"sym [isAbstract]               = ${sym.isAbstract}")
    println(s"sym [isAbstractOverride]       = ${sym.isAbstractOverride}")
    println(s"sym [isAliasType]              = ${sym.isAliasType}")
    println(s"sym [isCaseClass]              = ${sym.isCaseClass}")
    println(s"sym [isClass]                  = ${sym.isClass}")
    println(s"sym [isConstructor]            = ${sym.isConstructor}")
    println(s"sym [isContravariant]          = ${sym.isContravariant}")
    println(s"sym [isCovariant]              = ${sym.isCovariant}")
    println(s"sym [isDerivedValueClass]      = ${sym.isDerivedValueClass}")
    println(s"sym [isExistential]            = ${sym.isExistential}")
    println(s"sym [isFinal]                  = ${sym.isFinal}")
    println(s"sym [isImplementationArtifact] = ${sym.isImplementationArtifact}")
    println(s"sym [isImplicit]               = ${sym.isImplicit}")
    println(s"sym [isJava]                   = ${sym.isJava}")
    println(s"sym [isMacro]                  = ${sym.isMacro}")
    println(s"sym [isMethod]                 = ${sym.isMethod}")
    println(s"sym [isModule]                 = ${sym.isModule}")
    println(s"sym [isModuleClass]            = ${sym.isModuleClass}")
    println(s"sym [isNumeric]                = ${sym.isNumeric}")
    println(s"sym [isPackage]                = ${sym.isPackage}")
    println(s"sym [isPackageClass]           = ${sym.isPackageClass}")
    println(s"sym [isParameter]              = ${sym.isParameter}")
    println(s"sym [isPrimitive]              = ${sym.isPrimitive}")
    println(s"sym [isPrivate]                = ${sym.isPrivate}")
    println(s"sym [isPrivateThis]            = ${sym.isPrivateThis}")
    println(s"sym [isProtected]              = ${sym.isProtected}")
    println(s"sym [isProtectedThis]          = ${sym.isProtectedThis}")
    println(s"sym [isPublic]                 = ${sym.isPublic}")
    println(s"sym [isSealed]                 = ${sym.isSealed}")
    println(s"sym [isSpecialized]            = ${sym.isSpecialized}")
    println(s"sym [isSynthetic]              = ${sym.isSynthetic}")
    println(s"sym [isTerm]                   = ${sym.isTerm}")
    println(s"sym [isTrait]                  = ${sym.isTrait}")
    println(s"sym [isType]                   = ${sym.isType}")
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
}
