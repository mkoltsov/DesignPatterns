/**
 * Created by mkoltsov on 6/8/15.
 */
class PoliceStation implements Observer {

    @java.lang.Override
    def publish(Object state) {
        println("Police station ${state} ")
    }
}
