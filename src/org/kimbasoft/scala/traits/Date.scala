package org.kimbasoft.scala.traits

/**
 * Missing documentation
 *
 * @since 1.0
 */
class Date(y: Int, m: Int, d: Int) extends Test {

  def year = y

  def month = m

  def day = d

  override def toString: String = s"$y-$m-$d"

  override def equals(that: Any): Boolean = that.isInstanceOf[Date] && {
    val o = that.asInstanceOf[Date]
    o.day == day && o.month == month && o.year == year
  }

  override def <(that: Any): Boolean = {
    if (!that.isInstanceOf[Date])
      sys.error(s"cannot compare $that and a Date")
    val o = that.asInstanceOf[Date]
    (year < o.year) || (year == o.year && (month < o.month || (month == o.month && day < o.day)))
  }
}
