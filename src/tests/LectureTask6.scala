package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{Item, Modifier, Sale, SalesTax}

class LectureTask6 extends FunSuite{
  //compareDoubles
  val epsilon = 0.01
  def compareDoubles (input1: Double, input2: Double): Boolean = {
    math.abs(input1 - input2) < epsilon
  }

  val Machine1 = new SelfCheckout

  test("test1") {

    assert(Machine1.displayString().contains("welcome"))

    val Item1: Item = new Item("apple", 1.00)
    val Item2: Item = new Item("banana", 2.00)
    val Item3: Item = new Item("steak", 25.00)

    val sale1: Modifier = new Sale(50)
    val sale2: Modifier = new Sale(10)

    val saleTax1: Modifier = new SalesTax(10)

    Item1.addModifier(sale1)
    Item2.addModifier(sale2)
    Item1.addModifier(saleTax1)
    Item2.addModifier(saleTax1)
    Item3.addModifier(saleTax1)

    Machine1.addItem("001", Item1)
    Machine1.addItem("002", Item2)
    Machine1.addItem("003", Item3)

    Machine1.numberPressed(0)
    Machine1.numberPressed(0)
    Machine1.numberPressed(1)
    Machine1.enterPressed()
    Machine1.enterPressed()
    Machine1.enterPressed()

    assert(Machine1.receiptLines().head.description == "apple")
    assert(Machine1.receiptLines()(1).description == "apple")
    assert(Machine1.receiptLines()(2).description == "apple")
    assert(compareDoubles(Machine1.receiptLines().head.amount,.5))
    assert(compareDoubles(Machine1.receiptLines()(1).amount,.5))
    assert(compareDoubles(Machine1.receiptLines()(2).amount,.5))

    Machine1.clearPressed()
    Machine1.enterPressed()
    assert(Machine1.receiptLines()(3).description == "error")
    assert(compareDoubles(Machine1.receiptLines()(3).amount, 0))

    Machine1.checkoutPressed()
    assert(Machine1.displayString() == "cash or credit")
    assert(Machine1.receiptLines()(4).description == "subtotal")
    assert(compareDoubles(Machine1.receiptLines()(4).amount, 1.5))




  }

}
