package org.kimbasoft.scala.general

/**
 * The Companion Object definition.
 *
 * @since 1.0
 */
object CompanionObject {

  /**
   * The apply() methods in (Companion) Objects are normally used as factory
   * methods. They don't have to be factory methods but since that is their
   * widely accepted function in the Scala community it is not advisable to
   * use them as something else since it might confuse users of the API. If
   * Classes end up having many alternative constructors the Scala community
   * recommends to reduce the amount of alternative constructors and replace
   * them with overloaded apply() methods in a Companion Object. Following is
   * an example of how to use an apply() method that takes a String parameter
   * as argument:
   *
   *     val comObj = CompanionObject("Hello World!")
   */
  def apply(label: String) = new CompanionObject(label)

  /**
   * As mentioned in the documentation above, apply() methods are regular methods
   * and can therefore be overloaded. It is quite common to write a default or
   * primary apply() method that takes the full extend of all the parameters
   * possible and provide secondary apply() methods with less or nor arguments
   * that call the primary apply method with sensible default values. Just
   * remember, the overloading apply() methods MUST specify the return type in
   * order to function. Following is an example of how the overloaded apply()
   * method would be invoked:
   *
   *     val comObj = CompanionObject()
   */
  def apply(): CompanionObject = apply("unknown")

  /**
   *  The unapply() method is used as an Extractor e.g. in pattern matching.
   *  In the following example an instance of a CompanionObject class is being
   *  matched. One of the 'case' clauses extracts the values of the instance by
   *  looking in the object CompanionObject for a unapply() method that matches
   *  the signature "unapply(CompanionObject)" and invokes it:
   *
   *     comObj match {
   *        case CompanionObject(label, bool) => ...
   *        ...
   *     }
   *
   *  The case clause's argument (CompanionObject(...)) will trigger the invocation
   *  of the unapply() method. The variables 'label' and 'bool' will catch the
   *  values of the unapply() method's return. The amount of "catch" variables used
   *  in the case clause MUST match the amount of values returned by the unapply()
   *  method. The example above will look for an unapply() method with the following
   *  signature:
   *
   *     unapply(CompanionObject): Option[(Any, Any)]
   */
  def unapply(co: CompanionObject): Option[(String, Boolean)] = {
    co.label match {
      // Check if the label is "unknown"
      case "unknown" => None
      // If label is NOT "unknown" check length
      case lbl =>
        lbl.length match {
          case y if y > 5 => Some((lbl, true))
          case y if y <= 5 => Some((lbl, false))
        }
    }
  }
}

/**
 * The Class definition.
 *
 * @since 1.0
 */
class CompanionObject(val label: String) {
  private val array = label.toCharArray

  /**
   * In a class an apply() method can be used for all sorts of different things.
   * No real convention was established here but the rule-of-thumb is to make
   * it something logical and obvious. Arrays for example use it to allow a
   * developer access to a value at a certain index. Assume we had an array
   * of Strings called 'myArray', in order to access the third value in this
   * array one would write:
   *
   *     val third = myArray(2)
   *
   * The apply() method defined in a class has to be called on the instance
   * variable rather than on the Companion Object. The way it is invoked is
   * exactly the same as the invocation of apply() in the Companion Object.
   * An example of how the class level apply() method would be used with this
   * class could look like the following:
   *
   *     val comObj = new CompanionObject("Hello World")
   *     val myChar = comObj(4)
   *
   * This example would return the 5th character (arrays start at index 0)
   * of the label "Hello World" which is "o".
   */
  def apply(idx: Int) = {
    idx match {
      case y: Int if y < array.length => array(idx)
      case _ =>
    }
  }
}