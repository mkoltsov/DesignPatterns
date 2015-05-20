import groovy.transform.Canonical
interface PersonBean {
	String getname()
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
	int hotOrNotRating

	def getHotOrNotRating() {

	}

}