import javax.swing.JFrame
import java.awt.Graphics

class MyFrame extends JFrame {
	MyFrame(title) {
		super(title)
		this.setSize(300, 300)
		this.setVisible(true)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
	}

	def void paint(Graphics graphics){
		super.paint(graphics)
		graphics.drawString("i rule", 100, 100)
	}
}

new MyFrame("init")