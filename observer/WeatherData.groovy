/**
 * Created by mkoltsov on 6/8/15.
 */
class WeatherData implements Subject {

    List<Observer> observers = []
    def state

    @java.lang.Override
    def notifyObservers() {
        observers.each { _ -> _.publish(state) }
    }

    @java.lang.Override
    def addObserver(Observer sub) {
        observers.add(sub)
    }

    @java.lang.Override
    def deleteObserver(Observer sub) {
        observers.remove(sub)
    }

    @java.lang.Override
    def setState(Object state) {
        this.state = state
    }

    @java.lang.Override
    def getState(Object state) {
        return this.state
    }
}
