package store.model.checkout

import store.model.items.{Item, LoyaltyOff, LoyaltyOn, LoyaltySale, LoyaltyState}

class welcomeState(machine: SelfCheckout) extends State(machine){

  var itemsInMachine: Map[String, Item] = Map()
  var receiptList: List[ReceiptLine] = List()
  var welcome: String = "welcome"

  override def addItem(barcode: String, item: Item): Unit = {
    itemsInMachine = itemsInMachine + (barcode -> item)
  }

  override def numberPressed(number: Int): Unit = {
    machine.state = new checkoutState(machine, itemsInMachine, number.toString, receiptList, List())
  }

  override def clearPressed(): Unit = {
    welcome = ""
    val error_line: ReceiptLine = new ReceiptLine("error", 0.0)
    receiptList = receiptList :+ error_line
  }

  override def enterPressed(): Unit = {
    welcome = ""
    val error_line: ReceiptLine = new ReceiptLine("error", 0.0)
    receiptList = receiptList :+ error_line
  }

  override def checkoutPressed(): Unit = {}

  override def cashPressed(): Unit = {}

  override def creditPressed(): Unit = {}

  override def loyaltyCardPressed(): Unit = {
  }

  override def displayString(): String = {
    welcome
  }

  override def receiptLines(): List[ReceiptLine] = {
    receiptList
  }

  override def prepareStore(): Unit = {}

}
