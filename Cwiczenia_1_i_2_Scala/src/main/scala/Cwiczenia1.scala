import scala.annotation.tailrec
import scala.util.Random

object Cwiczenia1 extends App {

  println("Zadanie 1.")
  /**
   * Stwórz 7 elementową listę zawierającą nazwy dni tygodnia.
   * Napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
   * a. Pętli for
   * b. Pętli for wypisując tylko dni z nazwami zaczynającymi się na „P”
   * c. Pętli while
   */

  def zad1a(weekDays: List[String]) = {
    var res = ""
    for (weekDay <- weekDays) {
      res += "," + weekDay
    }
    res.tail
  }

  def zad1b(weekDays: List[String]) = {
    var res = ""
    for (weekDay <- weekDays) {
      if (weekDay.head.equals('P')) {
        res += "," + weekDay
      }
    }
    res.tail
  }

  def zad1c(weekDays: List[String]) = {
    var idx = 0
    var res = ""
    while (idx < weekDays.length) {
      res += "," + weekDays(idx)
      idx += 1
    }
    res.tail
  }

  val weekDays = List("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela")
  // a
  println(zad1a(weekDays))
  // b
  println(zad1b(weekDays))
  // c
  println(zad1c(weekDays))

  println("Zadanie 2.")
  /**
   *     2. Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z
   *     a. Funkcji rekurencyjnej
   *     b. Funkcji rekurencyjnej wypisując elementy listy od końca
   */

  def zad2a(weekDays: List[String]): String = {
    if (weekDays.length == 1) {
      return weekDays.head
    }
    weekDays.head + "," + zad2a(weekDays.tail)
  }

  def zad2b(weekDays: List[String]): String = {
    if (weekDays.length == 1) {
      return weekDays.head
    }
    zad2b(weekDays.tail) + "," + weekDays.head
  }

  // a
  println(zad2a(weekDays))
  // b
  println(zad2b(weekDays))

  println("Zadanie 3.")
  /**
   * Stwórz funkcję korzystającą z rekurencji ogonowej do zwrócenia oddzielonego przecinkami stringa zawierającego elementy listy z ćwiczenia 1
   */

  @tailrec
  def zad3(list: List[String], acumulator: String=""): String = list match {
    case Nil => acumulator.tail     // .tail because the first char is ',' and we want result without it
    case head :: tail => zad3(tail, acumulator + "," + head)
  }

  println(zad3(weekDays))

  println("Zadanie 4.")
  /**
   * Dla listy z ćwiczenia 1 napisz funkcję tworzącą w oparciu o nią stringa z elementami oddzielonymi przecinkami korzystając z:
   * a. Metody foldl
   * b. Metody foldr
   * c. Metody foldl wypisując tylko dni z nazwami zaczynającymi się na „P”
   */

  def zad4a(weekDays: List[String]) = {
    weekDays.foldLeft("")(_ + "," + _).tail
  }

  def zad4b(weekDays: List[String]) = {
    val res = weekDays.foldRight("")(_ + "," + _)
    res.substring(0, res.length - 1)
  }

  def zad4c(weekDays: List[String]) = {
    weekDays.filter(p => p.startsWith("P")).foldLeft("")(_ + "," + _).tail
  }

  // a
  println(zad4a(weekDays))
  // b
  println(zad4b(weekDays))
  // c
  println(zad4c(weekDays))

  println("Zadanie 5.")
  /**
   * Stwórz mapę z nazwami produktów i cenami. Na jej podstawie wygeneruj drugą, z 10% obniżką cen.
   * Wykorzystaj mechanizm mapowania kolekcji.
   */
  val productMap = Map[String, Double](
    "apple" -> 5.40,
    "pineapple" -> 7.50,
    "strawberry" -> 8,
    "chocolate" -> 5.60,
    "raspberry" -> 8)
  val discountMap = productMap map { case (key, value) => (key, value * 0.9) }
  println(discountMap)

  println("Zadanie 6.")
  /**
   * Zdefiniuj funkcję przyjmującą krotkę z 3 wartościami różnych typów i wypisującą je
   */

  def zad6(a: (Any, Any, Any)): Unit = {
    println(s"${a._1} ${a._2} ${a._3}")
  }

  zad6(("ABCD", 12, 8.7))

  println("Zadanie 7.")
  /**
   * Zaprezentuj działanie Option na dowolnym przykładzie (np. mapy, w której wyszukujemy wartości po kluczu)
   */

  def mapping(x: Double) = s"Value in cents: ${100 * x}"

  println("Non existing value mapping:")
  println(productMap.get("aaa").map(mapping))
  println("Existing value mapping:")
  println(productMap.get("apple").map(mapping))

  println("Zadanie 8.")
  /**
   * Napisz funkcję usuwającą zera z listy wartości całkowitych
   * (tzn. zwracającą listę elementów mających wartości różne od 0).
   * Wykorzystaj rekurencję.
   */

  Random.setSeed(1)
  var listLen = 40
  val integerList = List.fill(listLen)(Random.between(-1, 1 + 1))
  println("Input List: " + integerList + " length: " + integerList.length)

  @tailrec
  def filterRecByFunc(inList: List[Int], filterFunc: Int => Boolean, outList: List[Int] = List[Int]()): List[Int] = {
    inList match {
      case Nil                           => outList
      case ::(hd, tl) if filterFunc(hd)  => filterRecByFunc(tl, filterFunc, outList :+ hd)
      case ::(_, tl)                     => filterRecByFunc(tl, filterFunc, outList)
    }
  }

  val integerListWithoutZeros = filterRecByFunc(integerList, x => x != 0)
  println("Filtered List: " + integerListWithoutZeros + " length: " + integerListWithoutZeros.length)

  println("Zadanie 9.")
  /**
   * Zdefiniuj funkcję, przyjmującą listę liczb całkowitych i zwracającą wygenerowaną na jej podstawie listę,
   * w której wszystkie liczby zostały zwiększone o 1. Wykorzystaj mechanizm mapowania kolekcji.
   */

  def zad9(intList: List[Int]): List[Int] = {
    intList.map(x => x + 1)
  }

  val intListLen = 20
  val intList = List.fill(intListLen)(Random.between(0, 10))

  val incIntList = zad9(intList)

  println(s"Input: $intList")
  println(s"Incresed: $incIntList")

  println("Zadanie 10.")
  /**
   * Stwórz funkcję przyjmującą listę liczb rzeczywistych i zwracającą stworzoną na jej podstawie listę zawierającą
   * wartości bezwzględne elementów z oryginalnej listy należących do przedziału <-5,12>. Wykorzystaj filtrowanie.
   */

  def zad10(list: List[Double]) = {
    list.filter(r => -5 <= r && r <= 12).map(Math.abs)
  }

  val zad10ListLen = 100
  val zad10List = List.fill(zad10ListLen)(Random.nextDouble() * 200 - 100)
  println("Input List: " + zad10List + " length: " + zad10List.length)
  val zad10OutList = zad10(zad10List)
  println("Input List: " + zad10OutList + " length: " + zad10OutList.length)

}
