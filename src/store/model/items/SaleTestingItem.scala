package store.model.items


class SaleTestingItem (item_description: String, item_price: Double){

  var list: List[Sale] = List()

  def addSale (sale: Sale): Unit = {
    list = list :+ sale
  }

  def price(): Double = {
    var price: Double = item_price
    for (eachSale <- list) {
      price = eachSale.updatePrice(price)
    }
    price
  }
}
