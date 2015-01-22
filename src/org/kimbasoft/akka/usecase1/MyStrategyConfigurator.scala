package org.kimbasoft.akka.usecase1

import akka.actor.SupervisorStrategy.{Stop, Restart, Resume}
import akka.actor.{OneForOneStrategy, SupervisorStrategy, SupervisorStrategyConfigurator}

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class MyStrategyConfigurator extends SupervisorStrategyConfigurator {
  /* Implementations of SupervisorStrategyConfigurator must be of type 'class' */
  def create(): SupervisorStrategy = OneForOneStrategy() {
    /* The Matching must return a SupervisorStrategy Directive.
     * Since Directive is a sealed Trait only the following options are possible:
     *    Resume   - Resumes Message processing for the failed Actor
     *    Restart  - Discards the old Actor instance and replaces it with a new, then resumes message processing.
     *    Stop     - Stops the failed Actor
     *    Escalate - Escalates the problem to the next higher supervisor
     */
    case MyActor.ProcessingException => Resume
    case MyActor.GeneralException => Restart
    case _: MyActor.TestException => Stop
  }
}
