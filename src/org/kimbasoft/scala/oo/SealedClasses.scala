package org.kimbasoft.scala.oo

/**
 * If a class is marked 'sealed' then all of its possible subclasses
 * must reside in the same file. Inheritance of a sealed class outside
 * the declaring file is not possible. The main benefit of a sealed
 * class is that since all possible sub-types are known, a pattern
 * matching can be safely done without having to specify a default case
 * clause.
 */
sealed abstract class SealedClasses

case class MyClass(body: String) extends SealedClasses

case class YourClass(body: String) extends SealedClasses

case class HisClass(body: String) extends SealedClasses

case class HerClass(body: String) extends SealedClasses

case class ItsClass(body: String) extends SealedClasses

case class OurClass(body: String) extends SealedClasses

case class TheirClass(body: String) extends SealedClasses
