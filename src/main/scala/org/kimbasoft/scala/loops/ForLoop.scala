package org.kimbasoft.scala.loops

/**
 * Missing documentation
 *
 * @since 1.0
 */
object ForLoop extends App {
  // Regular For loop (from 1 - 9)
  println("-- For Loop ------------------------")
  for (i <- 1 to 9) // same as 1.to(9)
    println(s"$i.> Iteration")

  // Empty for loop (?)
  println("-- Empty For Loop ------------------")
  for (_ <- 1 to 9)
    println("empty> iteration")

  // Foreach loop over collection
  println("-- For-Each Loop -------------------")
  val dogBreeds = List("Doberman", "Yorkshire Terrier", "Dachshund", "Scottish Terrier", "Great Dane", "Schnauzer")
  for (dog <- dogBreeds)
    println(s"dog> $dog")

  // Simplified foreach loop over same collection. Suitable for singe statement bodied loops.
  println("-- For-Each Loop (one-liner) -------")
  dogBreeds.foreach(printf("dog> %s%n", _))

  // Foreach loop over collection with filter
  println("-- For-Each Loop filter ------------")
  for (dog <- dogBreeds if dog.contains("Terrier"))
    println(s"terrier> $dog")

  // Foreach loop over collection with multiple filters
  println("-- For-Each Loop multiple filter ---")
  for (dog <- dogBreeds if dog.contains("Terrier"); if !dog.contains("Yorkshire"))
    println(s"terrier> $dog")

  // Foreach loop over collection with multiple filters yielding and accumulating filtered list
  println("-- For-Each Loop yielding results --")
  val terrierBreed = for {
    dog <- dogBreeds
    if dog.contains("Terrier")
    if !dog.contains("Yorkshire")
  } yield dog
  println(terrierBreed)

  // For comprehension over two maps with a filter yielding and accumulating filtered list
  val owners = Map("John" -> 1, "Jason" -> 2, "Mary Ellen" -> 3, "Erin" -> 2, "Ben" -> 4)
  val cars = Map(1 -> "Ford", 2 -> "Oldsmobile", 3 -> "Buick", 4 -> "Adler")
  val carOwners = for {
    (name, carID) <- owners
    car <- cars.get(carID)
    if car == "Oldsmobile"
  } yield (name, car)
  println(carOwners)
}
