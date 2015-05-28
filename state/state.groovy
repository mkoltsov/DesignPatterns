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
    State winnerState

    def state = soldOutState

    def count = 0

    GumballMachine(int numberGumballs) {
        this.soldOutState = new SoldOutState()
        this.hasQuarterState = new HasQuarterState()
        this.noQuarterState = new NoQuarterState()
        this.soldState = new SoldState()
        this.winnerState = new WinnerState()

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

@Immutable
class SoldOutState implements State {
    def GumballMachine gumballMachine

    @Override
    def insertQuarter() {
        gumballMachine.setState(gumballMachine.getHasQuarterState())
    }

    @Override
    def ejectQuarter() {
        gumballMachine.setState(gumballMachine.noQuarterState)
    }

    @Override
    def turnCrank() {
        println("Sorry, no gumballs for you")
    }

    @Override
    def dispense() {
        println("Nothing to dispense")
    }
}

@Immutable
class HasQuarterState implements State {
    Random random = new Random(System.currentTimeMillis())
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
        int winner = random.nextInt(10)
        if (winner == 0 && gumballMachine.count > 1) {
            gumballMachine.setState(gumballMachine.winnerState)
        } else {
            gumballMachine.setState(gumballMachine.getSoldState())
        }
    }

    @Override
    def dispense() {
        println("No quarter dispensed")
    }
}

@Immutable
class NoQuarterState implements State {
    def GumballMachine gumballMachine

    @Override
    def insertQuarter() {
        println("You've inserted a quarter")
        gumballMachine.setState(gumballMachine.getHasQuarterState())
    }

    @Override
    def ejectQuarter() {
        println("You haven't inserted yet")
    }

    @Override
    def turnCrank() {
        println("there's no quarter")
    }

    @Override
    def dispense() {
        println("You need to pay first")
    }
}

@Immutable
class SoldState implements State {
    def GumballMachine gumballMachine

    @Override
    def insertQuarter() {
        println("please wait we've already given you a gumball")
    }

    @Override
    def ejectQuarter() {
        return null
    }

    @Override
    def turnCrank() {
        println("Turning twice doesn't give you another gumball")
    }

    @Override
    def dispense() {
        gumballMachine.releaseBall()

        if (gumballMachine.count > 0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState())
        } else {
            println("out of gumballs")
            gumballMachine.setState(gumballMachine.getSoldOutState())
        }
    }
}

@Immutable
class WinnerState implements State {
    def GumballMachine gumballMachine

    @Override
    def insertQuarter() {
        println("no need for a quarter")
    }

    @Override
    def ejectQuarter() {
        println("can't eject")
    }

    @Override
    def turnCrank() {
        println("will do nothing")
    }

    @Override
    def dispense() {
        println("You're a winner!")
        gumballMachine.releaseBall()
        if (gumballMachine.count == 0) {
            gumballMachine.setState(gumballMachine.getSoldOutState())
        } else {
            gumballMachine.releaseBall()
            if (gumballMachine.count > 0) {
                gumballMachine.setState(gumballMachine.getNoQuarterState())
            } else {
                println("out of gumballs")
                gumballMachine.setState(gumballMachine.getSoldOutState())
            }
        }
    }
}

