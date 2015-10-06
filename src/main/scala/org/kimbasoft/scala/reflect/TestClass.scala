package org.kimbasoft.scala.reflect

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class TestClass(name: String, id: Int) extends AbstractTestClass with TestTrait {

  type MyType = String

  val number = 1234

  var status = "ALIVE"

  private val privateValue = -1

  private[reflect] val privateScope = -2

  protected val protectedValue = -3

  protected[reflect] val protectedScope = -4

  def this(name: String){
    this(name, -1)
  }

  override def traitMethod(str: String): Unit = {}

  override def abstractMethod(str: String): String = s"Default Implementation with '$str'"

  override def toString: String = s"This is TestClass($name)"

  def intTest(num: Int): String = s"number was $num"

  def defaultValueTest(id: Int = -1, name: String = "unknown"): Unit = {}

  def callByNameTest(num: => Int): String = s"number was $num"

  def curryingTest(name: String)(id: Int = -1): Unit = {}

  def functionTest(f: (String) => Boolean): Boolean = f("Hello")

  def overloadTest(str: String): Int = overloadTest(str, 0)

  def overloadTest(int: Int): Int = overloadTest("", int)

  def overloadTest(str: String, int: Int) = int + str.length

  def varargsTest(num: Int, str: String, bol: Boolean*): Unit = {}

  private class InnerTestClass {

    def innerMethod(int: Int): Boolean = int % 2 != 0
  }
}


object TestClass {

  def apply(name: String, number: Int): TestClass = new TestClass(name, number)

  def apply(name: String): TestClass = new TestClass(name, -1)

  def unapply(inst: TestClass): Option[(Int, String)] = {
    Some((inst.number, inst.status))
  }
}


abstract class AbstractTestClass {

  def abstractMethod(str: String): String
}

trait MixinTestClass extends AbstractTestClass {

  abstract override def abstractMethod(str: String): String = s">>>${super.abstractMethod(str)}<<<"
}


object TestObject {

  override def toString: String = "This is TestObject"
}


case class TestCase(name: String) {

  override def toString: String = s"This is TestCase($name)"
}


trait TestTrait extends UberTestTrait {

  def traitMethod(str: String): Unit
}

trait UberTestTrait { }