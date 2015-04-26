package org.kimbasoft.specs2.scalacheck

import org.scalacheck.{Arbitrary, Gen}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object GeneratorsScalaCheck extends App {

  println("-- Simple Generators -------------------------------------")
  /* Generating a random number Char */
  val simpleNumChar = Gen.numChar
  println("Simple Number Char: " + simpleNumChar.sample)

  /* Generating a random number String */
  val simpleNumString = Gen.numStr
  println("Simple Number String: " + simpleNumString.sample)

  /* Generating a random alpha-numeric Char */
  val simpleAlphaNumChar = Gen.alphaNumChar
  println("Simple Alpha-Numeric Char: " + simpleAlphaNumChar.sample)

  /* Generating a random alpha-numeric String */
  val simpleAlphaNumString = Gen.identifier
  println("Simple Alpha-Numeric String: " + simpleAlphaNumString.sample)
  
  /* Generating a random alpha String */
  val simpleAlphaChar = Gen.alphaChar
  println("Simple Alpha Char: " + simpleAlphaChar.sample)

  /* Generating a random alpha String */
  val simpleAlphaString = Gen.alphaStr
  println("Simple Alpha String: " + simpleAlphaString.sample)


  println("\n-- Container Generators ----------------------------------")
  /* Generating random tuples with (semi)random values */
  val contTuple = for {
    n <- Gen.choose(10, 20)
    m <- Gen.choose(2*n, 500)
  } yield (n, m)
  println("Tuple Container : " + contTuple.sample)

  /* Generating a list of random length with only the provided elements */
  val contIntList = Gen.containerOf[List, Int](Gen.oneOf(1, 3, 5))
  println("Integer List: " + contIntList.sample)

  /* Generating an array of boolean values all of value 'true' */
  val contBooleanArray = Gen.containerOf[Array, Boolean](true)
  println("Boolean Array: " + contBooleanArray.sample)


  println("\n-- Conditional Generators ------------------------------")
  /* Generating a String by randomly selecting one of a provided list */
  val conditionString = Gen.oneOf("a", "bc", "def", "ghij") suchThat (s => s.contains(Gen.oneOf("a", "c", "e", "h"))) 
  println("Conditional String: " + conditionString.sample)

  /* Generating an Integer value from provided range */
  val conditionEvenNum = Gen.choose(0,200) suchThat (i => i % 2 == 0)
  println("Conditional even Int: " + conditionEvenNum.sample)

  /* Generating a list with random length from provided list */
  val conditionShortList = Gen.someOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9) suchThat (l => l.length < 5)
  println("Conditional short List: " + conditionShortList.sample)
  

  println("\n-- Selective Generators --------------------------------")
  /* Generating a String by randomly selecting one of a provided list */
  val selectString = Gen.oneOf("abc", "def", "ghi", "jkl")
  println("Selecting String from list: " + selectString.sample)

  /* Generating an Integer value from provided range */
  val selectIntRange = Gen.choose(0,200)
  println("Selecting Int from Range: " + selectIntRange.sample)

  /* Generating a list with random length from provided list */
  val selectList = Gen.someOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
  println("Selecting Sub-List from List: " + selectList.sample)


  println("\n-- Arbitrary Value Generators --------------------------")
  val arbIntNumber = Arbitrary.arbitrary[Int]
  println("Arbitrary Integer Number: " + arbIntNumber.sample)

  /* Generating an arbitrary String */
  val arbitraryString = Arbitrary.arbitrary[String]
  println("Arbitrary String: " + arbitraryString.sample)

  /* Generating an arbitrary Int List */
  val arbitraryList = Arbitrary.arbitrary[List[Int]]
  println("Arbitrary Int List: " + arbitraryList.sample)


  println("\n-- Case Class Generators -------------------------------")
  /* Generating an Int Tree from Case Classes */
  val treeGen = TreeFactory.genTree
  println("Case Tree Gen: " + treeGen.sample)
}

sealed abstract class Tree

case class Node(left: Tree, right: Tree, value: Int) extends Tree

case object Leaf extends Tree

object TreeFactory {

  val genLeaf = Gen.const(Leaf)

  val genNode = for {
    v <- Arbitrary.arbitrary[Int]
    left <- genTree
    right <- genTree
  } yield Node(left, right, v)

  def genTree: Gen[Tree] = Gen.oneOf(genLeaf, genNode)

}