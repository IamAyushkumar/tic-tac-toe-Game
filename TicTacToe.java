package Game;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener {

	public enum gamestatus {
		ZeroWon, crossWon, Tie, Incomplete
	}

	private boolean crossturn = true;
	public static final int Board_Size = 3;
	public static final String Cross = "X";
	public static final String Zero = "O";
	private JButton[][] buttonarray = new JButton[Board_Size][Board_Size];

	public TicTacToe() {
		super.setTitle("TicTac Toe");
		super.setSize(800, 800);

		GridLayout gridlayout = new GridLayout(Board_Size, Board_Size);
		super.setLayout(gridlayout);
		Font font = new Font("Comic sans", 1, 150);
		for (int row = 0; row < Board_Size; row++) {
			for (int col = 0; col < Board_Size; col++) {
				JButton button = new JButton();
				button.setBackground(Color.BLACK);
				buttonarray[row][col] = button;
				button.setFont(font);
				button.addActionListener(this);
				super.add(button);
			}
		}
		super.setResizable(false);
		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton clickedbutton = (JButton) e.getSource();
		makemove(clickedbutton);
		gamestatus gs = getGamestatus();
		if (gs != gamestatus.Incomplete) {
			declarewinner(gs);
			int yesorno = JOptionPane.showConfirmDialog(this, "do you want to play again ?");
			if (yesorno == JOptionPane.YES_OPTION) {
				for (int i = 0; i < Board_Size; i++) {
					for (int j = 0; j < Board_Size; j++) {
						JButton button = buttonarray[i][j];
						button.setText("");
					}
				}
			} else {
				super.dispose();
			}

		}
	}

	private void makemove(JButton clickedButton) {
		if (clickedButton.getText().length() > 0) {
			JOptionPane.showMessageDialog(this, "Move not allowed");
			return;
		}
		if (crossturn) {
			clickedButton.setText(Cross);
			clickedButton.setBackground(Color.red);
		} else {
			clickedButton.setText(Zero);
			clickedButton.setBackground(Color.green);
		}

		crossturn = !crossturn;
	}

	private gamestatus getGamestatus() {

		int row = 0;
		int col = 0;
		String text1 = "";
		String text2 = "";
		// checking in rows
		for (row = 0; row < Board_Size; row++) {
			col = 0;
			while (col < Board_Size - 1) {
				text1 = buttonarray[row][col].getText();
				text2 = buttonarray[row][col + 1].getText();
				if (text1.length() == 0 || !text1.equals(text2)) {
					break;
				}

				col++;
			}

			if (col == Board_Size - 1) {
				if (text1 == "X")
					return gamestatus.crossWon;
				else {
					return gamestatus.ZeroWon;
				}
			}
		}

		// for checking in columns
		for (col = 0; col < Board_Size; col++) {
			row = 0;
			while (row < Board_Size - 1) {
				text1 = buttonarray[row][col].getText();
				text2 = buttonarray[row + 1][col].getText();
				if (text1.length() == 0 || !text1.equals(text2)) {
					break;
				}
				row++;
			}

			if (row == Board_Size - 1) {
				if (text1 == "X")
					return gamestatus.crossWon;
				else {
					return gamestatus.ZeroWon;
				}
			}
		}

		// for checking in diag1
		row = 0;
		col = 0;
		while (row < Board_Size - 1) {

			text1 = buttonarray[row][col].getText();
			text2 = buttonarray[row + 1][col + 1].getText();
			if (text1.length() == 0 || !text1.equals(text2)) {
				break;
			}

			row++;
			col++;
		}

		if (row == Board_Size - 1) {
			if (text1 == "X")
				return gamestatus.crossWon;
			else {
				return gamestatus.ZeroWon;
			}
		}

		// for checking in diag2
		row = 0;
		col = Board_Size - 1;
		while (row < Board_Size - 1) {

			text1 = buttonarray[row][col].getText();
			text2 = buttonarray[row + 1][col - 1].getText();
			if (text1.length() == 0 || !text1.equals(text2)) {
				break;
			}

			row++;
			col--;
		}

		if (row == Board_Size - 1) {
			if (text1 == "X")
				return gamestatus.crossWon;
			else {
				return gamestatus.ZeroWon;
			}
		}

		for (int i = 0; i < Board_Size; i++) {
			for (int j = 0; j < Board_Size; j++) {
				JButton button = buttonarray[i][j];
				if (button.getText().length() == 0) {
					return gamestatus.Incomplete;
				}
			}
		}
		return gamestatus.Tie;
	}

	private void declarewinner(gamestatus gs) {
		if (gs == gamestatus.crossWon) {
			JOptionPane.showMessageDialog(this, "Cross won the game");
			return;
		}
		if (gs == gamestatus.ZeroWon) {
			JOptionPane.showMessageDialog(this, "zero won the game");
			return;
		}
		if (gs == gamestatus.Tie) {
			JOptionPane.showMessageDialog(this, "game tied");
			return;
		}
	}

}
