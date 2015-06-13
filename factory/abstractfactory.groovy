//NOT TESTED
abstract class Pizza {
	def dough
	def cheese
	def topping
	def adding
	abstract def prepare()

	def bake(){
		println("burnt like in hell")
	}

	def cut(){
		println("cut into pieces")
	}

}

class CheesePizza extends Pizza {
	def ingridientFactory
	CheesePizza(IngridientFactory ingridientFactory){
		this.ingridientFactory = ingridientFactory
	}
	
	def prepare {
		dough = ingridientFactory.getDough()
		cheese = ingridientFactory.getCheese()
		topping = ingridientFactory.getTopping()
	}   
}

class ShimpPizza extends Pizza {
	def ingridientFactory
	CheesePizza(IngridientFactory ingridientFactory){
		this.ingridientFactory = ingridientFactory
	}
	
	def prepare {
		dough = ingridientFactory.getDough()
		cheese = ingridientFactory.getCheese()
		topping = ingridientFactory.getTopping()
		adding = ingridientFactory.getAdding()
	}   
} 


interface IngridientFactory {
	def getDough()
	def getCheese()
	def getTopping()
	def getAdding()
}

class NyPizzaIngridientFactory implements IngridientFactory{
	def getDough(){
		return "NY flavored dough"
	}

	def getCheese(){
		return "NY styled cheese"
	}

	def getTopping(){
		return "NY flavored topping" 
	}

	def getAdding(){
		return "Something"
	}
}

abstract class PizzaStore(){
	abstract createPizza()
}

class NYPizzaStore extends PizzaStore{
	def ingridientFactory

	NYPizzaStore(IngridientFactory ingridientFactory){
		this.ingridientFactory = ingridientFactory
	}

	
}