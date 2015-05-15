import groovy.transform.Canonical
import java.util.Random
abstract class PurchasePower {
	def final double BASE = 1.00

	protected PurchasePower successor

	def void setSuccessor(PurchasePower successor) {
		this.successor = successor
	}

	def abstract void processRequest(PurchaseRequest request)
}

class ManagerPPower extends PurchasePower {
	def final ALLOWABLE = BASE *10

	def void processRequest(PurchaseRequest request) {
		if (request.getAmount() < ALLOWABLE) {
			println("Manager will approve ${request.getAmount()}")
		} else if (successor != null) {
			successor.processRequest(request)
		}
	}
}

class DirectorPPower extends PurchasePower {
		def final ALLOWABLE = BASE *20

	def void processRequest(PurchaseRequest request) {
		if (request.getAmount() < ALLOWABLE) {
			println("Director will approve ${request.getAmount()}")
		} else if (successor != null) {
			successor.processRequest(request)
		}
	}
}

class VicePresidentPPower extends PurchasePower {
		def final ALLOWABLE = BASE *40

	def void processRequest(PurchaseRequest request) {
		if (request.getAmount() < ALLOWABLE) {
			println("Vice President will approve ${request.getAmount()}")
		} else if (successor != null) {
			successor.processRequest(request)
		}
	}
}

class PresidentPPower extends PurchasePower {
		def final ALLOWABLE = BASE *60

	def void processRequest(PurchaseRequest request) {
		if (request.getAmount() < ALLOWABLE) {
			println("President will approve ${request.getAmount()}")
		} else if (successor != null) {
			println("your request for ${request.getAmount()} needs a board meeting!")
		}
	}
}

@Canonical
class PurchaseRequest {
	def double amount
	def String purpose
}

PurchasePower managerPower = new ManagerPPower()
PurchasePower directorPower = new DirectorPPower()
PurchasePower vicePPower = new VicePresidentPPower()
PurchasePower presidentPower = new PresidentPPower()

managerPower.setSuccessor(directorPower)
directorPower.setSuccessor(vicePPower)
vicePPower.setSuccessor(presidentPower)

while(true) {
	println("We're proccessing requests in real time!")

	def random = new Random()

	managerPower.processRequest(new PurchaseRequest(amount:(double)random.nextInt(100), purpose:"General"))
}