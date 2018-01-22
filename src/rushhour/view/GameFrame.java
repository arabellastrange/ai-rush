package rushhour.view;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import rushhour.controller.RushHour;

public class GameFrame extends JFrame {

	private static final long serialVersionUID = 4208649157275353992L;
	
	public GameFrame (String title, RushHour controller) {
		super(title);
		setPreferredSize(new Dimension(800,600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout layout = null;
		JPanel main = new JPanel(layout);
		controller.getGamePanel().setBounds(0,0,525, 570);
		GameChooser chooser = new GameChooser(controller);
		chooser.setBounds(531,0,260,600);
		main.add(controller.getGamePanel());
		main.add(chooser);
		getContentPane().add(main);
		pack();
		setVisible(true);
	}
}
