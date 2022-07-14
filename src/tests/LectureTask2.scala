package tests

import org.scalatest.FunSuite
import store.model.items.{Sale, SaleTestingItem}

class LectureTask2 extends FunSuite{

  val epsilon = 0.01

  def compareDoubles (input1: Double, input2: Double): Boolean = {
    math.abs(input1 - input2) < epsilon
  }

  test("50% to 75% Sale") {

    val sale1: Sale = new Sale(50.0)

    assert(compareDoubles(sale1.updatePrice(100.0),50.0))

    val item1: SaleTestingItem = new SaleTestingItem("banana", 100)

    item1.addSale(sale1)
    assert(compareDoubles(item1.price(),50.0))

    sale1.percentOff = 75.0
    assert(compareDoubles(item1.price(), 25.0))
  }

  test("20% * 10%") {
    val sale1 = new Sale(20)
    val sale2 = new Sale(10)
    val item1: SaleTestingItem = new SaleTestingItem("steak", 25)

    item1.addSale(sale1)
    item1.addSale(sale2)

    println (item1.price)

    assert(compareDoubles(item1.price(), 18))

  }

}
