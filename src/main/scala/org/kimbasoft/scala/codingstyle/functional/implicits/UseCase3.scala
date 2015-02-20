package org.kimbasoft.scala.codingstyle.functional.implicits

/**
 * Use Case: Type Classes (aka. Extension Methods)
 *
 * @since 1.0
 */
object UseCase3 {
  case class Address(street: String, city: String)
  case class Person(name: String, address: Address)

  trait ToJSON {
    def toJSON(level: Int = 0): String
    val INDENTATION = "  "
    def indentation(level: Int = 0): (String, String) = {
      (INDENTATION * level, INDENTATION * (level + 1))
    }
  }

  implicit class AddressToJSON(address: Address) extends ToJSON {
    def toJSON(level: Int = 0): String = {
      val (outdent, indent) = indentation(level)
      s"""{
         |$indent"street": "${address.street}",
         |$indent"city": "${address.city}"
         |$outdent}""".stripMargin
    }
  }

  implicit class PersonToJSON(person: Person) extends ToJSON {
    def toJSON(level: Int = 0): String = {
      val (outdent, indent) = indentation(level)
      s"""{
         |$indent"name": "${person.name}"
         |$indent"address": ${person.address.toJSON(level + 1)}
         |$outdent}""".stripMargin
    }
  }

  def main(args: Array[String]) {
    val addr = Address("12 Scala Ct", "Program Ville")
    val pers = Person("Programmer Joe", addr)

    println("-- Print Address -------------------")
    println(addr.toJSON())
    println("-- Print Person (w/ Address) -------")
    println(pers.toJSON())
  }
}
