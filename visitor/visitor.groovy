import groovy.transform.Immutable
interface CarElementVisitor {
	def void visit(Wheel wheel)
	def void visit(Engine engine)
	def void visit(Body body)
	def void visit(Car car)
}

interface CarElement {
	def void accept (CarElementVisitor visitor)
}

@Immutable
class Wheel implements CarElement {
	def String name

	def void accept(CarElementVisitor visitor) {
		visitor.visit(this)
	}
}

class Engine implements CarElement {
	def void accept(CarElementVisitor visitor) {
		visitor.visit(this)
	}
}

class Body implements CarElement {
	def void accept(CarElementVisitor visitor) {
		visitor.visit(this)
	}
}

class Car implements CarElement {
	def elements

	Car(){
		this.elements = [new Wheel(name: "front left"),
		 new Wheel(name:"front right"),
		 new Wheel(name:"back right"),
		 new Wheel(name:"back left"),
		 new Body(), new Engine()]
	}

	def void accept(CarElementVisitor visitor) {
		elements.each { _ -> 
			_.accept(visitor)
		}
		visitor.visit(this)
	}
}

class CarElementPrintVisitor implements CarElementVisitor {
	def void visit(Wheel wheel) {
		println("Visiting ${wheel.name} wheel")
	}

		def void visit(Engine engine) {
		println("Visiting engine")
	}

		def void visit(Body body) {
		println("Visiting body")
	}

		def void visit(Car car) {
		println("Visiting car")
	}
}

class CarElementDoVisitor implements CarElementVisitor {
	def void visit(Wheel wheel) {
		println("Kicking ${wheel.name} wheel")
	}

		def void visit(Engine engine) {
		println("Starting my engine")
	}

		def void visit(Body body) {
		println("Moving my body")
	}

		def void visit(Car car) {
		println("Starting my car")
	}
}

def car = new Car()

car.accept(new CarElementPrintVisitor())
car.accept(new CarElementDoVisitor())