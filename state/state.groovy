import jdk.nashorn.internal.ir.annotations.Immutable

interface State {
    def insertQuarter()

    def ejectQuarter()

    def turnCrank()

    def dispense()

}

class NoQuarter implements State {

    @Override
    def insertQuarter() {
        return null
    }

    @Override
    def ejectQuarter() {
        return null
    }

    @Override
    def turnCrank() {
        return null
    }

    @Override
    def dispense() {
        return null
    }
}

class GumballMachine {
    State soldOutState
    State noQuarterState
    State hasQuarterState
    State soldState

    def state = soldOutState

    def count = 0

    GumballMachine(int numberGumballs) {
        this.soldOutState = new SoldOutState()
        this.hasQuarterState = new HasQuarterState()
        this.noQuarterState = new NoQuarterState()
        this.soldState = new SoldState()
        this.count = numberGumballs

        if (numberGumballs > 0) {
            state = noQuarterState
        }
    }

    def void insertQuarter() {
        state.insertQuarter()
    }

    def void ejectQuarter() {
        state.ejectQuarter()
    }

    def void turnCrank() {
        state.turnCrank()
        state.dispense()
    }

    def void setState(State state) {
        this.state = state
    }

    def void releaseBall() {
        println("A gumball comes out")

        if (count != 0) {
            count--
        }
    }
}

class SoldOutState implements State {

    @Override
    def insertQuarter() {
        return null
    }

    @Override
    def ejectQuarter() {
        return null
    }

    @Override
    def turnCrank() {
        return null
    }

    @Override
    def dispense() {
        return null
    }
}

@Immutable
class HasQuarterState implements State {
    def GumballMachine gumballMachine

    @Override
    def insertQuarter() {
        println("You can't insert another quarter")
    }

    @Override
    def ejectQuarter() {
        println("Quarter returned")
        gumballMachine.setState(gumballMachine.getNoQuarterState())
    }

    @Override
    def turnCrank() {
        println("You turned...")
        gumballMachine.setState(gumballMachine.getSoldState())
    }

    @Override
    def dispense() {
        println("No quarter dispensed")
    }
}

class NoQuarterState implements State {

    @Override
    def insertQuarter() {
        return null
    }

    @Override
    def ejectQuarter() {
        return null
    }

    @Override
    def turnCrank() {
        return null
    }

    @Override
    def dispense() {
        return null
    }
}

class SoldState implements State {

    @Override
    def insertQuarter() {
        return null
    }

    @Override
    def ejectQuarter() {
        return null
    }

    @Override
    def turnCrank() {
        return null
    }

    @Override
    def dispense() {
        return null
    }
}

