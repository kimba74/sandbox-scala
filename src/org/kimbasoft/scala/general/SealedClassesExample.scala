package org.kimbasoft.scala.general

/**
 * Missing documentation
 *
 * @since 1.0
 */
object SealedClassesExample {

  /**
   * The pattern matching in this method can securely omit
   * providing a default case clause. Usually it is good
   * practice to specify a default case to protect the pattern
   * matching at runtime from unforeseen implementations:
   *
   *     case _ => ...
   *
   * Since the class that the pattern matching analyzes is of
   * a sealed type, all known sub-types are already declared
   * and are contained in the same source file as the sealed
   * class itself.
   */
  def handle(sclass: SealedClasses) = sclass match {
    case MyClass(text) => println("My Class: " + text)
    case YourClass(text) => println("Your Class: " + text)
    case HisClass(text) => println("His Class: " + text)
    case HerClass(text) => println("Her Class: " + text)
    case ItsClass(text) => println("Its Class: " + text)
    case OurClass(text) => println("Our Class: " + text)
    case TheirClass(text) => println("Their Class: " + text)
  }

  def main(args: Array[String]) {
    val sclasses = List(
      MyClass("This is my class..."),
      YourClass("This is your class..."),
      HisClass("This is his class..."),
      HerClass("This is her class..."),
      ItsClass("This is its class..."),
      OurClass("This is our class..."),
      TheirClass("This is their class...")
    )

    for (sclass <- sclasses) handle(sclass)
  }
}
