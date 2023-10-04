package ui;

import entity.QuestionAnswer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FAQMain extends JPanel {
    private JTextArea AnswerTextArea;
    private JTextArea AnswerCodeTextArea;
    private JButton AddButton;
    private JLabel AnswerCodeLabel;
    private JLabel AnswerLabel;
    private JLabel QuestionLabel;
    private JTextArea QuestionTextArea;
    private JPanel rootPanel;

    public QuestionAnswerOperation qao;

    public FAQMain() {

        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = AnswerTextArea.getText();
                String q = QuestionTextArea.getText();
                String code = AnswerCodeTextArea.getText();
                qao = new QuestionAnswerOperation(q, a, code);

                System.out.println(a + " " + q + " " + code);

                qao.performInsertNewFAQ();
            }
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame frame = new JFrame("GUIApp");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new FAQMain().rootPanel);
                frame.pack();
                frame.setVisible(true);

            }
        });

    }
}
