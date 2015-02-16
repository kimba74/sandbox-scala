package org.kimbasoft.akka.supervision

import akka.actor.SupervisorStrategy.{Resume, Stop}
import akka.actor.{OneForOneStrategy, SupervisorStrategy, SupervisorStrategyConfigurator}
import org.kimbasoft.akka.supervision.SupervisionMessages.Exceptions.{IllegalFactorException, IllegalDepthException, IllegalRequestException}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class SupervisionStrategyConfig extends SupervisorStrategyConfigurator {
  /* Implementations of SupervisorStrategyConfigurator must be of type 'class' */
  def create(): SupervisorStrategy = OneForOneStrategy() {
    /* The Matching must return a SupervisorStrategy Directive.
     * Since Directive is a sealed Trait only the following options are possible:
     *    Resume   - Resumes Message processing for the failed Actor
     *    Restart  - Discards the old Actor instance and replaces it with a new, then resumes message processing.
     *    Stop     - Stops the failed Actor
     *    Escalate - Escalates the problem to the next higher supervisor
     */
    case IllegalRequestException =>
      println("Root: IllegalRequestException -> Stopping Actor")
      Stop
    case IllegalDepthException =>
      println("Root: IllegalDepthException -> Resuming Actor")
      Resume
    case IllegalFactorException =>
      println("Root: IllegalFactorException -> Resuming Actor")
      Resume
  }
}
