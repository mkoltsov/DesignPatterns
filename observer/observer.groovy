interface Subject {
	def notifyObservers() {
	}

	def addObserver(Observer sub){
	}

	def deleteObserver(Observer sub){
	}

	def setState(Object state){

	}

	def getState(Object state){

	}

}

interface Observer {
	def publish(Object state){

	}
}