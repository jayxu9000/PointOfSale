package store.model.checkout

import store.model.items.Item

class endState (machine: SelfCheckout, var itemsInMachine: Map[String, Item], var barcode: String, var receiptList: List[ReceiptLine]) extends State(machine){

  var newBarcode: String = ""

  override def addItem(barcode: String, item: Item): Unit = {}

  override def numberPressed(number: Int): Unit = {
    newBarcode += number.toString
    machine.state = new checkoutState(machine, itemsInMachine, newBarcode, receiptList, List())
  }

  override def clearPressed(): Unit = {
    machine.state = new checkoutState(machine, itemsInMachine, newBarcode, receiptList, List())
  }

  override def enterPressed(): Unit = {
    machine.state = new checkoutState(machine, itemsInMachine, newBarcode, receiptList, List())
  }

  override def checkoutPressed(): Unit = {}

  override def cashPressed(): Unit = {}

  override def creditPressed(): Unit = {}

  override def loyaltyCardPressed(): Unit = {}

  override def displayString(): String = {
    barcode
  }

  override def receiptLines(): List[ReceiptLine] = {
    receiptList
  }

  override def prepareStore(): Unit = {}

}
