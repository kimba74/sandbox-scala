package org.kimbasoft.scala.codingstyle.oo

/**
 * Missing documentation
 *
 * @since 1.0
 */
object Linearization {

  /**
   * The algorithm for the linearization of inheritance is defined as follows:
   *   Step 1. Put type of instance as first element
   *   Step 2. Right-to-left compute the linearization of each parent type and append to list
   *   Step 3. Left-to-right remove all duplicate parent types if they appear again further to the right
   *   Step 4. Append ScalaObject, AnyRef, and Any to the list
   *
   * In this example we have the following classes with their parents (leaving out C3 for now):
   *   C1
   *   T1 -> C1
   *   T2 -> C1
   *   T3 -> C1
   *   C2 -> T2 -> C1
   *
   * Applying the linearization algorithm to C3 we will get the following steps:
   *   1. C3                                               - Step 1: Add C3, the type of the instance
   *   2. C3, T3, C1                                       - Step 2: Add right-to-left linearization for T3 (far right)
   *   3. C3, T3, C1, T2, C1                               - Step 2: Add right-to-left linearization for T2
   *   4. C3, T3, C1, T2, C1, T1, C1                       - Step 2: Add right-to-left linearization for T1
   *   5. C3, T3, C1, T2, C1, T1, C1, C2, T2, C1           - Step 2: Add right-to-left linearization for C2
   *   6. C3, T3, T2, T1, C2, T2, C1                       - Step 3: Remove duplicate C1 left-to-right (except last one)
   *   7. C3, T3, T1, C2, T2, C1                           - Step 3: Remove duplicate T2 left-to-right (except last one)
   *   8. C3, T3, T1, C2, T2, C1, ScalaObject, AnyRef, Any - Step 4: Append ScalaObject, AnyRef, and Any
   *
   * So when invoking the callMe() method the order of inheritance will be: C3, T3, T1, C2, T2, C1
   */
  def main(args: Array[String]) {
    val c3 = new C3
    c3.callMe("")
  }

  class C1 {
    def callMe(prefix: String) = println(prefix + "C1 invoked")
  }

  trait T1 extends C1 {
    override def callMe(prefix: String): Unit = {
      println(prefix + "T1 invoked")
      super.callMe(prefix + "  ")
    }
  }

  trait T2 extends C1 {
    override def callMe(prefix: String): Unit = {
      println(prefix + "T2 invoked")
      super.callMe(prefix + "  ")
    }
  }

  trait T3 extends C1 {
    override def callMe(prefix: String): Unit = {
      println(prefix + "T3 invoked")
      super.callMe(prefix + "  ")
    }
  }

  class C2 extends T2 {
    override def callMe(prefix: String): Unit = {
      println(prefix + "C2 invoked")
      super.callMe(prefix + "  ")
    }
  }

  class C3 extends C2 with T1 with T2 with T3 {
    override def callMe(prefix: String): Unit = {
      println(prefix + "C3 invoked")
      super.callMe(prefix + "  ")
    }
  }
}
