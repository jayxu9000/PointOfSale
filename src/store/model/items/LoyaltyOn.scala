package store.model.items

class LoyaltyOn extends LoyaltyState {

  def updatePrice(price: Double): Double = {
    price * (1 * 0.01)
  }

  def computeTax(price: Double): Double = {
    0
  }

}
