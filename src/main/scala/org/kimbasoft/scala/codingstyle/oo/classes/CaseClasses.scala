package org.kimbasoft.scala.codingstyle.oo.classes

/**
 * Case classes are best when used as immutable Value Objects.
 *
 * @since 1.0
 */
object CaseClasses {

  case class Point(x: Double, y: Double)

  abstract class Shape() {
    def draw(): Unit
  }

  case class Circle(center: Point, radius: Double) extends Shape {

    def this() = this(Point(0.0, 0.0), 10)

    override def draw(): Unit = {
      println("Circle.draw: " + this)
    }
  }

  case class Rectangle(lowerLeft: Point, height: Double, width: Double) extends Shape {
    override def draw(): Unit = {
      println("Rectangle.draw: " + this)
    }
  }

  case class Triangle(poin1: Point, point2: Point, point3: Point) extends Shape {
    override def draw(): Unit = {
      println("Triangle.draw: " + this)
    }
  }

  def main(args: Array[String]) {
    // Using the automatically generated apply() method for the primary constructor
    val shapes = List(
      Circle(Point(0.0, 1.4), 3.0),
      new Circle(),
      Rectangle(Point(0.0,0.0), 5.0, 7.5),
      Rectangle(Point(7.0,-3.0), 2.0, 4.5),
      Triangle(Point(3.2,6.1), Point(5.5, 1.3), Point(3.9, 8.2)),
      Triangle(Point(5.2,3.3), Point(3.3, -5.8), Point(-9.9, -3.4)))

    // Using the automatically generated hashCode() method
    val shape1 = shapes.head
    println("shape1: " + shape1 + ".hash = " + shape1.hashCode())

    // Using the automatically generated equals() method
    for (shape2 <- shapes)
      println("shape2: " + shape2 + ". shape1 == shape2 ? " + (shape1 == shape2))

    // Using the automatically generated unapply() method
    for (shape3 <- shapes) {
      shape3 match {
        // Match Circles using the unapply method of the auto-generated Companion Object
        case Circle(center, radius) =>
          println("Recognized shape Circle with center=" + center + " and radius=" + radius)
        // Match Rectangles using the unapply method of the auto-generated Companion Object
        case Rectangle(lleft, hg, wd) =>
          println("Recognized shape Rectangle with lower left point=" + lleft + ", height=" + hg + ", and width=" + wd)
        // Match Triangles using an infix operator for unapply method of the auto-generated Companion Object
        case p1 Triangle (p2, p3) =>
          println("Recognized shape Triangle with point1=" + p1 + ", point2=" + p2 + ", and point3=" + p3)
        case shape => println("Unrecognized shape: " + shape)
      }
    }

    // Using the automatically generated copy() method (making use of default and named parameter), Scala 2.8+
    val circle1 = Circle(Point(0.0, 0.0), 4.0)
    val circle2 = circle1.copy(radius = 6.0)
    println("Circle 1: " + circle1)
    println("Circle 2: " + circle2)
  }
}
