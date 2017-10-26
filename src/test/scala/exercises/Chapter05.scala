package exercises

import org.scalatest.{FreeSpec, Matchers}


class Chapter05 extends FreeSpec with Matchers {

  "Classes" - {

    "Exercise 1" - {
      """--------
        |Improve the Counter class in Section 5.1, “Simple Classes and Parameterless Methods,”
        |on page 55 so that it doesn't turn negative at Int.MaxValue.
        |
        |  class Counter {
        |    private var value = 0
        |    def increment() { value += 1 }
        |    def current() = value
        |  }
      """.stripMargin in {

        class Counter {
          private var value = 0

          def current() = value

          def increment() {
            if (value < Int.MaxValue) value += 1
          }
        }

        val counter = new Counter()

        (1 to Int.MaxValue) foreach { _ => counter.increment() }
        counter.current() should be(Int.MaxValue)

        counter.increment()
        counter.current() should be(Int.MaxValue)
      }
    }

    "Exercise 2" - {
      """--------
        |Write a class BankAccount with methods deposit and withdraw, and a read-only property balance.
      """.stripMargin in {

        import scala.util.Try

        class BankAccount(initialBalance: Double) {
          private var accountBalance = initialBalance

          def balance: Double = accountBalance

          def deposit(amount: Double): Double = {
            accountBalance += amount
            accountBalance
          }

          def withdraw(amount: Double): Try[Double] = {
            Try(if (amount > accountBalance)
              throw new IllegalStateException("Not enough cash")
            else {
              accountBalance -= amount
              accountBalance
            })
          }
        }

        val account = new BankAccount(10000.0)

        account.balance should be(10000.0)
        "account.balance = 15000.0" shouldNot compile

        account.deposit(1000.0)
        account.balance should be(11000.0)

        account.withdraw(6000.0).isSuccess should be(true)
        account.balance should be(5000.0)

        account.withdraw(6000.0).isFailure should be(true)
      }
    }

    "Exercise 3" - {
      """--------
        |Write a class Time with read-only properties hours and minutes and a method
        |before(other: Time): Boolean that checks whether this time comes before the other.
        |A Time object should be constructed as new Time(hrs, min), where hrs is in
        |military time format (between 0 and 23).
      """.stripMargin in {

        class Time(hrs: Int, min: Int) {
          if (hrs < 0 || hrs > 23) throw new IllegalArgumentException("Invalid hours")
          if (min < 0 || min > 59) throw new IllegalArgumentException("Invalid minutes")
          private val hour = hrs
          private val minute = min

          def hours: Int = hour

          def minutes: Int = minute

          def before(other: Time): Boolean = {
            if (other.hours < this.hours) false
            else if (other.hours == this.hours && other.minutes <= this.minutes) false
            else true
          }
        }

        val midnight = new Time(0, 0)

        midnight.hours should be(0)
        midnight.minutes should be(0)

        "midnight.hours = 2" shouldNot compile
        "midnight.minutes = 10" shouldNot compile

        val midday = new Time(12, 0)

        midnight.before(midday) should be(true)
        midday.before(midnight) should be(false)

        an[IllegalArgumentException] should be thrownBy new Time(24, 0)
        the[IllegalArgumentException] thrownBy new Time(23, 64) should have message "Invalid minutes"
      }
    }

    "Exercise 4" - {
      """--------
        |Reimplement the Time class from the preceding exercise so that the internal
        |representation is the number of minutes since midnight (between 0 and 24 × 60 – 1).
        |Do not change the public interface. That is, client code should be unaffected
        |by your change.
      """.stripMargin in {


        class Time(hrs: Int, min: Int) {
          if (hrs < 0 || hrs > 23) throw new IllegalArgumentException("Invalid hours")
          if (min < 0 || min > 59) throw new IllegalArgumentException("Invalid minutes")
          private val mins = (hrs * 60) + min

          def hours: Int = mins / 60

          def minutes: Int = mins % 60

          def before(other: Time): Boolean = this.mins < other.mins
        }

        val midnight = new Time(0, 0)

        midnight.hours should be(0)
        midnight.minutes should be(0)

        "midnight.hours = 2" shouldNot compile
        "midnight.minutes = 10" shouldNot compile

        val midday = new Time(12, 0)

        midnight.before(midday) should be(true)
        midday.before(midnight) should be(false)

        an[IllegalArgumentException] should be thrownBy new Time(24, 0)
        the[IllegalArgumentException] thrownBy new Time(23, 64) should have message "Invalid minutes"
      }
    }

    "Exercise 5" - {
      """--------
        |Make a class Student with read-write JavaBeans properties name (of type String)
        |and id (of type Long). What methods are generated? (Use javap to check.) Can
        |you call the JavaBeans getters and setters in Scala? Should you?
      """.stripMargin in {

        import scala.beans.BeanProperty

        class Student {
          @BeanProperty var name: String = _
          @BeanProperty var id: Long = _
        }

        val student = new Student()
        student.setId(1L)
        student.setName("Josh")

        student.getId should be(1L)
        student.getName should be("Josh")
      }
    }

    "Exercise 6" - {
      """--------
        |In the Person class of Section 5.1, “Simple Classes and Parameterless Methods,”
        |on page 55, provide a primary constructor that turns negative ages to 0.
        |
        |  public class Person {
        |    public int age;
        |  }
      """.stripMargin in {

        class Person(_age: Int) {
          val age: Int = if (_age > 0) _age else 0
        }

        val john = new Person(12)
        val peter = new Person(-17)

        john.age should be(12)
        peter.age should be(0)
      }
    }

    "Exercise 7" - {
      """--------
        |Write a class Person with a primary constructor that accepts a string containing
        |a first name, a space, and a last name, such as new Person("Fred Smith"). Supply
        |read-only properties firstName and lastName. Should the primary constructor
        |parameter be a var, a val, or a plain parameter? Why?
      """.stripMargin in {

        class Person(val fullName: String) {
          def firstName: String = fullName.split(" ").head

          def lastName: String = fullName.split(" ").last
        }

        val peterSmith = new Person("Peter Smith")
        peterSmith.firstName should be("Peter")
        peterSmith.lastName should be("Smith")

        info("The constructor argument could be a val if you want to expose the full name")
        info("The constructor argument could be a parameter if you don't want to expose the full name")
        info("The constructor argument could be a var if you want to expose the full name, and also make it mutable :(")
      }
    }

    "Exercise 8" - {
      """--------
        |Make a class Car with read-only properties for manufacturer, model name, and model
        |year, and a read-write property for the license plate. Supply four constructors.
        |All require the manufacturer and model name. Optionally, model year and license
        |plate can also be specified in the constructor. If not, the model year is set to
        |-1 and the license plate to the empty string. Which constructor are you choosing
        |as the primary constructor? Why?
      """.stripMargin in {

        class Car(val manufacturer: String, val model: String, val year: Int, var licencePlate: String) {
          def this(manufacturer: String, model: String, year: Int) {
            this(manufacturer, model, year, "")
          }

          def this(manufacturer: String, model: String, licencePlate: String) {
            this(manufacturer, model, -1, licencePlate)
          }

          def this(manufacturer: String, model: String) {
            this(manufacturer, model, -1, "")
          }
        }

        val manufacturer = "Fiat"
        val model = "Punto"
        val year = 2000
        val licensePlate = "1234 ABC"
        val car1 = new Car(manufacturer, model, year, licensePlate)
        val car2 = new Car(manufacturer, model, year)
        val car3 = new Car(manufacturer, model, licensePlate)
        val car4 = new Car(manufacturer, model)

        car1.year should be(year)
        car2.year should be(year)
        car3.year should be(-1)
        car4.year should be(-1)

        car1.licencePlate should be(licensePlate)
        car2.licencePlate should be("")
        car3.licencePlate should be(licensePlate)
        car4.licencePlate should be("")

        info("The primary constructor is the one that contains all the parameters")
        info("Auxiliary constructors need to call other auxiliary constructors or the primary constructors")
        info("This is why primary constructor having all the parameters is the best choice")
      }
    }

    "Exercise 9" - {
      """--------
        |Reimplement the class of the preceding exercise in Java, C#, or C++ (your choice).
        |How much shorter is the Scala class?
      """.stripMargin ignore {

      }
    }

    "Exercise 10" - {
      """---------
        |Consider the class
        |
        |  class Employee(val name: String, var salary: Double) {
        |    def this() { this("John Q. Public", 0.0) }
        |  }
        |
        |Rewrite it to use explicit fields and a default primary constructor.
        |Which form do you prefer? Why?
      """.stripMargin ignore {

      }
    }
  }
}
