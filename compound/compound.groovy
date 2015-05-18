import groovy.transform.Canonical
interface Quackable {
	def void quack()
}

class MallardDuck implements Quackable {
	def void quack(){println('Quack!')}
}

class RedHeadDuck implements Quackable {
	def void quack(){println('Quack!')}
}

class RubberDuck implements Quackable {
	def void quack(){println('Squeak')}
}

class DuckCall implements Quackable {
	def void quack(){println('Kwak')}
}

@Canonical
class GooseAdapter implements Quackable {
	def Goose goose

	def void quack(){
		goose.honk()
	} 
}

class QuackCounter implements Quackable {
	def Quackable duck
	def static int quackCounter
	QuackCounter(Quackable duck){
		this.duck = duck
	}
	def void quack(){
		duck.quack()
		quackCounter++
	}
}

interface AbstractDuckFactory {
	def Quackable createMallardDuck()
	def Quackable createRedheadDuck()
	def Quackable createRubberDuck()
	def Quackable createDuckCall()
}

class DuckFactory implements AbstractDuckFactory {
	def Quackable createMallardDuck(){ return new MallardDuck()}
	def Quackable createRedheadDuck(){return new RedHeadDuck()}
	def Quackable createRubberDuck(){return new RubberDuck()}
	def Quackable createDuckCall(){return new DuckCall()}
}

class CountingDuckFactory implements AbstractDuckFactory {
	def Quackable createMallardDuck(){ return new QuackCounter(new MallardDuck())}
	def Quackable createRedheadDuck(){return new QuackCounter( new RedHeadDuck())}
	def Quackable createRubberDuck(){return new QuackCounter( new RubberDuck())}
	def Quackable createDuckCall(){return new QuackCounter( new DuckCall())}
}

class Flock implements Quackable {
	def flock = []

	def addQuackable(Quackable quackable){
		flock.add(quackable)
	}

	def void quack() {
		flock.each{_->_.quack()}
	}
}

void simulate(AbstractDuckFactory countFactory){
	
	def mDuck = countFactory.createMallardDuck()
	def rDuck = countFactory.createRedheadDuck()
	def rbDuck = countFactory.createRubberDuck()
	def dcDuck = countFactory.createDuckCall()
	def goose = new Goose()
	
	def flockOfDucks = new Flock()
	flockOfDucks.addQuackable(mDuck)
	flockOfDucks.addQuackable(rDuck)
	flockOfDucks.addQuackable(rbDuck)
	flockOfDucks.addQuackable(dcDuck)

	def flockOfMallardDucks = new Flock()

	flockOfMallardDucks.addQuackable(countFactory.createMallardDuck())
	flockOfMallardDucks.addQuackable(countFactory.createMallardDuck())
	flockOfMallardDucks.addQuackable(countFactory.createMallardDuck())
	flockOfMallardDucks.addQuackable(countFactory.createMallardDuck())

	simulate(flockOfDucks)
	simulate(flockOfMallardDucks)
	simulate(new GooseAdapter(goose))

	println("number of ducks quacked is ${QuackCounter.quackCounter}")

}

void simulate(Quackable duck) {
	duck.quack()
}

class Goose {
	def honk(){
		println('Honk')
	}
}




simulate(new CountingDuckFactory())