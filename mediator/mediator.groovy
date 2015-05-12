import java.util.Random
class Mediator {
	def slotFull = false
	def int number

	def synchronized void storeMessage(int num) {
		while(slotFull == true) {
			try {
				wait()	
			}
			catch(Exception e) {
				
			}
			slotFull = true
			number = num
			notifyAll()
		}
	}

	def synchronized int retrieveMessage() {
		while(slotFull == false) {
			try {
				wait()	
			}
			catch(Exception e) {
				
			}
			slotFull = false
			notifyAll()
			return number
		}
	}
}

class Producer extends Thread {
	private Mediator mediator
	private int id

	private static int num = 1

	Producer(Mediator m) {
		this.mediator = m
		id = num++
	}

	def void run() {
		int num

		while(true) {
			mediator.storeMessage(num = new Random().nextInt(100))
			println("p ${id} - ${num}")
		}
	}
}

class Consumer extends Thread {
	private Mediator mediator
	private int id

	private static int num = 1

	Consumer(Mediator m) {
		this.mediator = m
		id = num++
	}

	def void run() {
		while(true) {
			println("c ${id} - ${mediator.retrieveMessage()}")
		}
	}
}

def mediator = new Mediator()

new Producer(mediator).start()
new Producer(mediator).start()
new Consumer(mediator).start()
new Consumer(mediator).start()
new Consumer(mediator).start()
new Consumer(mediator).start()