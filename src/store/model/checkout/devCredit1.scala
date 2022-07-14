package store.model.checkout

import store.model.items.{Item, SalesTax}

class devCredit1(machine: SelfCheckout, var itemsInMachine: Map[String, Item], var barcode: String, var receiptList: List[ReceiptLine], var taxList: List[Double]) extends State(machine){

  override def addItem(barcode: String, item: Item): Unit = {}

  override def numberPressed(number: Int): Unit = {
    barcode += number.toString
  }

  override def clearPressed(): Unit = {
    barcode = ""
  }

  override def enterPressed(): Unit = {
    var devReceipt: List[ReceiptLine] = List()
    for (eachLine <- receiptList) {
      val new_amount = eachLine.amount * 0
      devReceipt = devReceipt :+ new ReceiptLine(eachLine.description, new_amount)
    }
    var devTax: List[Double] = List()
    for (eachTax <- taxList) {
      val new_tax = eachTax * 0
      devTax = devTax :+ new_tax
    }
    machine.state = new devEnter1(machine, itemsInMachine, barcode, devReceipt, devTax)
  }

  override def checkoutPressed(): Unit = {
    barcode = "cash or credit"
    var subtotal: Double = 0
    var tax: Double = 0
    for (eachLine <- receiptList) {
      subtotal += eachLine.amount
    }
    for (eachTax <- taxList) {
      tax += eachTax
    }
    val subtotalLine: ReceiptLine = new ReceiptLine("subtotal", subtotal)
    val taxLine: ReceiptLine = new ReceiptLine("tax", tax)
    val total = subtotal + tax
    val totalLine: ReceiptLine = new ReceiptLine("total", total)
    receiptList = receiptList :+ subtotalLine
    receiptList = receiptList :+ taxLine
    receiptList = receiptList :+ totalLine

    machine.state = new selectionState(machine, itemsInMachine, barcode, receiptList)
  }

  override def cashPressed(): Unit = {
    machine.state = new checkoutState(machine, itemsInMachine, barcode, receiptList, taxList)
  }

  override def creditPressed(): Unit = {
    machine.state = new checkoutState(machine, itemsInMachine, barcode, receiptList, taxList)
  }

  override def loyaltyCardPressed(): Unit = {
    machine.state = new checkoutState(machine, itemsInMachine, barcode, receiptList, taxList)
  }

  override def displayString(): String = {
    barcode
  }

  override def receiptLines(): List[ReceiptLine] = {
    receiptList
  }

  override def prepareStore(): Unit = {}

}
