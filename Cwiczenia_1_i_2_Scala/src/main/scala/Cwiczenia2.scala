object Cwiczenia2 extends App {

  println("Zadanie 1.")
  /**
   * Wykorzystaj Pattern Matching w funkcji przyjmującej parametr typu String. Dla stringów odpowiadających nazwom dni
   * tygodnia funkcja ma zwrócić „Praca” i „Weekend” (odpowiednio dla dni roboczych i wolnych), dla pozostałych napisów
   * „Nie ma takiego dnia”.
   */
  val weekDays = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")

  def zad1(weekDay: String) = weekDay.toLowerCase match {
    case "poniedziałek" | "wtorek" | "środa" | "czwartek" | "piątek" => "Praca"
    case "sobota" | "niedziela" => "Weekend"
    case _ => "Nie ma takiego dnia"
  }

  for (s <- weekDays :+ "Październik" :+ "Pies") {
    println(f"$s%12s: ${zad1(s)}")
  }

  println("Zadanie 2.")

  /**
   * Zdefiniuj klasę `KontoBankowe` z metodami:
   * * `wplata`
   * * `wyplata`
   * oraz własnością:
   * * `stanKonta` - własność ma być tylko do odczytu.
   * Klasa powinna udostępniać konstruktor przyjmujący początkowy stan konta oraz drugi,
   * ustawiający początkowy stan konta na 0.
   */

  class KontoBankowe(private var _stanKonta: BigDecimal = 0) {

    def stanKonta: BigDecimal = _stanKonta

    def wplata(wartosc: BigDecimal): Unit = {
      _stanKonta = stanKonta + wartosc
    }

    def wyplata(wartosc: BigDecimal): Unit = {
      _stanKonta = stanKonta - wartosc
    }

    override def toString: String = s"Konto: <${_stanKonta.toString}>"
  }

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

  val kontoBankowe = new KontoBankowe(1200)
  test(kontoBankowe, "1200 - initialized bank account")

  val kontoBankoweZero = new KontoBankowe()
  test(kontoBankoweZero, "Zero-initialized bank account")

  println("Zadanie 3.")

  /**
   * Zdefiniuj klasę Osoba z własnościami imie i nazwisko. Stwórz kilka instancji tej klasy. Zdefiniuj funkcję, która
   * przyjmuje obiekt klasy osoba i przy pomocy Pattern Matching wybiera i zwraca napis zawierający przywitanie danej
   * osoby. Zdefiniuj 2-3 różne przywitania dla konkretnych osób (z określonym imionami lub nazwiskami) oraz jedno
   * domyślne.
   */
  case class Osoba(imie: String, nazwisko: String)

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

  def zad3(person: Osoba) = person match {
    case x if x.imie.equals("Beniamin") && x.nazwisko.equals("Kalinowski") => s"Autor programu. Witam, witam."
    case x if x.imie.endsWith("a") && x.nazwisko.endsWith("a") => s"Pani ${x.nazwisko}. Dzień dobry."
    case x if !x.imie.endsWith("a") && x.nazwisko.endsWith("i") => s"${x.imie} ${x.nazwisko}. Witam Pana."
    case _ => s"Witam ${person.imie} ${person.nazwisko}."
  }

  for (o <- peopleList) {
    println(zad3(o))
  }

  println("Zadanie 4.")
  /**
   * Zdefiniuj funkcję przyjmującą dwa parametry - wartość całkowitą i funkcję operującą na wartości całkowitej.
   * Zastosuj przekazaną jako parametr funkcję trzykrotnie do wartości całkowitej i zwróć wynik.
   */

  def zad4(int: Int, func: Int => Int) = func(func(func(int)))

  println(zad4(2, x => x * x))
  println(zad4(4, x => x + x))
  println(zad4(5, x => 10 * x + x))

  println("Zadanie 5.")
  /**
   * Zdefiniuj klasę Osoba i trzy traity: Student, Nauczyciel, Pracownik. Osoba powinna mieć własności read only: imie,
   * nazwisko, podatek. Pracownik powinien mieć własność pensja (z getterem i seterem). Student i Pracownik powinni
   * przesłaniać własność podatek – dla Studenta zwracamy 0, dla Pracownika 20% pensji. Nauczyciel powinien dziedziczyć
   * z Pracownika, dla niego podatek zwraca 10% pensji. Stwórz obiekty z każdym z traitów, pokaż jak podatek działa dla
   * każdego z nich. Stwórz obiekty z traitami Student i Pracownik, pokaż jak podatek zadziała w zależności od
   * kolejności w jakiej te traity zostały dodane przy tworzeniu obiektu.
   */


  abstract class Person(val imie: String, val nazwisko: String) {
    def podatek: BigDecimal
  }

  trait Student extends Person {
    override def podatek: BigDecimal = 0.0
  }

  trait Pracownik extends Person {
    override def podatek: BigDecimal = 0.2 * pensja

    private var _pensja: Int = 0

    def pensja: Int = _pensja

    def pensja_=(newPensja: Int): Unit = {
      if (newPensja < 0)
        println("Warning: Nowa pensja ma zla wartosc.")
      else
        _pensja = newPensja
    }
  }

  trait Nauczyciel extends Pracownik {
    override def podatek: BigDecimal = 0.1 * pensja
  }

  val a = new Person("Anna", "Jakubiak") with Student
  println(a.podatek)

  val b = new Person("Beniamin", "Kalinowski") with Pracownik
  b.pensja = 2000
  println(b.podatek)

  val c = new Person("Cezary", "Liberda") with Nauczyciel
  c.pensja = 3000
  println(c.podatek)

  val d = new Person("Daniel", "Michalak") with Pracownik
  d.pensja = 4000
  println(d.podatek)

  d.pensja = -100
  println(d.podatek)
}
