package store.model.items

class LoyaltyOff extends LoyaltyState {

  def updatePrice(price: Double): Double = {
    price
  }

  def computeTax(price: Double): Double = {
    0
  }

}
