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

    private String answerText;
    private String questionText;
    private String answerCodeText;

    public FAQ_UI() {
        qao = new QuestionAnswerOperation();

        initButtonsActionListeners();
        initTable();
    }

    public void initButtonsActionListeners() {
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerText = AnswerTextArea.getText();
                questionText = QuestionTextArea.getText();
                answerCodeText = AnswerCodeTextArea.getText();

                System.out.println(answerText + " " + questionText + " " + answerCodeText);

                qao.performInsertNewFAQ(answerText, questionText, answerCodeText);
            }
        });

        ShowContent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answerText = AnswerTextArea.getText();

                List<QuestionAnswer> data = qao.findWithName(answerText);

                for (QuestionAnswer curr_elem : data
                ) {
                    System.out.print(curr_elem.getAnswerText() + ", ");
                }
            }
        });
    }

    public void initTable() {
        System.out.println("AnswersTable: " + AnswersTable);
        System.out.println("rootPanel: " + rootPanel);

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(new String[] {"Question", "Answer", "Answer Code"});

        dtm.addRow(new Object[] {"Some q", "Some a", "Some a c"});

        AnswersTable.setModel(dtm);
    }
}
