import groovy.transform.Canonical

//Implementor
interface DrawingApi {
	def void draw(int x, int y, int radius)
}

//Concrete Implementor 1
class DrawingApiImpl1 implements DrawingApi {
	def void draw(int x, int y, int radius) {
		println("concrete shape by implementor1 has ${x} ${y} coordinates and ${radius} radius")
	}
}

//Concrete Implementor 2
class DrawingApiImpl2 implements DrawingApi {
	def void draw(int x, int y, int radius) {
		println("concrete shape by implementor2 has ${x} ${y} coordinates and ${radius} radius")
	}
}

//Abstraction
abstract class Shape {
	def DrawingApi drawingApi


	def abstract void drawCircle()

	def abstract void resizeByPercentage(int pct)
}

//redefined abstraction
@Canonical
class CircleShape extends Shape {
		def int x, y, radius

		def void drawCircle() {
			drawingApi.draw(x, y, radius)
		}

		def void resizeByPercentage(int pct) {
			radius *= pct
		}
}

class BridgePattern {
	def shapes = [new CircleShape(drawingApi: new DrawingApiImpl1(), x:1, y:2, radius:5),
	new CircleShape(drawingApi: new DrawingApiImpl2(), x:10, y:34, radius:6) ]
}

def bridge = new BridgePattern()
	def int pct = 2.5
	bridge.shapes.each {_-> 
						_.resizeByPercentage(pct)
						_.drawCircle()}