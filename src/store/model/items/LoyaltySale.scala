package store.model.items

class LoyaltySale (val loyalty_percentage: Double) extends Modifier {

  var loyaltyState: LoyaltyState = new LoyaltyOff

  override def updatePrice(price: Double): Double = {
    this.loyaltyState.updatePrice(price)
  }

  override def computeTax(price: Double): Double = {
    this.loyaltyState.computeTax(price)
  }

}
