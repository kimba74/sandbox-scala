package org.kimbasoft.scala.oo.methods

/**
 * Missing documentation
 *
 * @since 1.0
 */
object MethodInvocation {

  def main(args: Array[String]) {
    val str = Array("This", "will", "be", "a", "list", "of", "Strings")

    /* Fully fledged specification of a function literal. The parameter is declared
     * (s:String), the functions body is specified (println(...)), and the parameter
     * is passed to the function's single statement (println(s)) */
    println("-- Function Literal 1 ----")
    str.foreach((s:String) => println(s))

    /* Since the Array infers a type 'String' we know what will be passed to the
     * function literal in the foreach() method. Therefore we can drop the type of
     * the parameter and shorten the statement */
    println("-- Function Literal 2 ----")
    str.foreach(s => println(s))

    /* The foreach() method will only pass one parameter to the function literal and we
     * don't really need to reference it anywhere else thus we can drop specifying the
     * parameter all together and just reference whatever is being passed in by the outer
     * method. That is done via the '_' */
    println("-- Function Literal 3 ----")
    str.foreach(println(_))

    /* As mentioned in the example above, there is only one parameter that is being passed
     * to the function literal and since the function literal itself only excepts one
     * parameter we can even omit the '_' and shorten the statement even further */
    println("-- Function Literal 4 ----")
    str.foreach(println)

    /* Since everything in Scala is an object (even operations like '<' and '>') Scala allows
     * us to make function invocations without having to do the usual 'object.method(parameter)'
     * dot annotation. Instead we can write the function invocation like we would write an
     * operator 'object function function-literal'. So we can modify the statement to look like
     * this. (Using '{...}' to show that Scala mostly doesn't care which braces are used) */
    println("-- Function Literal 5 ----")
    str foreach { println }

    /* Even that can be shortened further. Since our functional literal consists of only one
     * statement, and since the statement only takes one parameter we can safely omit even the
     * curly braces (or regular braces), which shortens the function invocation with function
     * literal to only three words */
    println("-- Function Literal 6 ----")
    str foreach println

  }
}
