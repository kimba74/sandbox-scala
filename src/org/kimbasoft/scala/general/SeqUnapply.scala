package org.kimbasoft.scala.general

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object SeqUnapply {
  def apply[A](itm: A*) = new SeqUnapply[A](itm.toList)

  def unapplySeq[A](sequn: SeqUnapply[A]): Some[Seq[A]] = Some(sequn.content)
}

class SeqUnapply[A](cnt: List[A]) {
  def apply(idx: Int) = {
    cnt.length match {
      case l if l < cnt.length => Some(cnt(idx))
      case _ => None
    }
  }

  def content = cnt

  def length = cnt.length
}