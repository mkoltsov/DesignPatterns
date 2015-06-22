import groovy.transform.Immutable

abstract class MenuComponent {
    def void add(MenuComponent component) {
        throw new UnsupportedOperationException()
    }

    def void remove(MenuComponent component) {
        throw new UnsupportedOperationException()
    }

    def MenuComponent getChild(int i) {
        throw new UnsupportedOperationException()
    }

    def String getName() {
        throw new UnsupportedOperationException()
    }

    def String getDescription() {
        throw new UnsupportedOperationException()
    }

    def double getPrice() {
        throw new UnsupportedOperationException()
    }

    def boolean getVegeterian() {
        throw new UnsupportedOperationException()
    }

    def void print() {
        throw new UnsupportedOperationException()
    }
}

@Immutable
class MenuItem extends MenuComponent {
    def String name
    def String description
    def double price
    def boolean vegeterian

    @Override
    void print() {
        println("the meal is vegeterian ${vegeterian}, with price ${price}, name is ${name}")
    }
}

