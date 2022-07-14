package store.model.checkout

import store.model.items.{Item, LoyaltySale, SalesTax}

class checkoutState(machine: SelfCheckout, var itemsInMachine: Map[String, Item], var barcode: String, var receiptList: List[ReceiptLine], var taxList: List[Double]) extends State(machine){

  override def addItem(barcode: String, item: Item): Unit = {}

  override def numberPressed(number: Int): Unit = {
    barcode += number.toString
  }

  override def clearPressed(): Unit = {
    barcode = ""
  }

  override def enterPressed(): Unit = {
    val currentItem: Item = itemsInMachine.getOrElse(barcode, error_result)
    val y: ReceiptLine = new ReceiptLine(currentItem.description(), currentItem.price())
    receiptList = receiptList :+ y
    taxList = taxList :+ currentItem.tax()
    val storedBarcode = barcode
    barcode = ""
    machine.state = new itemScanState(machine, itemsInMachine, storedBarcode, receiptList, taxList)
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
    machine.state = new devCash1(machine, itemsInMachine, barcode, receiptList, taxList)
  }

  override def creditPressed(): Unit = {}

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
