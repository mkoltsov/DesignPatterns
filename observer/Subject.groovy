/**
 * Created by mkoltsov on 6/8/15.
 */
interface Subject {
    def notifyObservers()

    def addObserver(Observer sub)

    def deleteObserver(Observer sub)

    def setState(Object state)

    def getState(Object state)

}
