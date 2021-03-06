import groovy.transform.Canonical

interface State {
    def insertQuarter()

    def ejectQuarter()

    def turnCrank()

    def dispense()

}

@Canonical
class NoQuarter implements State {
    def GumballMachine gumballMachine

    @Override
    def insertQuarter() {
        println("You inserted a quarter")
        gumballMachine.setState(gumballMachine.HasQuarterState())
    }

    @Override
    def ejectQuarter() {
        println("You haven't inserted a quarter")
    }

    @Override
    def turnCrank() {
        println("You turned, but there's no quarter")
    }

    @Override
    def dispense() {
        println("Need to play first")
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
        this.soldOutState = new SoldOutState(this)
        this.hasQuarterState = new HasQuarterState(random: new Random(System.currentTimeMillis()),
                gumballMachine: this)
        this.noQuarterState = new NoQuarterState(this)
        this.soldState = new SoldState(this)
        this.winnerState = new WinnerState(this)

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

@Canonical
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

@Canonical
class HasQuarterState implements State {
    def Random random
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

@Canonical
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

@Canonical
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

@Canonical
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

def gumballMachine = new GumballMachine(5)

println(gumballMachine)

gumballMachine.insertQuarter()
gumballMachine.turnCrank()

println(gumballMachine)

gumballMachine.insertQuarter()
gumballMachine.turnCrank()
gumballMachine.insertQuarter()
gumballMachine.turnCrank()
println(gumballMachine)
