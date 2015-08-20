package org.kimbasoft.scala.reflect

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class TestClass(name: String) extends TestTrait {
  val id = 1234
  var status = "ALIVE"
  override def toString: String = s"This is TestClass($name)"
  def aTest(num: Int): String = s"number was $num"
  def myTest(name: String)(id: Int = -1): Unit = {}
  def aFunct(f: (String) => Boolean): Boolean = f("Hello")
}

object TestClass {
  def apply(name: String): TestClass = new TestClass(name)
}

object TestObject {
  override def toString: String = "This is TestObject"
}

case class TestCase(name: String) {
  override def toString: String = s"This is TestCase($name)"
}

trait TestTrait extends UberTestTrait

trait UberTestTrait