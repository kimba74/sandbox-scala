package org.kimbasoft.specs2.scalacheck

import org.scalacheck.{Arbitrary, Gen}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object GeneratorsScalaCheck extends App {

  /* Generating random tuples with (semi)random values */
  val tupleGen = for {
    n <- Gen.choose(10, 20)
    m <- Gen.choose(2*n, 500)
  } yield (n, m)
  println("Tuple Gen : " + tupleGen.sample)

  /* Generating a String by randomly selecting one of a provided list */
  val stringGen = Gen.oneOf("a", "b", "c", "d")
  println("String Gen: " + stringGen.sample)

  /* Generating a random String */
  val randStringGen = Gen.alphaStr
  println("Random String Gen: " + randStringGen.sample)

  /* Generating a small even Integer values by setting a range then
   * filtering the values based in even and odd */
  val evenIntGen = Gen.choose(0,200) suchThat (_ % 2 == 0)
  println("Small Even Int Gen: " + evenIntGen.sample)

  /* Generating a list with random length from provided list */
  val selectionGen = Gen.someOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
  println("Random List length Gen: " + selectionGen.sample)

  /* Generating a list of random length with only the provided elements */
  val intListGen = Gen.containerOf[List,Int](Gen.oneOf(1, 3, 5))
  println("Integer List Gen: " + intListGen.sample)

  /* Generating an array of boolean values all of value 'true' */
  val booleanArrayGen = Gen.containerOf[Array,Boolean](true)
  println("Boolean Array Gen: " + booleanArrayGen.sample)

  /* Generating arbitrary Integer number and limiting it to even numbers only */
  val evenNumber = Arbitrary.arbitrary[Int] suchThat (_ % 2 == 0)
  println("Arbitrary even Integer Number (or None): " + evenNumber.sample)

  /* Generating an arbitrary String */
  val arbitraryString = Arbitrary.arbitrary[String]
  println("Arbitrary String: " + arbitraryString.sample)

  /* Generating an arbitrary Int List */
  val arbitraryList = Arbitrary.arbitrary[List[Int]]
  println("Arbitrary Int List: " + arbitraryList.sample)

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