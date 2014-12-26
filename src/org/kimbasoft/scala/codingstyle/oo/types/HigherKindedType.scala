package org.kimbasoft.scala.codingstyle.oo.types

// Turning on the Higher-Kinded Type Feature
import scala.language.higherKinds

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object HigherKindedType {

  trait Add[T] {
    def add(t1: T, t2: T): T
  }

  object Add {
    implicit val addInt = new Add[Int] {
      def add(t1: Int, t2: Int): Int = t1 + t2
    }
    implicit val addIntIntPair = new Add[(Int,Int)] {
      def add(t1: (Int, Int), t2: (Int, Int)): (Int, Int) = (t1._1 + t2._1, t1._2 + t2._2)
    }
  }

  def sumSeq[T : Add](seq: Seq[T]): T = {
    seq reduce implicitly[Add[T]].add
  }

  /**
   * The Reduce trait functions as an abstraction of reduction (reduce(...) method)
   * for higher-kinded types. Using M as parameter name is an informal convention.
   */
  trait Reduce1[T, -M[T]] {
    def reduce(m: M[T])(f: (T,T) => T): T
  }

  /**
   * Object that defines implicit instances of the Reduce Trait for reducing Seq
   * and Option values.
   */
  object Reduce1 {
    implicit def reduceSeq[T]: Reduce1[T, Seq] = new Reduce1[T, Seq] {
      def reduce(seq: Seq[T])(f: (T, T) => T): T = seq reduce f
    }
    implicit def reduceOption[T]: Reduce1[T, Option] = new Reduce1[T, Option] {
      def reduce(opt: Option[T])(f: (T, T) => T): T = opt reduce f
    }
  }

  /**
   * This method requires a container object of type M that holds a value of type T
   * and an implicit Reduce object with the types T and M. Furthermore, the type T
   * is only valid if an implicit Add[T] object exists (Context Binding) within the
   * context otherwise this code will not compile.
   */
  def sumReduce1[T : Add,M[T]](container: M[T])(implicit red: Reduce1[T,M]): T = {
    red.reduce(container)(implicitly[Add[T]].add)
  }  
  
  
  trait Reduce2[-M[_]] {
    def reduce[T](m: M[T])(f: (T,T) => T): T
  }

  object Reduce2 {
    implicit val reduce2Seq = new Reduce2[Seq] {
      def reduce[T](seq: Seq[T])(f: (T, T) => T): T = seq reduce f
    }
    implicit val reduce2Option = new Reduce2[Option] {
      def reduce[T](option: Option[T])(f: (T, T) => T): T = option reduce f
    }
  }

  def sumReduce2[T : Add, M[_] : Reduce2](container: M[T]): T = {
    implicitly[Reduce2[M]].reduce(container)(implicitly[Add[T]].add)
  }

  def main(args: Array[String]) {
    println("-- No Higher-Kinded Type ---------------------")
    println(sumSeq(Vector(1 -> 10, 2 -> 20, 3 -> 30)))
    println(sumSeq(1 to 10))
//  println(sumSeq(List('a','b','c')))     // Does not compile because no implicit Add[Char] exists
//  println(sumSeq(Option(2)))             // Does not compile since Option is not a sub-type of Seq

    println("-- Higher-Kinded Type ------------------------")
    println(sumReduce1(Vector(2 -> 20, 3 -> 30, 4 -> 40)))
    println(sumReduce1(2 to 20))
    println(sumReduce1(Option(2)))

    println("-- Higher-Kinded Type Improved ---------------")
    println(sumReduce2(Vector(3 -> 30, 4 -> 40, 5 -> 50)))
    println(sumReduce2(3 to 30))
    println(sumReduce2(Option(3)))
  }

}
