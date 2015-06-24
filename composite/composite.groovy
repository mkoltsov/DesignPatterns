import groovy.transform.Immutable
import groovy.transform.Canonical

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

@Canonical
class Menu extends MenuComponent {
    def menuComponents = []
    def String name
    def String description

    @Override
    void add(MenuComponent component) {
        menuComponents.add(component)
    }

    @Override
    void remove(MenuComponent component) {
        menuComponents.remove(component)
    }

    @Override
    MenuComponent getChild(int i) {
        return menuComponents[i] as MenuComponent
    }

    @Override
    void print() {
        println("${name} - ${description}")

        menuComponents.each {_ -> ( _ as MenuComponent).print()}
    }
}

@Canonical
class Waitress {
    def MenuComponent allMenus

    def void printMenu(){
        allMenus.print()
    }
}

def pancakeMenu = new Menu(menuComponents: [], name: "Pancake house menu", description: "breakfast")
def dinerMenu = new Menu(menuComponents: [], name: "Diner menu", description: "lunch")
def cafeMenu = new Menu(menuComponents: [], name: "Cafe menu", description: "diner")
def dessertMenu = new Menu(menuComponents: [], name: "Dessert menu", description: "dessert of course")

def allMenus = new Menu(menuComponents: [], name: "All menus", description: "all menus combined")
allMenus.add(pancakeMenu)
allMenus.add(dinerMenu)
allMenus.add(cafeMenu)

dinerMenu.add(new MenuItem(name: "Pasta", description: "Spaghetti with sauce", vegeterian: true, price: 100.00))
dinerMenu.add(dessertMenu)

dessertMenu.add(new MenuItem(name: "Apple Pie", description: "pie", vegeterian: true, price: 3.00))

new Waitress(allMenus).printMenu()
