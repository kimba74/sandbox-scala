package org.kimbasoft.specs2.contexts

import java.util.UUID

import org.specs2.execute.{Result, AsResult}
import org.specs2.mutable.Specification
import org.specs2.specification.ForEach

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
trait Transaction {
  val id: String
}

trait DatabaseContext extends ForEach[Transaction] {

  protected def foreach[R: AsResult](f: (Transaction) => R): Result = {
    val transaction = openTransaction
    try AsResult(f(transaction))
    finally closeTransaction(transaction)
  }

  def openTransaction: Transaction = {
    val vid = UUID.randomUUID().toString
    println(s"Opening Transaction $vid")
    new Transaction {
      val id: String = vid
    }
  }

  def closeTransaction(t: Transaction) = {
    println(s"Closing Transaction ${t.id}")
  }
}

class ForEachContext extends Specification with DatabaseContext {
  "ForEach Example 1" >> { t: Transaction =>
    println(s"Use first transaction ${t.id}")
    ok
  }

  "ForEach Example 2" >> { t: Transaction =>
    println(s"Use second transaction ${t.id}")
    ok
  }
}
