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

  // Check if dynamically loaded class is of type TestClass
  println(s"classTest of type TestClass = ${classTest.toType =:= ru.typeOf[TestClass]}")

  // Loading the primary constructor of the loaded class
  val constTest   = classTest.primaryConstructor.asMethod
  val constMirror = classMirror.reflectConstructor(constTest)

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

  // Using Inspector to inspect class
  Inspector.inspect(classTest)

  //TODO slk: Check into type erasure as cause for TypeTag being unavailable for dynamically loaded class
  val tpe = getTypeTag(classTest.info.erasure) // TODO slk: find a way to get access to class type
  println(s"tpe  = ${tpe.tpe}")

  val ttpe = testTypeTag[String]
  println(s"ttpe = $ttpe")

  for (tsym <- classTest.baseClasses)
    println(getTypeTag(tsym.info.dealias))

  def getTypeTag[T : ru.TypeTag](obj: T) = ru.typeTag[T]

  def testTypeTag[T](implicit obj: ru.TypeTag[T]) = obj.tpe

  println("----------------------------------------------------------")
  // Testing approach via Java Class (TODO slk: Check on type erasure)
  val sclass = runMirror.staticClass("org.kimbasoft.scala.reflect.TestClass")
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

  val smirror  = runMirror.reflectClass(sclass)
  val sconst   = sclass.primaryConstructor.asMethod
  val scmirror = smirror.reflectConstructor(sconst)
  val sobj = scmirror("Bla!")
  println(s"sobj   = $sobj")
  println(s"sobj isInstanceOf[TestClass] = ${sobj.isInstanceOf[TestClass]}")

  val stype = sclass.asType
  println(s"stype  = $stype")

  val stpe   = getTypeTag(sclass)
  println(s"stpe   = ${stpe.tpe}")

}
