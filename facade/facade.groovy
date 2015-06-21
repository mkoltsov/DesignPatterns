class HomeTheaterFacade {
	def amp, popper, dvd, tv
	HomeTheaterFacade(amp, popper, dvd, tv) {
		this.amp = amp
		this.popper = popper
		this.dvd = dvd
		this.tv = tv
	}
	
	def watchMovie() {
		amp.turnOn()
		popper.pop()
		dvd.turnOn()
		tv.switchOn()
		dvd.selectTheMovie()
		dvd.playTheMovie()
	}
	
	def switchOffMovie() {
		amp.turnOff()
		popper.turnOff()
		dvd.turnOff()
		tv.turnOff()
	}
}

def theater = new HomeTheaterFacade(new Expando(), new Expando(), new Expando(), new Expando())
theater.watchMovie()