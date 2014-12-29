package org.kimbasoft.scala.codingstyle.oo.types

import scala.language.{reflectiveCalls, higherKinds}
import org.kimbasoft.scala.codingstyle.oo.types.TypeLambda.Functor._

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object TypeLambda {

  trait Functor[A,+M[_]] {
    def map2[B](f: A => B): M[B]
  }

  object Functor {
    implicit class SeqFunctor[A](seq: Seq[A]) extends Functor[A,Seq] {
      def map2[B](f: A => B): Seq[B] = seq map f
    }

    implicit class OptionFunctor[A](option: Option[A]) extends Functor[A,Option] {
      def map2[B](f: A => B): Option[B] = option map f
    }

    implicit class MapFunctor[K,V1](mapKV1: Map[K,V1]) extends Functor[V1,({type l[a] = Map[K,a]})#l] {
      def map2[V2](f: V1 => V2): Map[K,V2] = mapKV1 map { case (k,v) => (k, f(v)) }
    }
  }

  def main(args: Array[String]) {
    println(List(1,2,3) map2 (_ * 2))
    println(Option(4) map2 (_ * 3))
    println(Map("one" -> 1, "two" -> 2, "three" -> 3) map2 (_ * 4))
  }
}
