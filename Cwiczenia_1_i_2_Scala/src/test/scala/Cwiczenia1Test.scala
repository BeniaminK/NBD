import org.scalatest.FunSuite

import java.io.ByteArrayOutputStream
import java.util

class Cwiczenia1Test extends FunSuite {

  test("Cwiczenia1.zad1") {

    val weekDays = List("Poniedzialek", "Wtorek", "Środa", "Czwartek", "Piatek", "Sobota", "Niedziela")

    assert(Cwiczenia1.zad1a(weekDays) == "Poniedzialek,Wtorek,Środa,Czwartek,Piatek,Sobota,Niedziela")
    assert(Cwiczenia1.zad1b(weekDays) == "Poniedzialek,Piatek")
    assert(Cwiczenia1.zad1c(weekDays) == "Poniedzialek,Wtorek,Środa,Czwartek,Piatek,Sobota,Niedziela")
  }

  test("Cwiczenia1.zad2") {

    val weekDays = List("Poniedzialek", "Wtorek", "Środa", "Czwartek", "Piatek", "Sobota", "Niedziela")

    assert(Cwiczenia1.zad2a(weekDays) == "Poniedzialek,Wtorek,Środa,Czwartek,Piatek,Sobota,Niedziela")
    assert(Cwiczenia1.zad2b(weekDays) == "Niedziela,Sobota,Piatek,Czwartek,Środa,Wtorek,Poniedzialek")
  }

  test("Cwiczenia1.zad3") {

    val weekDays = List("Pn", "Wt", "Śr", "Czw", "Pt", "Sb", "Nd")

    assert(Cwiczenia1.zad3(weekDays) == "Pn,Wt,Śr,Czw,Pt,Sb,Nd")
  }

  test("Cwiczenia1.zad4") {

    val weekDays = List("Poniedzialek", "Wtorek", "Środa", "Czwartek", "Piatek", "Sobota", "Niedziela")

    assert(Cwiczenia1.zad4a(weekDays) == "Poniedzialek,Wtorek,Środa,Czwartek,Piatek,Sobota,Niedziela")
    assert(Cwiczenia1.zad4b(weekDays) == "Poniedzialek,Wtorek,Środa,Czwartek,Piatek,Sobota,Niedziela")
    assert(Cwiczenia1.zad4c(weekDays) == "Poniedzialek,Piatek")
  }

  test("Cwiczenia1.zad5") {

    val productMap = Map[String, Double](
      "a" -> 10,
      "b" -> 5,
      "c" -> 8,
      "d" -> 5.60,
      "e" -> 8)
    val discountMap = productMap map { case (key, value) => (key, value * 0.9) }

    assert(discountMap.apply("a") == 9.0)
    assert(discountMap.apply("b") == 4.5)
    assert(discountMap.size == productMap.size)
  }

  test("Cwiczenia1.zad6") {

    val baos = new ByteArrayOutputStream()
    Console.withOut(baos)(Cwiczenia1.zad6(("a", 12, 3.14)))
    assert(baos.toString.stripSuffix("\n") == "a 12 3.14")
  }

  test("Cwiczenia1.zad7") {

    val map = Map[String, Double](
      "a" -> 10,
      "b" -> 5,
      "c" -> 8,
      "d" -> 5.60,
      "e" -> 8)
    def mapping(x: Double) = s"Value in cents: ${100 * x}"

    assert(map.get("aaa").map(mapping).isEmpty)
    assert(map.get("a").map(mapping).isDefined)
  }

  test("Cwiczenia1.zad8") {

    val inputList = List(0, 1, 2, 3, 0, 1, 2)
    val outputList = Cwiczenia1.filterRecByFunc(inputList, x => x != 0)

    assert (outputList == List(1, 2, 3, 1, 2))
  }

  test("Cwiczenia1.zad9") {

    val inputList = List(0, 1, 2, 3, 0, 1, 2)
    val outputList = Cwiczenia1.zad9(inputList)

    assert (outputList == List(1, 2, 3, 4, 1, 2, 3))
  }

  test("Cwiczenia1.zad10") {

    val inputList = List(-12.3, -8.7, 6.5, -15, 12, -5, 5, -12, 19, -3, 3)
    val outputList = Cwiczenia1.zad10(inputList)

    assert(outputList == List(6.5, 12, 5, 5, 3, 3))
  }
}
