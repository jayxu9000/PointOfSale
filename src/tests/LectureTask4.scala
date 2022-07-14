package tests

import org.scalatest.FunSuite
import store.model.items.{BottleDeposit, Item, Modifier, Sale, SalesTax}

class LectureTask4 extends FunSuite{

  val epsilon = 0.01

  def compareDoubles (input1: Double, input2: Double): Boolean = {
    math.abs(input1 - input2) < epsilon
  }

  val item1: Item = new Item("peach", 100)

  test("modify") {

    val sale1: Sale = new Sale(40)
    val sale2: Sale = new Sale(10)
    val salesTax1: Modifier = new SalesTax(5)
    val salesTax2: Modifier = new SalesTax(10)
    val bottleDeposit: Modifier = new BottleDeposit(1)


    item1.addModifier(sale1)
    item1.addModifier(sale2)
    item1.addModifier(salesTax1)
    item1.addModifier(salesTax2)
    item1.addModifier(bottleDeposit)

    assert(compareDoubles(item1.price(), 54))
    assert(compareDoubles(item1.tax(), 9.1))

    sale1.percentOff = 10

    assert(compareDoubles(item1.price(), 81))
    assert(compareDoubles(item1.tax(), 13.15))

  }
}
