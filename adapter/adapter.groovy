class EnumerationAdapter implements Iterator {
	def enumeration
	
	EnumerationAdapter(Enumeration enumeration){
		this.enumeration = enumeration
	}

	def boolean hasNext(){
		return enumeration.hasMoreElements()
	}

	def void remove(){
 		throw new UnsupportedOperationException()
	}

	def Object next(){
		return enumeration.nextElement()
	}
}