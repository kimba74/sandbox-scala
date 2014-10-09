/**
 * Defining a package structure in a Scala file rather than following the
 * Java standard with the directory structure. This approach is not recommended
 * but it is possible. The first package in this file demonstrates the "chaining"
 * of package names (name.name.name)
 *
 * @since 1.0
 */
package org.kimbasoft.scala {

  /* This package is a sub-package under the above create hierarchy */
  package pkg1 {

    class Class11 {
      def name = "Class 1.1"
    }

    class Class12 {
      def name = "Class 1.2"
    }
  }

  /* This package is another sub-package under the above create hierarchy */
  package pkg2 {

    class Class21 {
      def name = "Class 2.1"
      /* Following are two examples to illustrate how to shorthand reference
       * Classes from sibling packages */
      def makeCl11 = new pkg1.Class11
      def makeCl12 = new pkg1.Class12
    }
  }

}
