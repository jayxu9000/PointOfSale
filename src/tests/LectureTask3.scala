package tests

import org.scalatest.FunSuite
import store.model.items.{BottleDeposit, Modifier, Sale, SalesTax}

class LectureTask3 extends FunSuite{

  val epsilon = 0.01

  def compareDoubles (input1: Double, input2: Double): Boolean = {
    math.abs(input1 - input2) < epsilon
  }


  test ("modifiers") {

    val sale1: Modifier = new Sale(50)
    val salesTax1: Modifier = new SalesTax(10)
    val bottleDeposit1: Modifier = new BottleDeposit(2)

    assert(compareDoubles(sale1.updatePrice(100),50))
    assert(compareDoubles(sale1.computeTax(100), 0))

    assert(compareDoubles(salesTax1.updatePrice(100), 100))
    assert(compareDoubles(salesTax1.computeTax(100), 10))

    assert(compareDoubles(bottleDeposit1.updatePrice(100), 100))
    assert(compareDoubles(bottleDeposit1.computeTax(100), 2))
  }





}
