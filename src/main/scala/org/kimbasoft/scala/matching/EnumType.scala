package org.kimbasoft.scala.matching

/**
 * Missing documentation
 *
 * @since 1.0
 */
object EnumType extends Enumeration {
  /**
   * Setting the 'type' is important for Objects (especially ones with subclasses
   * like Enumeration). Objects are Singletons and therefore exist only once.
   * In case of an Enumeration we want to be able to pass one of its values to
   * a method and rather than having to use the subclass in the method declaration
   * we want to use the Object Type like the following:
   *
   *    def isTerrier(breed: EnumType): Boolean = {...}
   *
   * This allows us to call the method like the following:
   *
   *    val terrier = isTerrier(Dane)
   *
   * If the type of this object was not set to its subclass we would have to declare
   * the the method like the following:
   *
   *    def isTerrier(breed: EnumType.Value): Boolean = {...}
   */
  type EnumType = Value

  /**
   * The following lines declare the Enumeration's values and set a human readable
   * name for each one. This name can be retrieved via the 'toString' method.
   * Each declaration line calls the 'Value(...)' method, which is an overloaded
   * method that creates an instance of the Enumeration's subclass 'Value', places
   * it in the internal collection.
   * One thing to consider when naming values in an Enumeration is to give them names
   * starting with a capital letter. This will avoid having to escape them when the
   * entire Object was imported:
   *
   *    case Schnauzer => println(...)
   *
   * rather than:
   *
   *    case `schnauzer` => println(...)   or   case EnumType.schnauzer => println(...)
   */
  val Doberman = Value("Doberman Pinscher")
  val Yorkie = Value("Yorkshire Terrier")
  val Scottie = Value("Scottish Terrier")
  val Dane = Value("Great Dane")
  val Portie = Value("Portuguese Water Dog")
  val Schnauzer = Value("Schnauzer")
}
