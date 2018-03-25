package Bot;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;
import java.util.Random;

public class ChatBot extends JFrame implements KeyListener {

	int state = 0;
	String Output = "Hi What is your name";
	Random rn = new Random();


	JPanel p = new JPanel();
	JTextArea dialog = new JTextArea(20, 50);
	JTextArea input = new JTextArea(2, 50);
	JScrollPane scroll = new JScrollPane(dialog, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	String[] info = new String[2];
	
	String[] dangers = {"Lions", "Panthers", "Tigers", "Wild Fires", "Lightening"};


	public static void main(String[] args) {
		new ChatBot();
	}

	public ChatBot() {
		super("Bot");
		setSize(600, 400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		dialog.setEditable(false);
		input.addKeyListener(this);

		p.add(scroll);
		p.add(input);
		p.setBackground(Color.blue);
		add(p);

		setVisible(true);
	}

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(false);

			// -----grab quote-----------
			String quote = input.getText();
			input.setText("");

			addText(" You:\t" + quote);
			quote.trim();

			
			quote.trim();
			byte response = 0;

			switch (state) {
			case 0:
				Output = "Hello tell fortune teller your name.";
				state++;
				break;
			case 1:
				Output = "Where are you from " + quote + "?";
				state++;
				info[0] = quote;
				break;
			case 2:
				int x =  rn.nextInt(4) + 0;

				Output = info[0] + " from " + quote + " you should stay away from " +  dangers[x] + "." ;
				state = 0;
				info = new String[2];
				break;
				
				
			default:
				Output = "Invalid month";
				break;
			}

			addText("\n Bot\t" + Output);
			
			if (state == 0) {
				addText("\n Bot\tLets move on to someone else.\n");

			}

			addText("\n");

			/*
			 * 0:we're searching through chatBot[][] for matches 1:we didn't
			 * find anything 2:we did find something
			 */
			// -----check for matches----
			/*
			 * int j = 0;// which group we're checking while (response == 0) {
			 * if (inArray(quote.toLowerCase(), commandList[j * 2])) { response
			 * = 2; int r = (int) Math.floor(Math.random() * commandList[(j * 2)
			 * + 1].length); addText("\n Bot\t" + commandList[(j * 2) + 1][r]);
			 * } j++; if (j * 2 == commandList.length - 1 && response == 0) {
			 * response = 1; } }
			 * 
			 * // -----default-------------- if (response == 1) { int r = (int)
			 * Math.floor(Math.random() * commandList[commandList.length -
			 * 1].length); addText("\n Bot\t" + commandList[commandList.length -
			 * 1][r]); } addText("\n");
			 */
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			input.setEditable(true);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void addText(String str) {
		dialog.setText(dialog.getText() + str);
	}

	public boolean inArray(String in, String[] str) {
		boolean match = false;
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals(in)) {
				match = true;
			}
		}
		return match;
	}
}