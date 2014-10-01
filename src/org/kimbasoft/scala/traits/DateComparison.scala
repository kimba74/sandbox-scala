package org.kimbasoft.scala.traits

/**
 * Missing documentation
 *
 * @since 1.0
 */
object DateComparison {

  def main(args: Array[String]): Unit = {
    val date1 = new Date(2014,10,1)
    val date2 = new Date(2013,10,1)
    println(s"$date1 < $date2 = ${date1 < date2}")
    println(s"$date1 > $date2 = ${date1 > date2}")
  }
}
