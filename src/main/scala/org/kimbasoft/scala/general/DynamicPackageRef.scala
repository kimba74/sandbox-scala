package org.kimbasoft.scala.general

import org.kimbasoft.scala.pkg2.Class21

/**
 * Missing documentation
 *
 * @since 1.0
 */
object DynamicPackageRef {

  def main(args: Array[String]) {
    /* The following classes are referenced from the packages defined
     * in the DynamicPackages.scala file found in the src/ directory
     * of this project */
    val class21 = new Class21
    val class11 = class21.makeCl11
    val class12 = class21.makeCl12

    println(class11.name)
    println(class12.name)
    println(class21.name)
  }
}
