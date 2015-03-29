package org.kimbasoft.scala.codingstyle.oo.types

// Turning on the Higher-Kinded Type Feature
import scala.language.higherKinds

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object HigherKindedType extends App {

  /**
   * Abstract Trait 'Add' that defines an 'add()' method for the defined type T
   */
  trait Add[T] {
    def add(t1: T, t2: T): T
  }

  /**
   * Companion object to trait 'Add' that provides implicit implementations of the
   * abstract Trait for the types 'Int' and Tuple2 '(Int,Int)'
   */
  object Add {
    implicit val addInt = new Add[Int] {
      def add(t1: Int, t2: Int): Int = t1 + t2
    }
    implicit val addIntIntPair = new Add[(Int,Int)] {
      def add(t1: (Int, Int), t2: (Int, Int)): (Int, Int) = (t1._1 + t2._1, t1._2 + t2._2)
    }
  }

  /**
   * Summing method that takes arguments of type 'Seq' that are parametrized with type T.
   * The type T is context bound and requires an implementation Add for type T to be present
   * in the current context, is the object not present the code will not compile. The
   * method will implicitly call the Add[T] object and invoke the add() method on it.
   * This method will only allow Seq sub-types to be used and will not allow custom collections
   * not part of the Seq sub-type system. This is highly undesirable since a new method
   * must be implemented for any collection sub-type. The Reduce1 example will explore
   * a more flexible approach to that problem.
   */
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
   * Companion Object for Reduce1 Trait that defines implicit instances of the Trait
   * for reducing Seq and Option values.
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
   * and an implicit Reduce1 object parametrized with the types T and M. Furthermore,
   * the type T is only valid if an implicit Add[T] object exists (Context Binding)
   * within the context otherwise this code will not compile.
   * This approach lets us use the Add Trait on custom collections and other container
   * objects that were not accepted by the sumSeq() method due to the fact that they
   * were not sub-types of Seq. However, this approach is a bit ugly. We would rather
   * have the Reduce1 object to be context bound rather than having to pass it in via
   * 'implicit' in a second argument list. With the Reduce1 approach this cannot be
   * accomplished since the Reduce1 Trait has two parameters rather than one. The
   * Reduce2 approach will try to address this issue.
   */
  def sumReduce1[T : Add,M[T]](container: M[T])(implicit red: Reduce1[T,M]): T = {
    red.reduce(container)(implicitly[Add[T]].add)
  }

  /**
   * The Reduce2 Trait is very similar to the Reduce1 Trait in that it defines an
   * abstract 'reduce()' method. The main difference is that the value parameter T
   * has been moved from the trait to the method definition. This change however
   * prevents the use of the value parameter T in the container parameter M declaration.
   * The container parameter M will therefore be declared as an Existential Type.
   */
  trait Reduce2[-M[_]] {
    def reduce[T](m: M[T])(f: (T,T) => T): T
  }

  /**
   * The Companion Object of the Reduce2 trait provides implicit instances of Trait
   * for the Seq and Option containers. Other than its sibling the Reduce1 companion
   * object this object defines the implicit instances as 'val' rather than as
   * factory method. This was made possible by moving the value parameter T from the
   * trait and moving it to the method declaration thus eliminating the need for
   * having a variable parameter around. The value parameter T will not be required
   * until the Reduce2.reduce() method is actually invoked.
   */
  object Reduce2 {
    implicit val reduce2Seq = new Reduce2[Seq] {
      def reduce[T](seq: Seq[T])(f: (T, T) => T): T = seq reduce f
    }
    implicit val reduce2Option = new Reduce2[Option] {
      def reduce[T](option: Option[T])(f: (T, T) => T): T = option reduce f
    }
  }

  /**
   * The change in the Reduce2 trait definition and the elimination of the value
   * parameter T allows us to context bind the container parameter M and remove
   * the ugly second argument list with the implicit Reduce argument. Now the
   * Reduce2.reduce() method can be invoked using 'implicitly' like we used for
   * the Add.add() method from the beginning.
   * This approach now elegantly disconnected the container from its value type
   * and thus permits us handle container implementations separately from their
   * value implementations in a flexible, extensible, and elegant manner.
   */
  def sumReduce2[T : Add, M[_] : Reduce2](container: M[T]): T = {
    implicitly[Reduce2[M]].reduce(container)(implicitly[Add[T]].add)
  }


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
