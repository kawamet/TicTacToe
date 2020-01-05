package pl.com.sda.karolina.swing.ex1.ticTacToeCopy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowTTT {
    private JFrame frame;
    private JButton[] buttons = new JButton[9];
    private TicTacToe game = new TicTacToe();

    public WindowTTT() {
        frame = new JFrame("TTT");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new GridLayout(3, 3));

        Font font = new Font("arial", Font.BOLD, 32);

        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setHorizontalAlignment(SwingConstants.CENTER);
            button.setFont(font);
            int finalI = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    handleElementClicked(finalI);
                }
            });

            frame.add(button);
            buttons[i] = button;

        }
        refreshTitle();

    }

    private void handleElementClicked(int elementID) {
        //i -->0-8
        // i%3 --> daje nam col
        //i /3 --> daje nam row

        int row = elementID / 3;
        int col = elementID % 3;
        game.addMark(row, col);
        refreshButton(buttons[elementID]);
        refreshTitle();

        chcekResult();

    }

    private void chcekResult() {
        GameResult result = game.getResult();
        switch (result) {
            case PLAYER_O_WIN:
                disableALlButons();
                showDiaglog("Player O win");
                break;
            case PLAYER_X_WIN:
                disableALlButons();
                showDiaglog("Player X win");

                break;
            case DRAW:
                disableALlButons();
                showDiaglog("Till");
                break;
        }

    }

    private void disableALlButons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    private void showDiaglog(String title) {
        JDialog dialog = new JDialog(frame, true);
        dialog.add(new JLabel(title));
        dialog.setLocationRelativeTo(frame);
        dialog.setSize(200, 100);
        dialog.setVisible(true);
    }

    private void refreshButton(JButton button) {
        button.setText(game.isPlayerXTurn() ? "X" : "O");
        button.setEnabled(false);
    }

    private void refreshTitle() {
        if (game.isPlayerXTurn()) {
            frame.setTitle("O turn");
        } else {
            frame.setTitle("X turn");
        }

    }

    void show() {
        frame.setVisible(true);
    }
}
