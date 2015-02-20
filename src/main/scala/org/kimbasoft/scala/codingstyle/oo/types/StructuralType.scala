package org.kimbasoft.scala.codingstyle.oo.types

/**
 * Missing documentation
 *
 * @since 1.0
 */
object StructuralType {

  object Registry {
    /* Import flag to enable Reflective Calls for Structural Types.
     * Since Structural Types are not bound to a named type the Runtime
     * engine cannot determine by the Object's inheritance tree if it
     * satisfies required structure (like it would with Traits or
     * abstract classes). The Runtime engine must use a slightly more
     * costly Reflection introspection. This feature is deemed optional
     * in Scala but can be turned on by the following import statement */
    import scala.language.reflectiveCalls

    /* Structural Type describing an expected Object's structure.
     * Instead of tying an Object's structure to a named type such as a Trait
     * or abstract class Scala allows to define a more loosely structure known
     * as a structural type. The following declaration describes the members
     * an expected type must have in order to be used by the object as parameter.
     * It then assigns it to an internal name. Every object passed to the Object's
     * methods must adhere to the structure defined by the internal type. */
    type Applicant = { def apply(): String }

    /* Structural Type describing a function.
     * The above used structural type does lift the dependency on a named type by
     * other objects but it passes that dependency down to the method. It requires
     * all objects to implement a specific method. This dependency can also be
     * removed by declaring a structural type that describes a function rather than
     * an object. The following structural type definition requires that a passed
     * function follows a certain signature but abstracts the function's name.
     * This way allows us to implement methods with arbitrary names to be registered
     * with this object. */
    type Descriptor = () => String
    
    private var applicants: List[Applicant] = Nil 

    private var descriptors: List[Descriptor] = Nil

    def register(applicant: Applicant) = applicants ::= applicant

    def describe(descriptor: Descriptor) = descriptors ::= descriptor

    def printApplications() = applicants foreach (a => println(a.apply()))

    def printDescriptions() = descriptors foreach (d => println(d()))
  }

  case class SchoolApplicant(name: String) {
    def apply(): String = s"School application for $name"
  }

  case class PassportApplicant(name: String, country: String) {
    def apply(): String = s"Passport application for $name in $country"
  }

  case class BookDescription(summary: String)

  case class JobDescription(position: String)


  def main(args: Array[String]) {
    println("-- Using an Object Structural Type -----------")
    Registry.register(SchoolApplicant("John Doe"))
    Registry.register(PassportApplicant("Mark Smith", "Great Britain"))
    Registry.register(SchoolApplicant("Jane Doe"))
    Registry.register(PassportApplicant("Nancy Baker", "USA"))
    Registry.printApplications()

    println("-- Using a Function Structural Type ----------")
    // Passing a method satisfying the required signature of an non-specific object
    Registry.describe(() => BookDescription("Map of Bones is a great book!").summary)
    // Passing a method satisfying the required signature of another non-specific object
    Registry.describe(() => JobDescription("Working as a lumberjack is tough work").position)
    // Defining a named higher function satisfying the required signature and passing it to the object
    val funct = () => "This is just a function"
    Registry.describe(funct)
    // Passing an anonymous higher function satisfying the required signature to the object
    Registry.describe(() => "And this is an anonymous function")
    Registry.printDescriptions()
  }
}
