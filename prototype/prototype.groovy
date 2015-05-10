abstract class Prototype implements Cloneable {
	def abstract Prototype clone()
}

class ConcretePrototype1 extends Prototype {
	def Prototype clone() {
		return super.clone()
	}
}

class ConcretePrototype2 extends Prototype {
	def Prototype clone() {
		return super.clone()
	}
}

println("${new ConcretePrototype2().clone().getClass()}")