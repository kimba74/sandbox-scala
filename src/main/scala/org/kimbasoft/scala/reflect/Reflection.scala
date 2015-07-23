package org.kimbasoft.scala.reflect

import scala.reflect.runtime.{universe => ru}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object Reflection extends App {

  //---- Instantiation of a dynamically loaded class ----

  // Getting the runtime mirror for the provided ClassLoader
  val mirClassLoader = ru.runtimeMirror(getClass.getClassLoader)

  // Loading the ClassSymbol for a specified class based on the class' fully qualified name
  val symTestClass = mirClassLoader.staticClass("org.kimbasoft.scala.reflect.TestClass")
  
  // Getting the runtime mirror for the provided ClassSymbol
  val mirTestClass = mirClassLoader.reflectClass(symTestClass)

  // Loading the MethodSymbol for the primary constructor of the specified ClassSymbol
  val symConstruct = symTestClass.primaryConstructor.asMethod

  // Getting the runtime mirror for the provided MethodSymbol of the primary constructor
  val mirConstruct = mirTestClass.reflectConstructor(symConstruct)

  // Create instance of loaded class via runtime mirror of the primary constructor
  val instTest = mirConstruct("MyTest")


  //---- Inspection of the previously instantiated class ----

  // Invoke toString() of the instance of the dynamically loaded class
  println("Calling toString()")
  println(s"  > $instTest")

  // Inspecting the parameter list of the MethodSymbol of the default constructor
  println("Inspecting constructor parameters")
  for(symList <- symConstruct.info.paramLists) {
    for(symbol <- symList)
    println(s"  > ${symbol.name}: ${symbol.info}")
  }

  // Inspecting the base classes of the ClassSymbol
  println("Inspecting base classes")
  for (tsym <- symTestClass.baseClasses)
    println(s"  > ${tsym.fullName}")

  // Check if dynamically loaded class is of type TestClass
  println("Checking if class is of type 'TestClass'")
  println(s"  > ${symTestClass.toType =:= ru.typeOf[TestClass]}")

  println("----------------------------------------------------------")
  // Using Inspector to inspect class
  Inspector.inspect(symTestClass)

  //TODO slk: Check into type erasure as cause for TypeTag being unavailable for dynamically loaded class
  val tpe = getTypeTag(symTestClass.info.erasure) // TODO slk: find a way to get access to class type
  println(s"tpe  = ${tpe.tpe}")

  val ttpe = testTypeTag[String]
  println(s"ttpe = $ttpe")

  def getTypeTag[T : ru.TypeTag](obj: T) = ru.typeTag[T]

  def testTypeTag[T](implicit obj: ru.TypeTag[T]) = obj.tpe

  println("----------------------------------------------------------")
  val sclass = mirClassLoader.staticClass("org.kimbasoft.scala.reflect.TestClass")
  println(s"sclass                          = $sclass")
  println(s"sclass [fullName]               = ${sclass.fullName}")
  println(s"sclass [companion]              = ${sclass.companion}")
  println(s"sclass [info]                   = ${sclass.info}")
  println(s"sclass [knownDirectSubclasses]  = ${sclass.knownDirectSubclasses}")
  println(s"sclass [module]                 = ${sclass.module}")
  println(s"sclass [name]                   = ${sclass.name}")
  println(s"sclass [overrides]              = ${sclass.overrides}")
  println(s"sclass [owner]                  = ${sclass.owner}")
  println(s"sclass [pos]                    = ${sclass.pos}")
  println(s"sclass [selfType]               = ${sclass.selfType}")
  println(s"sclass [thisPrefix]             = ${sclass.thisPrefix}")
  println(s"sclass [toType]                 = ${sclass.toType}")
  println(s"sclass [toTypeConstructor]      = ${sclass.toTypeConstructor}")
  println(s"sclass [typeParams]             = ${sclass.typeParams}")
  println(s"sclass [typeSignature]          = ${sclass.typeSignature}")
  println(s"sclass =:= ru.typeOf[TestClass] = ${sclass.toType =:= ru.typeOf[TestClass]}")

  val smirror  = mirClassLoader.reflectClass(sclass)
  val sconst   = sclass.primaryConstructor.asMethod
  val scmirror = smirror.reflectConstructor(sconst)
  val sobj = scmirror("Bla!")
  println(s"sobj   = $sobj")
  println(s"sobj isInstanceOf[TestClass] = ${sobj.isInstanceOf[TestClass]}")

  val stype = sclass.asType
  println(s"stype  = $stype")

  val stpe   = getTypeTag(stype)
  println(s"stpe   = ${stpe.tpe}")

}
