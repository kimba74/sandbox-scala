package org.kimbasoft.scala.codingstyle.oo.objects

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object TypeVariance {

  class CSuper {
    def msuper = println("CSuper")
  }

  class C extends CSuper {
    def m = println("C")
  }

  class CSub extends C {
    def msub = println("CSub")
  }

  // C => C is inferred to be Function1[-T,+R]
  def useF(f: C => C) = {
    val c1 = new C
    val c2: C = f(c1)
    c2.msuper
    c2.m
  }

  def main(args: Array[String]): Unit = {
    /* Will work:
     * useF will pass a C into the function and call m() and msuper() on its returned value. */
    useF((c: C) => new C)

    /* Will work:
     * useF will pass a C into the function and call msuper() and m() on its returned value.
     * CSuper parameter can accept a C because C is a subtype CSuper
     * CSub result type can execute m() and msub() because CSub is a subtype C */
    useF((c: CSuper) => new CSub)

    /* Won't Work
     * useF will pass a C into the function but function can only accept CSub and it's subtypes
     * because it needs to invoce the parameter's msub() method which is not present in a
     * parameter of type C.
     * useF will try to invoke msuper() and m() on the value returned by the function. msuper()
     * will work but m() will fail since the function returns a CSuper which doesn't have an
     * implementation of method m(). */
//  useF((c: CSub) => {c.msub;new CSuper})
  }
}
