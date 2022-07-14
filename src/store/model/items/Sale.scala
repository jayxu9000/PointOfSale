package store.model.items

class Sale (var percentOff: Double) extends Modifier {

  override def updatePrice(price: Double): Double = {
    val value = 1.0 - (percentOff * 0.01)
    price * value
  }

  override def computeTax(price: Double): Double = {
    0.0
  }
}
