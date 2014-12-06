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

  /**
   * A class that defines the variance of its parameters during design time. We follow
   * the previous example of an anonymous function and define the parameters of this
   * class the same way: [-T,+R] meaning that type parameter T can only be used in
   * contravariant spots within this class. Analogous type parameter R is defined to
   * be covariant and can only be used as such within the UseCase class.
   *
   * @tparam T this is a contravariant type and can only be used in a contravariant spot
   * @tparam R this is a covariant type and can only be used in a covariant spot
   */
  class UseCase[-T,+R] {
    def useF(in: T): R = {
      println("Received: " + in)
      new R
    }
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

    /* Won't Work:
     * useF will pass a C into the function but function can only accept CSub and it's subtypes
     * because it needs to invoke the parameter's msub() method which is not present in a
     * parameter of type C.
     * useF will try to invoke msuper() and m() on the value returned by the function. msuper()
     * will work but m() will fail since the function returns a CSuper which doesn't have an
     * implementation of method m(). */
//  useF((c: CSub) => {c.msub;new CSuper})


    val usecase = new UseCase[C,C]

    /* Will Work:
     * Since the UseCase class is parametrized with [C,C] the method useF() is expected to take
     * a type C parameter and return a type C parameter. When invoking the method we do exactly
     * that and pass in a type C parameter and try to catch the returned value in a type C field. */
    val v1: C = usecase.useF(new C)

    /* Will Work:
     * Same as in the example above but this time we invoke the method useF() with a type CSub
     * parameter which is allowed since type CSub is a type C. We then try to store the returned
     * value of type C in a field of type CSuper. This will also work because the returned type
     * C ia a type CSuper. */
    val v2: CSuper = usecase.useF(new CSub)

    /* Won't Work:
     * The method useF() expects a type C as parameter but the parameter provided is of type
     * CSuper. This is a violation of the contract because while type C is a type CSuper,
     * type CSuper is not a type C.
     * Also, the method useF() is defined to return a type C but we try to catch the returned
     * value in a type CSub. Same as with the parameter, while type CSub is a type C, type C
     * is not a type CSub. */
//  val v3: CSub = usecase.useF(new CSuper)
  }
}
