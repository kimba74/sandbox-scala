package org.kimbasoft.scala.matching

import scala.util.Random

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object PatternMatching {

  def main(args: Array[String]) {

    // Very simple version of Pattern Matching
    println("-- Very Simple Example -----------------------")
    for(iter <- 1 to 7) {
      val num = Random.nextInt(10)
      num match {
        case 7 => println("YES!! Lucky 7")
        case _ => println("Well, not worth mentioning it")
      }
    }

    // Very simple Pattern Matching with assigning to variable
    println("-- Very Simple Example (variable) ------------")
    for(iter <- 1 to 7) {
      val num = Random.nextInt(10)
      num match {
        case 7 => println("YES!! Lucky 7")
        case other => println("Well, just boring old " + other)
      }
    }

    // Simple Type Pattern Matching
    println("-- Type Pattern Matching ---------------------")
    val list = List(1, true, "hello", 4.6)
    for (item <- list) {
      item match {
        case i: Int => println("Integer detected: " + i)
        case b: Boolean => println("Boolean detected: " + b)
        case d: Double => println("Double detected: " + d)
        case other => println("Unsupported type detected: " + other)
      }
    }

    // Pattern Matching of Sequences
    println("-- Sequence Pattern Matching -----------------")
    val l1 = List(1, 23, 45, 89)
    val l2 = List(8, 51, 66, 98)
    val l3 = List(3, 42, 78, 85, 99)
    val l4 = List(6, 23, 56, 79, 93)
    val l5 = List()
    for (list <- List(l1, l2, l3, l4, l5)) {
      list match {
        case List(_, 23, _, _) => println("Found list with 4 elements where 2nd element is 23: " )
        case List(_, _, _, _) => println("Found list with 4 elements: ")
        case List(_, 42, _*) => println("Found list of variable length where second element is 42: ")
        case List(_,_*) => println("Found a list of variable length with at least one element: ")
        case _ => println("Any list that didn't match previous criteria: ")
      }
    }

    // Pattern Matching of Tuples with Guards
    println("-- Tuple Pattern Matching with Guards --------")
    val t1 = ("Good", "Morning")
    val t2 = ("Guten", "Tag")
    val t3 = ("Bon", "Jour")
    for (tuple <- List(t1, t2, t3)) {
      tuple match {
        case (one, _) if one == "Good" => println("An English speaker, good morning")
        case (_, two) if two == "Tag" => println("A German. Oh, and it is morning")
        case (one, two) => printf("Not sure what language that is one=%s and two=%s%n", one, two)
      }
    }

    // Advanced Pattern Matching with Case Classes
    println("-- Case Class Pattern Matching ---------------")
    case class Employee(name:String, age:Int)
    val empl1 = new Employee("Alice", 35)
    val empl2 = new Employee("Bob", 41)
    val empl3 = new Employee("Charlie", 32)
    for (employee <- List(empl1, empl2, empl3)) {
      employee match {
        case Employee("Alice",_) => println("Hello, Alice! Good to see you.")
        case Employee("Charlie",_) => println("Hey Charlie, what's up, dude?")
        case Employee(name,_) => println("Not sure who you are but welcome, " + name)
      }
    }

    // Advanced Pattern Matching binding nested variables in Case Classes
    println("-- Case Class Pattern Matching ---------------")
    class Role
    case object Manager extends Role
    case object Developer extends Role

    case class Person(name:String, age:Int, role:Role)
    val alice = new Person("Alice", 35, Developer)
    val bob = new Person("Bob", 41, Manager)
    val charlie = new Person("Charlie", 32, Developer)

    for (person <- List(1 -> alice, 2 -> bob, 3 -> charlie)) {
      person match {
        case (id, p @ Person(_,_,Manager)) => println(s"$p (ID:$id) is overpaid")
        case (id, p @ Person(_,_,Developer)) => println(s"$p (ID:$id) is underpaid")
      }
    }
  }
}
