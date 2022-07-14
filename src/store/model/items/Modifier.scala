package store.model.items

abstract class Modifier {

  def updatePrice(price: Double): Double

  def computeTax(price: Double): Double

}
