package store.model.checkout

import store.model.items.Item

abstract class State (machine: SelfCheckout) {

  var error_result: Item = new Item("error", 0.0)

  def addItem(barcode: String, item: Item): Unit

  def numberPressed(number: Int): Unit

  def clearPressed(): Unit

  def enterPressed(): Unit

  def checkoutPressed(): Unit

  def cashPressed(): Unit

  def creditPressed(): Unit

  def loyaltyCardPressed(): Unit

  def displayString(): String

  def receiptLines(): List[ReceiptLine]

  def prepareStore(): Unit = {}

}
