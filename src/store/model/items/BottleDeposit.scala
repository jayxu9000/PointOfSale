package store.model.items

class BottleDeposit (deposit_charged: Double) extends Modifier {

  override def updatePrice(price: Double): Double = {
    price
  }

  override def computeTax(price: Double): Double = {
    deposit_charged
  }

}
