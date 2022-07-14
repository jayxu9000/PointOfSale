package store.model.checkout

import store.model.items.Item

class selectionState(machine: SelfCheckout, var itemsInMachine: Map[String, Item], var barcode: String, var receiptList: List[ReceiptLine]) extends State(machine){

  override def addItem(barcode: String, item: Item): Unit = {}

  override def numberPressed(number: Int): Unit = {}

  override def clearPressed(): Unit = {}

  override def enterPressed(): Unit = {}

  override def checkoutPressed(): Unit = {}

  override def cashPressed(): Unit = {
    barcode = "thank you"
    receiptList = List()
    machine.state = new endState(machine, itemsInMachine, barcode, receiptList)
  }
  override def creditPressed(): Unit = {
    barcode = "thank you"
    receiptList = List()
    machine.state = new endState(machine, itemsInMachine, barcode, receiptList)
  }

  override def loyaltyCardPressed(): Unit = {

  }

  override def displayString(): String = {
    barcode
  }

  override def receiptLines(): List[ReceiptLine] = {
    receiptList
  }

  override def prepareStore(): Unit = {}

}
