/**
 * Created by mkoltsov on 6/8/15.
 */
class WeatherStation implements Observer, Display {

    @java.lang.Override
    def display() {
        println(getClass())
    }

    @java.lang.Override
    def publish(Object state) {
        println("state of WeatherStation ${this.getClass()} is ${state} ")
    }
}
