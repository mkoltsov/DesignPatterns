interface Duck {
	def quack()
	def fly()
}
	
interface Turkey {
	def gobble()
	def jump()
}
	
class MallardDuck implements Duck {
  def quack() {
	println('Quacking...')
  }
  def fly() {
	println('Flying...')
  }
}

class TurkeyAdapter implements Duck {
  def turkey
  TurkeyAdapter(Turkey turkey){
	this.turkey = turkey
  }
  
  def quack(){
     turkey.gobble()
  }
  def fly(){
      println("i don't do much flying, instead i ${turkey.jump()}")
  }
  
}

class TGTurkey implements Turkey {
  def gobble() {
	println('Gobb...')
  }
  def jump() {
	println('Yay!')
  }
}

def testDuck = {_ -> _.fly()}
def turkeyAdapter = new TurkeyAdapter(new TGTurkey())
testDuck(turkeyAdapter)
testDuck(new MallardDuck())
//def turkeyAdapter = new TurkeyAdapter(new TGTurkey()).fly()