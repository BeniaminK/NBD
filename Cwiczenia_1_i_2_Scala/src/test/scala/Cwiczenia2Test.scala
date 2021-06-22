import Cwiczenia2.{KontoBankowe, Nauczyciel, Osoba, Person, Pracownik, Student}
import org.scalatest.FunSuite

class Cwiczenia2Test extends FunSuite {

  test("Cwiczenia2.zad1") {

    val weekDays = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela", "Inne", "Tmp")
    val praca = "Praca"
    val weekend = "Weekend"
    val nieMaDnia = "Nie ma takiego dnia"
    val outputs = List(praca, praca, praca, praca, praca, weekend, weekend, nieMaDnia, nieMaDnia)

    for ((weekDay, output) <- weekDays zip outputs) {
      assert(Cwiczenia2.zad1(weekDay) == output, s"${weekDays} != ${output}")
    }
  }

  test("Cwiczenia2.zad2") {

    def test(konto: KontoBankowe, title: String): Unit = {
      println(s"Test: $title")
      println("Stan konta: " + konto.stanKonta)
      println("Wpłacamy 900.")
      konto.wplata(900)
      println("Stan konta: " + konto.stanKonta)
      println("Wypłacamy 300.")
      konto.wyplata(300)
      println("Stan konta: " + konto.stanKonta)
    }

    val kontoBankowe1 = new KontoBankowe(1200)
    assert(kontoBankowe1.stanKonta == 1200)
    kontoBankowe1.wplata(900)
    assert(kontoBankowe1.stanKonta == 2100)
    kontoBankowe1.wyplata(300)
    assert(kontoBankowe1.stanKonta == 1800)

    val kontoBankoweZero2 = new KontoBankowe()
    assert(kontoBankoweZero2.stanKonta == 0)
    kontoBankoweZero2.wplata(900)
    assert(kontoBankoweZero2.stanKonta == 900)
    kontoBankoweZero2.wyplata(300)
    assert(kontoBankoweZero2.stanKonta == 600)
  }

  test("Cwiczenia2.zad3") {

    val peopleList = List(
      Osoba("Anna", "Zielińska"),
      Osoba("Beniamin", "Kalinowski"),
      Osoba("Celina", "Liberda"),
      Osoba("Daniel", "Stefaniuk"),
      Osoba("Eugeniusz", "Buksa"),
      Osoba("Felix", "Potocki"),
      Osoba("Gabriel", "Wolski"),
      Osoba("Helena", "Jóźwiak")
    )

    val outputs = List(
      "Pani Zielińska. Dzień dobry.",
      "Autor programu. Witam, witam.",
      "Pani Liberda. Dzień dobry.",
      "Witam Daniel Stefaniuk.",
      "Witam Eugeniusz Buksa.",
      "Felix Potocki. Witam Pana.",
      "Gabriel Wolski. Witam Pana.",
      "Witam Helena Jóźwiak."
    )

    for ((person, output) <- peopleList zip outputs) {
      assert(Cwiczenia2.zad3(person) == output)
    }
  }

  test("Cwiczenia2.zad4") {

    assert(Cwiczenia2.zad4(2, x => x * x) == 256)
    assert(Cwiczenia2.zad4(4, x => x + x) == 32)
    assert(Cwiczenia2.zad4(5, x => 10 * x + x) == 6655)

  }

  test("Cwiczenia2.zad5") {

    val a = new Person("Anna", "Jakubiak") with Student
    assert(a.podatek == 0)

    val b = new Person("Beniamin", "Kalinowski") with Pracownik
    b.pensja = 2000
    assert(b.podatek == 400)

    val c = new Person("Cezary", "Liberda") with Nauczyciel
    c.pensja = 3000
    assert(c.podatek == 300)

    val d = new Person("Daniel", "Michalak") with Pracownik
    d.pensja = 4000
    assert(d.podatek == 800)

    d.pensja = -100
    assert(d.podatek == 800)
  }
}


