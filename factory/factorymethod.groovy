abstract class SecurityFactory{
	def getException(){
		Exc ex = getActualException()
		println("${ex.getName()} = ${ex.getSeverity()} + ${ex.getRootCause()}")
	}
	abstract def getActualException() 
}

abstract class Exc(){
	abstract getName()
	abstract getSeverity()
	abstract getRootCause()
}

class TrojanException extends SecurityFactory {

	def getActualException(String type){
		return "you've got trojan!"
	}

}

class ExploitException extends SecurityFactory {

	def getActualException(String type){
		return 
	}

}