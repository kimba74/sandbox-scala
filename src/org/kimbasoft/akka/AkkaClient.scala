package org.kimbasoft.akka

import akka.actor.{ActorRef, ActorSystem}

/**
 * Missing documentation
 *
 * @since 1.0
 */
object AkkaClient {
  import Messages._

  private var system: Option[ActorSystem] = None

  def main(args: Array[String]) {
    processArgs(args)
    val sys = ActorSystem("AkkaClient")
    system = Some(sys)
    val server = ServerActor.make(sys)
    val numOfWorkers = 5 // sys.settings.config.getInt("server.numWorkers")
    server ! Start(numOfWorkers)
    processInput(server)
  }

  private def processArgs(args: Seq[String]): Unit = args match {
    case Nil => Nil
    case ("-h" | "--help") +: tail => exit(help, 0)
    case head +: tail => exit(s"Unknown input $head!\n" + help, 1)
  }

  private def processInput(server: ActorRef): Unit = {
    val blankRE = """^\s*#?\s*$""".r
    val badCrashRE = """^\s*[Cc][Rr][Aa][Ss][Hh]\s*$""".r
    val crashRE = """^\s*[Cc][Rr][Aa][Ss][Hh]\s+(\d+)\s*$""".r
    val dumpRE = """^\s*[Dd][Uu][Mm][Pp](\s+\d+)?\s*$""".r
    val charNumberRE = """^\s*(\w)\s+(\d+)\s*$""".r
    val charNumberStringRE = """^\s*(\w)\s+(\d+)\s+(.*)$""".r

    def prompt() = print(">> ")
    def missingActorNumber() = println("Crash command requires an actor number.")
    def invalidInput(s: String) = println(s"Unrecognized command: $s")
    def invalidCommand(c: String) = println(s"Expected commands are 'c', 'r', 'u', and 'd'. Got: $c")
    def invalidNumber(s: String) = println(s"Expected a number. Got: $s")
    def expectedString(c: String) = println(s"Expected a string and a number after the command '$c'")
    def unexpectedString(c: String, n: Int) = println(s"Extra arguments after command and number '$c $n'")
    def finished(): Nothing = exit("Goodbye!", 0)

    val handleLine: PartialFunction[String,Unit] = {
      case blankRE() => /* do nothing */
      case ("h" | "help") => println(help)
      case dumpRE(n) => server ! (if (n == null) DumpAll else Dump(n.trim.toInt))
      case badCrashRE() => missingActorNumber()
      case crashRE(n) => server ! Crash(n.toInt)
      case charNumberStringRE(c, n, s) => c match {
        case "c" | "C" => server ! Create(n.toLong, s)
        case "u" | "U" => server ! Update(n.toLong, s)
        case "r" | "R" => unexpectedString(c, n.toInt)
        case "d" | "D" => unexpectedString(c, n.toInt)
        case _ => invalidCommand(c)
      }
      case charNumberRE(c,n) => c match {
        case "r" | "R" => server ! Read(n.toLong)
        case "d" | "D" => server ! Delete(n.toLong)
        case "c" | "C" => expectedString(c)
        case "u" | "U" => expectedString(c)
        case _ => invalidCommand(c)
      }
      case "q" | "quit" | "exit" => finished()
      case string => invalidInput(string)
    }

    while(true) {
      prompt()
      Console.in.readLine() match {
        case null => finished()
        case line => handleLine(line)
      }
    }
  }

  private val help =
      """Usage: AkkaClient [-h | --help]
        |Then, enter one of the following commands, one per line:
        | h | help Print this help message.
        | c n string Create "record" for key n for value string.
        | r n Read record for key n. It's an error if n isn't found.
        | u n string Update (or create) record for key n for value string.
        | d n Delete record for key n. It's an error if n isn't found.
        | crash n "Crash" worker n (to test recovery).
        | dump [n] Dump the state of all workers (default) or worker n.
        | ^d | quit Quit.
        |""".stripMargin

  private def exit(message: String, status: Int): Nothing = {
    for (sys <- system) sys.shutdown()
    println(message)
    sys.exit(status)
  }
}
