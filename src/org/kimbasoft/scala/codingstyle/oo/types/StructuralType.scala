package org.kimbasoft.scala.codingstyle.oo.types

/**
 * Missing documentation
 *
 * @since 1.0
 */
object StructuralType {

  object Registry {
    // Import flag to enable Reflective Calls for Structural Types
    import scala.language.reflectiveCalls
    // Structural Type describing an expected Object's structure
    type Applicant = { def apply(): String }
    // Structural Type describing a function
    type Descriptor = () => String
    
    private var applicants: List[Applicant] = Nil 

    private var descriptors: List[Descriptor] = Nil

    def register(applicant: Applicant) = applicants ::= applicant

    def describe(descriptor: Descriptor) = descriptors ::= descriptor

    def printApplications() = applicants foreach (a => println(a.apply()))

    def printDescriptions() = descriptors foreach (d => println(d()))
  }

  case class SchoolApplicant(name: String) {
    def apply(): String = "School application for " + name
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
    Registry.describe(() => BookDescription("Map of Bones is a great book!").summary)
    Registry.describe(() => JobDescription("Working as a lumberjack is tough work").position)
    val funct = () => "This is just a function"
    Registry.describe(funct)
    Registry.describe(() => "And this is an anonymous function")
    Registry.printDescriptions()
  }
}
