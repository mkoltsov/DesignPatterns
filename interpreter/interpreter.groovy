//NEEDS TO BE FIXED
import groovy.transform.Immutable
import groovy.transform.Canonical
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;
interface Expression {
	def int interpret(Map<String,Expression> variables)
}

@Immutable
class Number implements Expression {
	def int number
	def int interpret(Map<String,Expression> variables) {
		return number
	}
}

@Canonical
class Plus implements Expression {
	def Expression leftOperand
	def Expression rightOperand
	def int interpret(Map<String,Expression> variables) {
		return leftOperand.interpret(variables) + rightOperand.interpret(variables)
	}
}

@Canonical
class Minus implements Expression {
	def Expression leftOperand
	def Expression rightOperand
	def int interpret(Map<String,Expression> variables) {
		return leftOperand.interpret(variables) - rightOperand.interpret(variables)
	}
}

@Canonical
class Variable implements Expression {
	def String name
	def int interpret(Map<String,Expression> variables) {
		if (!variables.get(name)) return 0
		return variables.get(name).interpret(variables)
	}
}

class Evaluator implements Expression {
	def Expression syntaxTree

	Evaluator(String expression) {
		Stack<Expression> expressionStack = new Stack<Expression>()

		expression.split(" ").each { _ ->
			if (_ == "+") {
				def Expression subExpression = new Plus(expressionStack.pop(), expressionStack.pop())
				expressionStack.push(subExpression)
			} else if (_ == "-") {
				def Expression right = expressionStack.pop()
				def Expression left = expressionStack.pop()
				def Expression subExpression = new Minus(left, right)
				expressionStack.push(subExpression)
			} else {
				expressionStack.push(new Variable(_))
			}
		}
		syntaxTree = expressionStack.pop()
	}
	def int interpret(Map<String,Expression> variables) {
		return syntaxTree.interpret(variables)
	}
}

def expression = "w x z - +"

Evaluator sentence = new Evaluator(expression)

Map<String, Expression> variables = new HashMap<String, Expression>()

variables.put("w", new Number(5))
variables.put("x", new Number(10))
variables.put("z", new Number(42))

int result = sentence.interpret(variables)
println(result)