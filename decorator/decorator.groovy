abstract class Beverage {
    def private description = "Unknown Beverage"

    def getDescription() {
        return description
    }

    def abstract double cost()
}

public abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription()
}

class Espresso extends Beverage {

    def description = "Espresso"

    @Override
    double cost() {
        return 1.99
    }
}

class HouseBlend extends Beverage {
    def description = "House Blend"

    @Override
    double cost() {
        return 0.89
    }
}

class Mocha extends CondimentDecorator {
    Beverage beverage

    Mocha(Beverage beverage1) {
        beverage = beverage1
    }

    @Override
    String getDescription() {
        return beverage.getDescription() + ", Mocha"
    }

    @Override
    double cost() {
        return 0.20 + beverage.cost()
    }
}

Beverage beverage = new Espresso()

println("${beverage.getDescription()} + ${beverage.cost()}")

Beverage beverage1 = new HouseBlend()

beverage1 = new Mocha(beverage1)
beverage1 = new Mocha(beverage1)

println("${beverage1.getDescription()} + ${beverage1.cost()}")