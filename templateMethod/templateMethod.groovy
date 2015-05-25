abstract class Beverage {

	def prepareTheBeverage() {
		boiling()
		addCondiments()
		addMilk()
	}
	
	def abstract addCondiments()
	
	def addMilk() {
		println('Adding milk')
	}
	
	def boiling() {
		println('Boiling the water')
	}	
}

class Coffee extends Beverage {
	def addCondiments(){
		println('Adding coffee')
	}
}

class Tea extends Beverage {
	def addCondiments(){
		println('Adding tea')
	}
}

new Coffee().prepareTheBeverage()
new Tea().prepareTheBeverage()