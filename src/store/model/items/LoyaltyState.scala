package store.model.items

abstract class LoyaltyState {

  def updatePrice(price: Double): Double

  def computeTax(price: Double): Double

}
