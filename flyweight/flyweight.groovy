import groovy.transform.Canonical
import java.util.concurrent.CopyOnWriteArrayList
@Canonical
class CoffeeFlavour {
	def String name	
}

class Menu {
	def flavours = [:]

	def CoffeeFlavour lookup (String flavourName) {
		if (!flavours.containsKey(flavourName)){
			flavours.put(flavourName, new CoffeeFlavour(name:flavourName))
		}
		return flavours[flavourName]
	}

	def int totalCoffeeFlavoursMade(){
		return flavours.size()
	}
}
@Canonical
class Order {
	def int tableNumber
	def CoffeeFlavour flavour 
	def void serve() {
		println("Serve ${flavour} to table ${tableNumber}")
	}
}

class CoffeeShop {
	def orders = new CopyOnWriteArrayList<Order>()
	def menu = new Menu()

	def void takeOrder (String flavourName, int table) {
		def flavour = menu.lookup(flavourName)
		def order = new Order(table, flavour)
		orders.add(order)
	}

	def void service(){
		orders.each{ _-> _.serve()
						 orders.remove(_)}
	}

	def String report() {
		return "total number of coffee made: ${menu.totalCoffeeFlavoursMade()}"
	}
}

def shop = new CoffeeShop()

shop.takeOrder("Cappucino", 2)
shop.takeOrder("Frappe", 1)
shop.takeOrder("Espresso", 3)
shop.takeOrder("Frappe", 98)
shop.takeOrder("Espresso", 43)
shop.takeOrder("Simply coffee", 42)
shop.takeOrder("Americano", 34)
shop.takeOrder("Java", 32)

shop.service()

println(shop.report())