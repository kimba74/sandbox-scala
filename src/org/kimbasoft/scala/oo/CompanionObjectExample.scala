package org.kimbasoft.scala.oo

/**
 * Missing documentation
 *
 * @since 1.0
 */
object CompanionObjectExample {

  def main(args: Array[String]) {
    // Default apply() method of Companion Object
    val comObj1 = CompanionObject()
    println("Companion Object apply()       => " + comObj1.label)

    // Secondary apply() method of Companion Object with String as argument
    val comObj2 = CompanionObject("Hello World")
    println("Companion Object apply(String) => " + comObj2.label)

    // Primary constructor of the actual class
    val com3 = new CompanionObject("Hello everyone")
    println("Class Primary Constructor      => " + com3.label)

    // Invocation of the class level apply() method
    val comIns1 = comObj1(3)
    println("Class Instance apply()         => '" + comIns1 + "'")

    // Extractor unapply() method of Companion Object
    comObj2 match {
      case CompanionObject(label, bool) => println("Companion Object unapply(...)  => label='" + label + "', bool=" + bool)
      case _ =>  //Do nothing if not CompanionObject
    }
  }
}
