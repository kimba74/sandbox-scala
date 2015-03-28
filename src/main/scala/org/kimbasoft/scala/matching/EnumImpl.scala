package org.kimbasoft.scala.matching

import org.kimbasoft.scala.matching.EnumType._

/**
 * Missing documentation
 *
 * @since 1.0
 */
object EnumImpl extends App {

  def isTerrier(breed: EnumType) = breed.toString endsWith "Terrier"

  /**
   * The First Example will iterate over all Enumeration values and will
   * print their internal ID and their respective name. Earlier examples
   * wrote the foreach loop as follows:
   *
   * for (breed <- EnumType) {...}
   *
   * However the Enumeration's foreach(...) method was deprecated and
   * later removed. The values are now being returned by the Enumeration's
   * 'values' method thus changing the foreach loop to:
   *
   * for (breed <- EnumType.values) {...}
   */
  println("-- List All Dog Breeds -----------------------")
  println("ID\tName")
  for (breed <- EnumType.values) println(breed.id + "\t" + breed)

  /**
   * The Second Example is very similar to the first example in that it will
   * iterate over the Enumeration's values. It will however filter the
   * values first by running them through our custom filter method
   * 'isTerrier(breed: EnumType)', which will check if the Enumeration's
   * value name ends in 'Terrier' and return true vor all values where that
   * is the case. Following the filter we call the collections 'foreach()'
   * method and once again print the Enumeration's value's internal ID
   * and name.
   */
  println("-- List All Terrier Breeds -------------------")
  println("ID\tName")
  EnumType.values filter isTerrier foreach (breed => println(breed.id + "\t" + breed))

  /**
   * The Third Example will once again iterate over the Enumeration's values
   * but will then pattern match them and print a custom response to two
   * types of values. Since we are matching against a value rather than a type
   * we will have to use the '@' in order to assign the match to a variable that
   * we can then use in the case's function. (see. PatternMatching)
   */
  println("-- Pattern Matching --------------------------")
  for (breed <- EnumType.values) {
    breed match {
      case Dane => println("Did you know Great Danes are also called German Mastiffs?")
      case Schnauzer => println("I love Schnauzers, too!")
      case other => println("I don't particularly care for " + breed + "s")
    }
  }
}
