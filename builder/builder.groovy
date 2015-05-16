interface AbstractBuilder {
	def buildDay()
	def AddGuests()
	def AddTickets()
	def getVacationPlanner()

}

class VacationBuilder {
	def planner = new Expando()
	def buildDay() {
		planner.day = new Date()
	}
	def AddGuests() {
		planner.guests = ['Ben', 'Holly']
	}
	def AddTickets() {
		planner.tickets = ['100']
	}
	def getVacationPlanner() {
		return planner
	}
}

def plannerBuilder = new VacationBuilder()
	plannerBuilder.buildDay()
	plannerBuilder.AddGuests()
	plannerBuilder.AddTickets()
	plannerBuilder.AddGuests()
def planner = plannerBuilder.getVacationPlanner()

println("${planner}")