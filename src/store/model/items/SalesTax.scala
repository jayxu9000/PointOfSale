package store.model.items

class SalesTax (salesTax_percentage: Double) extends Modifier {

  override def updatePrice(price: Double): Double = {
    price
  }

  override def computeTax(price: Double): Double = {
    salesTax_percentage * 0.01 * price
  }
}
