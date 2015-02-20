package org.kimbasoft.scala.codingstyle.oo.objects

/**
 * Missing documentation
 *
 * @since 1.0
 */
object PhantomTypes {

  /* Phantom Types:
   * These sealed Traits are only being used as a type
   * and will not be implemented by any class. They will
   * serve as "Steps" helping to establish a logical
   * order of method calls. */
  sealed trait PreTaxDeductions
  sealed trait PostTaxDeductions
  sealed trait Final

  // Use implicit class as Wrapper
  implicit class Pipeline[V](value: V) {
    def |[R](f: V => R) = f(value)
  }

  /**
   * Case Class that serves as container class for all
   * financial information of an employee.
   */
  case class Employee(
    name: String,
    annualSalary: Float,
    taxRate: Float,  // For simplicity, just 1 rate covering all taxes.
    insurancePremiumsPerPayPeriod: Float,
    _401kDeductionRate: Float,  // A pretax, retirement savings plan in the USA.
    postTaxDeductions: Float)

  /**
   * Case class that holds the current financial calculation
   * for a particular employee. This class uses the "Phantom
   * Types" shown above to narrow down the possibility of
   * methods that can be called next. E.g. minus401K cannot
   * be called after the tax has been taken out via the minusTax
   * method, it can only be called BEFORE the call to minusTax.
   */
  case class Pay[Step](employee: Employee, netPay: Float)

  /**
   * Object containing static methods that allow to manipulate
   * the employee's income in order to calculate the Net value
   * of the salary. These methods only allow the Pay class with
   * certain types to call them thereby establishing an order
   * of invocation a developer will have to abide to.
   */
  object Payroll {
    // Biweekly paychecks. Assume exactly 52 weeks/year for simplicity.
    def start(employee: Employee): Pay[PreTaxDeductions] =
      Pay[PreTaxDeductions](employee, employee.annualSalary / 26.0F)

    def minusInsurance(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] = {
      val newNet = pay.netPay - pay.employee.insurancePremiumsPerPayPeriod
      pay copy (netPay = newNet)
    }

    def minus401k(pay: Pay[PreTaxDeductions]): Pay[PreTaxDeductions] = {
      val newNet = pay.netPay - (pay.employee._401kDeductionRate * pay.netPay)
      pay copy (netPay = newNet)
    }

    def minusTax(pay: Pay[PreTaxDeductions]): Pay[PostTaxDeductions] = {
      val newNet = pay.netPay - (pay.employee.taxRate * pay.netPay)
      pay copy (netPay = newNet)
    }

    def minusFinalDeductions(pay: Pay[PostTaxDeductions]): Pay[Final] = {
      val newNet = pay.netPay - pay.employee.postTaxDeductions
      pay copy (netPay = newNet)
    }

    def debug[T](pay: Pay[T]): Pay[T] = {
      println("Debug> " + pay)
      pay
    }
  }

  def main(args: Array[String]) {
    val employ = Employee("Joe Blow", 78000.0F, 0.25F, 200.0F, 0.15F, 0.05F)
    // Regular, step-wise calculation of the employee's salary

    val pay1 = Payroll start employ
    val pay2 = Payroll minus401k pay1
    val pay3 = Payroll minusInsurance pay2
    val pay4 = Payroll minusTax pay3
    val pay5 = Payroll minusFinalDeductions pay4

    val twoWkGrs = employ.annualSalary / 26.0F
    val twoWkNet = pay5.netPay
    val percent = (twoWkNet / twoWkGrs) * 100

    println(employ.name + ": " + twoWkGrs + " -> " + twoWkNet + " = " + percent + "%")

    /* Using the above defined pipeline function "|" to concatenate
     * the individual financial operations. */
    import org.kimbasoft.scala.codingstyle.oo.objects.PhantomTypes.Payroll._
    val e2PayF = start(employ) | minus401k | minusInsurance | minusTax | minusFinalDeductions
    println(employ.name + ": " + twoWkGrs + " -> " + twoWkNet)

  }

}
