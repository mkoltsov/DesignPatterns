interface FlyBehavior {
	def fly();
}

interface QuackBehaviour {
	def quack();
}

class MegaDuck implements FlyBehavior {
	def fly(){
		println("Flying saucepan")       	
	}
}

class GigaDuck implements QuackBehaviour {
	def quack(){
		println("Quack")       	
	}
}

class Duck
{

	private FlyBehavior flyBehavior
	private QuackBehaviour quackBehavior

	def Duck setFlyBehavior(FlyBehavior flyBehavior){
		this.flyBehavior = flyBehavior
		return this
	}

	def Duck setQuackBehavior(QuackBehaviour quackBehavior){
		this.quackBehavior = quackBehavior
		return this
	}

	def performQuack(){
		quackBehavior.quack()
	}

	def performFly(){
		swimBehavior.fly()
	}


	def swim(){
		println("All ducks can swim")
	}
}

new Duck().with{
	setQuackBehavior(new GigaDuck())
	setFlyBehavior(new MegaDuck())
	performQuack()
	swim()
}
