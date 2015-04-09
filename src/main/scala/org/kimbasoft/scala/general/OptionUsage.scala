package org.kimbasoft.scala.general

/**
 * Missing documentation
 *
 * @since 1.0
 */
object OptionUsage extends App {

  /**
   * A function definition (with pattern matching) that accepts any object and
   * returns a "Some(String)" object if the parameter is of type String or a
   * "None" object is the parameter is something else
   */
  def evaluate(obj: Any): Option[String] = {
    obj match {
      case s: String => Some(s)
      case _ => None
    }
  }

  val control = Map("Up" -> 1, "Down" -> 2, "Right" -> 3, "Left" -> 4)

  // Normal .get() on the Map will return an Option sub-class (Some(...), or None)
  println("""-- Getting Option ------------------""")
  println("Up   : " + control.get("Up")) // This and the following lines will return Some(...)
  println("Down : " + control.get("Down"))
  println("Right: " + control.get("Right"))
  println("Left : " + control.get("Left"))
  println("North: " + control.get("North")) // This one will return None

  /* To get the value from a "Some" object one will have to call .get() on the Some
   * object itself. This will not however work on a "None" object */
  println("""-- Getting "Some" value ------------""")
  println("Up   : " + control.get("Up").get)
  println("Down : " + control.get("Down").get)
  println("Right: " + control.get("Right").get)
  println("Left : " + control.get("Left").get)
  // println("North: " + control.get("North").get) // Will crash because .get() will return None

  /* In order to avoid having to check if a returned Option object is of type Some or None before
   * being able to call a .get() method to retrieve the value, Scala offers a retrieval method that
   * allows to specify an alternate return value in case of a None, .getOrElse() */
  println("""-- Safely getting "Some" value -----""")
  println("Up   : " + control.getOrElse("Up", "Oops!"))
  println("Down : " + control.getOrElse("Down", "Oops!"))
  println("Right: " + control.getOrElse("Right", "Oops!"))
  println("Left : " + control.getOrElse("Left", "Oops!"))
  println("North: " + control.getOrElse("North", "Oops!")) // Will return "Oops!"

  /* Calling the custom method evaluate(Any) and receiving the Option sub-class */
  println("-- Calling custom function ---------")
  println("String value for 24 = " + evaluate(24))
  println(s"""String value for "Text" = ${evaluate("Text")}""")
}
