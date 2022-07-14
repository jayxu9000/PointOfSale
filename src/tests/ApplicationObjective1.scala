package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{Item, LoyaltySale, Modifier, Sale, SalesTax}

class ApplicationObjective1 extends FunSuite{
  //compareDoubles
  val epsilon = 0.01
  def compareDoubles (input1: Double, input2: Double): Boolean = {
    math.abs(input1 - input2) < epsilon
  }

  test("scan_loyalty_card_after_checkout") {
    val Machine1 = new SelfCheckout

    val Item1: Item = new Item("apple", 1.00)
    val Item2: Item = new Item("banana", 2.00)
    val Item3: Item = new Item("steak", 25.00)

    Machine1.addItem("001", Item1)
    Machine1.addItem("002", Item2)
    Machine1.addItem("003", Item3)

    val loyalty1: LoyaltySale = new LoyaltySale(25)
    Item1.addModifier(loyalty1)

    Machine1.numberPressed(0)
    Machine1.numberPressed(0)
    Machine1.numberPressed(1)
    Machine1.enterPressed()
    Machine1.checkoutPressed()

    Machine1.loyaltyCardPressed()

    assert(Machine1.receiptLines().head.description == "apple")
    assert(compareDoubles(Machine1.receiptLines().head.amount, .75))
  }

  test("multiple_loyalty_scans") {
    val Machine1 = new SelfCheckout

    val Item1: Item = new Item("apple", 1.00)
    val Item2: Item = new Item("banana", 2.00)
    val Item3: Item = new Item("steak", 25.00)

    Machine1.addItem("001", Item1)
    Machine1.addItem("002", Item2)
    Machine1.addItem("003", Item3)

    val loyalty1: LoyaltySale = new LoyaltySale(25)
    Item1.addModifier(loyalty1)

    Machine1.numberPressed(0)
    Machine1.numberPressed(0)
    Machine1.numberPressed(1)
    Machine1.enterPressed()

    Machine1.loyaltyCardPressed()
    Machine1.loyaltyCardPressed()
    Machine1.loyaltyCardPressed()

    assert(Machine1.receiptLines().head.description == "apple")
    assert(compareDoubles(Machine1.receiptLines().head.amount, .75))
  }

  test("reset_for_next_customer") {
    val Machine1 = new SelfCheckout

    val Item1: Item = new Item("apple", 1.00)
    val Item2: Item = new Item("banana", 2.00)
    val Item3: Item = new Item("steak", 25.00)

    Machine1.addItem("001", Item1)
    Machine1.addItem("002", Item2)
    Machine1.addItem("003", Item3)

    val loyalty1: LoyaltySale = new LoyaltySale(25)
    Item1.addModifier(loyalty1)

    Machine1.numberPressed(0)
    Machine1.numberPressed(0)
    Machine1.numberPressed(1)
    Machine1.enterPressed()

    Machine1.loyaltyCardPressed()

    assert(Machine1.receiptLines().head.description == "apple")
    assert(compareDoubles(Machine1.receiptLines().head.amount, .75))

    Machine1.checkoutPressed()
    Machine1.cashPressed()
    Machine1.enterPressed()

    Machine1.numberPressed(0)
    Machine1.numberPressed(0)
    Machine1.numberPressed(1)
    Machine1.enterPressed()

    assert(Machine1.receiptLines().head.description == "apple")
    assert(compareDoubles(Machine1.receiptLines().head.amount, 1))


  }

}
