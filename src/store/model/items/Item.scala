package store.model.items

class Item (item_description: String, item_price: Double) {

  var times_scanned: Int = 0
  var modifyList: List[Modifier] = List()
  var mapOfModifiers: Map[Any, Modifier] = Map()

  def price(): Double = {
    var price: Double = item_price
    for (sales <- modifyList) {
      price = sales.updatePrice(price)
    }
    price
  }

  def description(): String = {
    item_description
  }

  def scanned(): Unit = {
    times_scanned += 1
  }

  def timesScanned(): Int = {
    times_scanned
  }

  def addModifier(modify: Modifier): Unit = {
    modifyList = modifyList :+ modify
  }

  def tax(): Double = {
    var tax: Double = 0
    for (eachTax <- modifyList) {
      tax += eachTax.computeTax(price())
    }
    tax
  }

}
