abstract class PC{
	abstract getSpecs()
	abstract getName()
}

class PcFactory {
	def static PC createPc(String type){
		switch(type) {
			case "WindowsPC":
				return new PC(){
					def getSpecs(){
						return "i5/16gb/128 ssd"
					}

					def getName(){
						return "Wintel PC"	
					}
				}	
			case "Mac":
				return new PC(){
					def getSpecs(){
						return "i7/16gb/256 ssd"
					}

					def getName(){
						return "Mac mini 2015"	
					}
				}
		}
	}
}

def pc = PcFactory.createPc("Mac")

println("my pc is ${pc.getName()} with ${pc.getSpecs()}")