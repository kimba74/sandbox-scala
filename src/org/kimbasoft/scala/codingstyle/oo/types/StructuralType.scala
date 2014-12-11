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
    // The Structural Type
    type Applicant = { def apply(): String }
    
    private var applicants: List[Applicant] = Nil 
    
    def register(applicant: Applicant) = applicants ::= applicant

    def printApplications() = applicants foreach (a => println(a.apply()))
  }

  case class SchoolApplicant(name: String) {
    def apply(): String = "School application for " + name
  }

  case class PassportApplicant(name: String, country: String) {
    def apply(): String = s"Passport application for $name in $country"
  }


  def main(args: Array[String]) {
    Registry.register(SchoolApplicant("John Doe"))
    Registry.register(SchoolApplicant("Jane Doe"))
    Registry.register(PassportApplicant("Mark Smith", "Great Britain"))
    Registry.register(SchoolApplicant("Frank Boyle"))
    Registry.register(PassportApplicant("Nancy Baker", "USA"))

    Registry.printApplications()
  }
}
