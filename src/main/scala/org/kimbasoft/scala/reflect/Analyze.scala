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
    println(s"sym                          = $sym")
    println(s"sym [alternatives]           = ${sym.alternatives}")
    println(s"sym [annotations]            = ${sym.annotations}")
    println(s"sym [fullName]               = ${sym.fullName}")
    println(s"sym [companion]              = ${sym.companion}")
    analyze(sym.info, "sym [info] ")
    println(s"sym [knownDirectSubclasses]  = ${sym.knownDirectSubclasses}")
    println(s"sym [module]                 = ${sym.module}")
    println(s"sym [name]                   = ${sym.name}")
    println(s"sym [overrides]              = ${sym.overrides}")
    println(s"sym [owner]                  = ${sym.owner}")
    println(s"sym [pos]                    = ${sym.pos}")
    println(s"sym [selfType]               = ${sym.selfType}")
    println(s"sym [thisPrefix]             = ${sym.thisPrefix}")
    println(s"sym [toType]                 = ${sym.toType}")
    println(s"sym [toTypeConstructor]      = ${sym.toTypeConstructor}")
    println(s"sym [typeParams]             = ${sym.typeParams}")
    println(s"sym [typeSignature]          = ${sym.typeSignature}")
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
