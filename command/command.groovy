interface Command {
	def void execute()
}

class LightSwitcher implements Command {
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

class ACSwitcher implements Command {
	def private void switchAcOn(){
		println("Switching AC on")
	}
	
	def private void switchACOff(){
		println("Switching AC off")
	}

	def void execute(){
		switchAcOn()
		switchACOff()
	}

}

class PartyMixer implements Command {
	def commands
	PartyMixer(commands){
		this.commands = commands
	}
	
	def void execute(){
		commands.each{
			_ -> _.execute()
		}
	}
}

class Remote {
	def command
	def setCommand(Command command) {
		this.command = command
		return this
	}
	
	def pushTheButton(){
		command?.execute()	
	}
}
def remote = new Remote()
remote.setCommand(new ACSwitcher()).pushTheButton()
remote.setCommand(new LightSwitcher()).pushTheButton()
println('-------')
remote.setCommand(new PartyMixer([new ACSwitcher(), new LightSwitcher()])).pushTheButton()

