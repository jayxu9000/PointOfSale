package store.model.checkout

import store.model.items.Item

class SelfCheckout extends {

  var state: State = new welcomeState(this)

  def addItem(barcode: String, item: Item): Unit = {
    this.state.addItem(barcode, item)
  }

  def numberPressed(number: Int): Unit = {
    this.state.numberPressed(number)
  }

  def clearPressed(): Unit = {
    this.state.clearPressed()
  }

  def enterPressed(): Unit = {
    this.state.enterPressed()
  }

  def checkoutPressed(): Unit = {
    this.state.checkoutPressed()
  }

  def cashPressed(): Unit = {
    this.state.cashPressed()
  }

  def creditPressed(): Unit = {
    this.state.creditPressed()
  }

  def loyaltyCardPressed(): Unit = {
    this.state.loyaltyCardPressed()
  }

  def displayString(): String = {
    this.state.displayString()
  }

  def receiptLines(): List[ReceiptLine] = {
    this.state.receiptLines()
  }

  def prepareStore(): Unit = {
    this.state.prepareStore()
  }

}
