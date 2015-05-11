//unfinished
import groovy.transform.Canonical

class Originator {
	def String state

	def void set(String state) {
		println("Originator: setting state to ${state}")
		this.state = state
	}

	def Memento saveToMemento() {
		println("Originator: Saving to Memento")
		return new Memento(state:this.state)
	}

	def void restoreFromMemento(Memento memento) {
		this.state = memento.getState()
		println("Originator: State after restoring from Memento - ${this.state}")
	}
	@Canonical
	static class Memento {
		def private String state
	}
}

def savedStates = []

def originator = new Originator()

originator.set("State1")
originator.set("State2")
savedStates.add(originator.saveToMemento())
originator.set("State3")
savedStates.add(originator.saveToMemento())
originator.set("State4")

originator.restoreFromMemento(savedStates[1])

