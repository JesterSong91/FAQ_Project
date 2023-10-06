package ui_service;

import entity.QuestionAnswer;
import ui.QuestionAnswerOperation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FAQ_UI {
    private JTextArea AnswerTextArea;
    private JTextArea AnswerCodeTextArea;
    private JButton AddButton;
    private JLabel AnswerCodeLabel;
    private JLabel AnswerLabel;
    private JLabel QuestionLabel;
    private JTextArea QuestionTextArea;
    public JPanel rootPanel;
    private JButton ShowContent;
    private JTable AnswersTable;
    private JLabel SearchLabel;
    private JTextField SearchField;
    private JButton SearchButton;

    public QuestionAnswerOperation qao;

    public FAQ_UI() {
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

        System.out.println("AnswersTable: " + AnswersTable);
        System.out.println("rootPanel: " + rootPanel);

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(new String[] {"Question", "Answer", "Answer Code"});

        dtm.addRow(new Object[] {"Some q", "Some a", "Some a c"});

        AnswersTable.setModel(dtm);
    }
}
