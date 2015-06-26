import groovy.transform.Canonical

import java.lang.reflect.*

interface PersonBean {
	String getName()
	String getGender()
	String getInterests()
	int getHotOrNotRating()

	void setName(String name)
	void setGender(String gender)
	void setInterests(String interests)
	void setHotOrNotRating(int rating)
}

@Canonical
class PersonBeanImpl implements PersonBean {
	def String name
	def String gender
	def String interests
	int rating
	int ratingCount = 0
	
	def int getHotOrNotRating() {
		if (ratingCount) {
			return 0
		} 
		return (rating/ratingCount)
	}
	
	def void setHotOrNotRating(int rating) {
		this.rating += rating
		ratingCount++
	}
}

@Canonical
class OnwerInvocationHandler implements InvocationHandler {
	PersonBean personBean
	
	@Override
	def Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
		try {
			switch (method.getName()) {
				case ~/^get.*/: 
				return method.invoke(proxy, args)
				case ~/^set.*/: 
				return method.invoke(proxy, args)
				case ~/^setHotOrNotRating/: 
				throw new IllegalAccessException()
				//default: println('4')
			}

		} catch(InvocationTargetException e) {
			e.printStackTrace()
		}
		return null
	}
}

@Canonical
class NonOnwerInvocationHandler implements InvocationHandler {
	PersonBean personBean
	
	@Override
	def Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
		try {
			switch (method.getName()) {
				case ~/^get.*/:  throw new IllegalAccessException()
				case ~/^set.*/:  throw new IllegalAccessException()
				case ~/^setHotOrNotRating/: return method.invoke(person, args)
			}

		} catch(InvocationTargetException) {
			e.printStackTrace()
		}
		return null
	}
}

def PersonBean getOwnerProxy(PersonBean person) {
	return (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(),
											   person.getClass().getInterfaces(),
											   new OnwerInvocationHandler(person))
}

def PersonBean getNonOwnerProxy(PersonBean person) {
	return (PersonBean) Proxy.newProxyInstance(person.getClass().getClassLoader(),
											   person.getClass().getInterfaces(),
											   new NonOnwerInvocationHandler(person))
}

def PersonBean joe = new PersonBeanImpl(name:'Joe',  gender:'M', interests:'Bowling', rating:0)

println("my name is ${joe.name} and my rating is ${joe.rating}")

def PersonBean ownerProxy = getOwnerProxy(joe)
def PersonBean nonOwnerProxy = getNonOwnerProxy(joe)

ownerProxy.setHotOrNotRating(10)
