package ui;

import entity.QuestionAnswer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
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
    private JButton ShowContent;

    public QuestionAnswerOperation qao;

    public FAQMain() {

        System.out.println("For fake commit... Just in order to rollback this change");

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

        ShowContent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = AnswerTextArea.getText();
                String q = QuestionTextArea.getText();
                String code = AnswerCodeTextArea.getText();
                qao = new QuestionAnswerOperation(q, a, code);

                List<QuestionAnswer> data = qao.findWithName(a);

                for (QuestionAnswer curr_elem : data
                     ) {
                    System.out.print(curr_elem.getAnswerText() + ", ");
                }
            }
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame frame = new JFrame("FAQ");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new FAQMain().rootPanel);
                frame.setPreferredSize(new Dimension(500, 500));
                frame.pack();
                frame.setVisible(true);

            }
        });

    }
}
