interface Command {
	def void execute(){}
}

class LightSwitcher implemants Command {
	def private void switchLightOn(){
		println("Switching the light on")
	}
    
    def private void switchLightOff(){
		println("Switching the light off")
	}

	def void execute(){
         switchLightOn()
         switchLightOff()
	}

}

class ACSwitcher implemants Command {
	def private void switchAcOn(){
		println("Switching the light on")
	}
    
    def private void switchACOff(){
		println("Switching the light off")
	}

	def void execute(){
         switchAcOn()
         switchAcOn()
	}

}