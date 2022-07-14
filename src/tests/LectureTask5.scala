package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{Item, Modifier, Sale}

class LectureTask5 extends FunSuite{

  val epsilon = 0.01
  def compareDoubles (input1: Double, input2: Double): Boolean = {
    math.abs(input1 - input2) < epsilon
  }

  var Machine1: SelfCheckout = new SelfCheckout

  val Item1: Item = new Item("apple", 1.00)
  val Item2: Item = new Item("banana", 2.00)
  val Item3: Item = new Item("steak", 25.00)

  val sale1: Modifier = new Sale(50)
  val sale2: Modifier = new Sale(10)

  Item1.addModifier(sale1)
  Item2.addModifier(sale2)

  Machine1.addItem("001", Item1)
  Machine1.addItem("002", Item2)
  Machine1.addItem("003", Item3)

  test("test1") {

    //Item 1 + 2 and enter button
    Machine1.numberPressed(0)
    Machine1.numberPressed(0)
    Machine1.numberPressed(1)
    assert(Machine1.displayString() == "001")

    Machine1.enterPressed()
    assert(Machine1.displayString() == "")

    Machine1.numberPressed(0)
    Machine1.numberPressed(0)
    Machine1.numberPressed(2)
    assert(Machine1.displayString() == "002")

    Machine1.enterPressed()
    assert(Machine1.displayString() == "")


    //wrong barcode breaks code
    Machine1.numberPressed(4)
    Machine1.enterPressed()

    //clear button
    Machine1.numberPressed(7)
    assert(Machine1.displayString() == "7")

    Machine1.clearPressed()
    assert(Machine1.displayString() == "")


    //check receipt description and amount
    assert(Machine1.receiptLines().head.description == "apple")
    assert(compareDoubles(Machine1.receiptLines().head.amount,.50))
    assert(Machine1.receiptLines()(1).description == "banana")
    assert(compareDoubles(Machine1.receiptLines()(1).amount,1.8))
    assert(Machine1.receiptLines()(2).description == "error")
    assert(compareDoubles(Machine1.receiptLines()(2).amount,0.0))

  }



}
