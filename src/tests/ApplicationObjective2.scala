package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{BottleDeposit, Item, Modifier, Sale, SalesTax}

class ApplicationObjective2 extends FunSuite{
  //compareDoubles
  val epsilon = 0.01
  def compareDoubles (input1: Double, input2: Double): Boolean = {
    math.abs(input1 - input2) < epsilon
  }

  test("test1") {

    val Machine1 = new SelfCheckout

    val Item1: Item = new Item("apple", 1.00)
    val Item2: Item = new Item("banana", 2.00)
    val Item3: Item = new Item("steak", 25.00)

    Machine1.addItem("001", Item1)
    Machine1.addItem("002", Item2)
    Machine1.addItem("003", Item3)

    val bottleDeposit1 = new BottleDeposit(10)
    Item1.addModifier(bottleDeposit1)

    Machine1.numberPressed(0)
    Machine1.cashPressed()
    Machine1.creditPressed()
    Machine1.enterPressed()

    Machine1.numberPressed(0)
    Machine1.numberPressed(1)
    Machine1.enterPressed()

    assert(Machine1.receiptLines().head.description == "apple")
    assert(compareDoubles(Machine1.receiptLines().head.amount,0))

    Machine1.checkoutPressed()

    assert(Machine1.receiptLines()(1).description == "subtotal")
    assert(compareDoubles(Machine1.receiptLines()(1).amount, 0))
    assert(Machine1.receiptLines()(2).description == "tax")
    assert(compareDoubles(Machine1.receiptLines()(2).amount, 0))
    assert(Machine1.receiptLines()(3).description == "total")
    assert(compareDoubles(Machine1.receiptLines()(3).amount, 0))

  }

  test("test2") {

    val Machine1 = new SelfCheckout

    val Item1: Item = new Item("apple", 1.00)
    val Item2: Item = new Item("banana", 2.00)
    val Item3: Item = new Item("steak", 25.00)

    Machine1.addItem("001", Item1)
    Machine1.addItem("002", Item2)
    Machine1.addItem("003", Item3)

    Machine1.numberPressed(0)
    Machine1.cashPressed()
    Machine1.creditPressed()
    Machine1.creditPressed()

    Machine1.numberPressed(0)
    Machine1.numberPressed(1)
    Machine1.enterPressed()

    assert(Machine1.receiptLines().head.description == "apple")
    assert(compareDoubles(Machine1.receiptLines().head.amount,1))

    Machine1.checkoutPressed()

    assert(Machine1.receiptLines()(1).description == "subtotal")
    assert(compareDoubles(Machine1.receiptLines()(1).amount, 1))

  }

}
