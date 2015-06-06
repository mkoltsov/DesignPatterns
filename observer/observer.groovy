class Publisher{
	def subscribers = []
	def publish() {
		subscribers.each(_-> _.publish)
	}
}
class Subscriber{
	def publish(){}
}