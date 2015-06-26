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

void simulate(){
	def mDuck = new MallardDuck()
	def rDuck = new RedHeadDuck()
	def rbDuck = new RubberDuck()
	def dcDuck = new DuckCall()
	def goose = new Goose()

	simulate(mDuck)
	simulate(rDuck)
	simulate(rbDuck)
	simulate(dcDuck)
	simulate(new GooseAdapter(goose))

}

void simulate(Quackable duck) {
	duck.quack()
}

class Goose {
	def honk(){
		println('Honk')
	}
}

@Canonical
class GooseAdapter implements Quackable {
	def Goose goose

	def void quack(){
		goose.honk()
	} 
}
simulate()