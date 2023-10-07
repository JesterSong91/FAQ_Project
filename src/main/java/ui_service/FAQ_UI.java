package ui_service;

import entity.QuestionAnswer;
import entity.Tag;
import ui.QuestionAnswerOperation;
import ui.TagOperation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
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
    private JLabel FilterLabel;
    private JTextField FilterTextField;
    private JButton FilterButton;
    private JLabel TagFilterLabel;
    private JComboBox TagFilterComboBox;
    private JComboBox TagComboBox;
    private JLabel TagLabel;

    public QuestionAnswerOperation qao;
    public TagOperation to;

    private String answerText;
    private String questionText;
    private String answerCodeText;

    private String filterText;

    private TableRowSorter myRowSorter;

    private DefaultTableModel dtm;
    private DefaultComboBoxModel dcbm;

    private List<Tag> tags;

    public FAQ_UI() {
        qao = new QuestionAnswerOperation();
        to = new TagOperation();

        initButtonsActionListeners();
        initTable();
        initTagComboBox();

    }

    public void initButtonsActionListeners() {
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answerText = AnswerTextArea.getText();
                questionText = QuestionTextArea.getText();
                answerCodeText = AnswerCodeTextArea.getText();
                Long tagId = (long) TagComboBox.getSelectedIndex() + 1;

                System.out.println(answerText + " " + questionText + " " + answerCodeText);

                qao.performInsertNewFAQ(answerText, questionText, answerCodeText, tagId);
            }
        });

        ShowContent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterText = FilterTextField.getText();

                List<QuestionAnswer> data = qao.findWithName(filterText);

                for (QuestionAnswer curr_elem : data
                ) {
                    System.out.print(curr_elem.getAnswerText() + ", ");
                }
            }
        });

        FilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String answerText = AnswerTextArea.getText();
//
//                List<QuestionAnswer> data = qao.findWithName(answerText);
//

                myRowSorter.setRowFilter(rowFilterFactory(FilterTextField.getText()));
            }
        });
    }

    public void initTable() {
        dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(new String[] {"Question", "Answer", "Answer Code"});

        List<QuestionAnswer> data = qao.findAllData();

        for (QuestionAnswer curr_elem : data) {
            dtm.addRow(new String[] {curr_elem.getQuestionText(), curr_elem.getAnswerText(), curr_elem.getAnswerExampleCode()});
        }

        myRowSorter = new TableRowSorter<DefaultTableModel>(dtm);

        AnswersTable.setModel(dtm);
        AnswersTable.setRowSorter(myRowSorter);

        ListSelectionModel rowSelectionModel = AnswersTable.getSelectionModel();
        rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                StringBuilder output = new StringBuilder();

                int chosenRowIndex = 0;

                ListSelectionModel lsm = (ListSelectionModel)e.getSource();

                int firstIndex = e.getFirstIndex();
                int lastIndex = e.getLastIndex();
                boolean isAdjusting = e.getValueIsAdjusting();
                output.append("Event for indexes "
                        + firstIndex + " - " + lastIndex
                        + "; isAdjusting is " + isAdjusting
                        + "; selected indexes:");

                if (lsm.isSelectionEmpty()) {
                    output.append(" <none>");
                } else {
                    // Find out which indexes are selected.
                    int minIndex = lsm.getMinSelectionIndex();
                    int maxIndex = lsm.getMaxSelectionIndex();
                    for (int i = minIndex; i <= maxIndex; i++) {
                        if (lsm.isSelectedIndex(i)) {
                            output.append(" " + i);
                            chosenRowIndex = i;
                        }
                    }
                }

                System.out.println("dtm: " + dtm);

                try {
                    questionText = dtm.getValueAt(chosenRowIndex, 0).toString();
                }
                catch (NullPointerException ex){
                    questionText = new String();
                }

                try {
                    answerText = dtm.getValueAt(chosenRowIndex, 1).toString();
                }
                catch (NullPointerException ex){
                    answerText = new String();
                }

                try {
                    answerCodeText = dtm.getValueAt(chosenRowIndex, 2).toString();
                }
                catch (NullPointerException ex) {
                    answerCodeText = new String();
                }

                System.out.println("questionText: " + questionText);
                System.out.println("answerText: " + answerText);
                System.out.println("answerCodeText: " + answerCodeText);

                QuestionTextArea.setText(questionText);
                AnswerTextArea.setText(answerText);
                AnswerCodeTextArea.setText(answerCodeText);
            }
        });
    }

    public RowFilter rowFilterFactory(String filterText) {
        RowFilter<DefaultTableModel, String> myRowFilter = new RowFilter<DefaultTableModel, String>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends String> entry) {
                for (int i = entry.getValueCount() - 1; i >=0 ; i--) {
                    if (entry.getStringValue(i).contains(filterText)){
                        return true;
                    }
                }
                return false;
            }
        };

        return myRowFilter;
    }

    public void initTagComboBox() {
        dcbm = new DefaultComboBoxModel();

        tags = to.findAllTags();

        for (Tag currentTag : tags) {
            dcbm.addElement(currentTag.getTagName());
        }

        TagComboBox.setModel(dcbm);



    }
}
