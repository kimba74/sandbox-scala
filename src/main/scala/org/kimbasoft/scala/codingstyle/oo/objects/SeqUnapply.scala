package org.kimbasoft.scala.codingstyle.oo.objects

/**
 * The Companion Object definition.
 *
 * @since 1.0
 */
object SeqUnapply {

  /**
   * This apply() factory method will take a variable set of arguments of the
   * same type and will transform them into a List and pass it to a new instance
   * of our SeqUnapply class.
   */
  def apply[A](itm: A*) = new SeqUnapply[A](itm.toList)

  /**
   * In order to match a sequence during a pattern matching the unapplySeq()
   * method needs to be used instead of the regular unapply() method. The
   * important part here is, that the method returns a Sequence, or one of
   * its sub-types. This will allow for a filtering with variable arguments
   * like the following:
   *
   *     case SeqUnapply(one, two, _*) => println(...)
   */
  def unapplySeq[A](sequn: SeqUnapply[A]): Some[Seq[A]] = Some(sequn.content)
}

/**
 * The Class definition.
 *
 * @since 1.0
 */
class SeqUnapply[A](cnt: List[A]) {

  /**
   * This apply() method will take an integer representing the index within the
   * underlying list we want to retrieve the value for. The pattern matching will
   * make sure that the index is within the allowed size and will return the value.
   * Should the index be outside the range of this list a 'None' Option will be returned.
   */
  def apply(idx: Int): Option[A] = {
    cnt.length match {
      case l if l < cnt.length => Some(cnt(idx))
      case _ => None
    }
  }

  /**
   * Simple convenience method to return the content of the SeqUnapply class.
   */
  def content = cnt

  /**
   * Simple method returning the length of the content within the SeqUnapply class.
   */
  def length = cnt.length
}