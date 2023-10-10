package ui_service;

import entity.QuestionAnswer;
import entity.Tag;
import ui.QuestionAnswerOperation;
import ui.TagOperation;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private JTable AnswersTable;
    private JLabel FilterLabel;
    private JTextField FilterTextField;
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

    private TableRowSorter rowSorter;
    private DefaultTableModel dtm;
    private DefaultComboBoxModel defCBoxModelTag;
    private DefaultComboBoxModel defCBoxModelTagFilter;

    private List<Tag> tags;

    public FAQ_UI() {
        qao = new QuestionAnswerOperation();
        to = new TagOperation();

        initButtonsActionListeners();
        initTable();
        initTagComboBox();
        initTextFields();
    }

    private void initTextFields() {
        FilterTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                recreatingFilters();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                recreatingFilters();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                recreatingFilters();
            }
        });
    }

    private void recreatingFilters() {
        List<RowFilter<DefaultTableModel,Object>> filters = new ArrayList<RowFilter<DefaultTableModel,Object>>(2);
        filters.add(RowFilter.regexFilter(FilterTextField.getText(), 0));
        if (!TagFilterComboBox.getSelectedItem().toString().equals("empty")) {
            filters.add(RowFilter.regexFilter(TagFilterComboBox.getSelectedItem().toString(), 3));
        }
        RowFilter<DefaultTableModel,Object> fooBarFilter = RowFilter.andFilter(filters);

        rowSorter.setRowFilter(fooBarFilter);
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
    }

    public void initTable() {
        dtm = new DefaultTableModel();
        dtm.setColumnIdentifiers(new String[] {"Question", "Answer", "Answer Code", "Tag"});

        List<QuestionAnswer> data = qao.findAllData();

        for (QuestionAnswer curr_elem : data) {
            String currTagName = to.findTagNameById(curr_elem.getTagId());
            dtm.addRow(new String[] {curr_elem.getQuestionText(), curr_elem.getAnswerText(), curr_elem.getAnswerExampleCode(), currTagName});
        }

        rowSorter = new TableRowSorter<DefaultTableModel>(dtm);
        AnswersTable.setModel(dtm);
        AnswersTable.setRowSorter(rowSorter);

        ListSelectionModel rowSelectionModel = AnswersTable.getSelectionModel();
        rowSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int viewRow = AnswersTable.getSelectedRow();
                if (viewRow >= 0) {

                    int modelRow = AnswersTable.convertRowIndexToModel(viewRow);

                    System.out.println("modelRow: " + modelRow);

                    try {
                        questionText = dtm.getValueAt(modelRow, 0).toString();
                    } catch (NullPointerException ex) {
                        questionText = new String();
                    }

                    try {
                        answerText = dtm.getValueAt(modelRow, 1).toString();
                    } catch (NullPointerException ex) {
                        answerText = new String();
                    }

                    try {
                        answerCodeText = dtm.getValueAt(modelRow, 2).toString();
                    } catch (NullPointerException ex) {
                        answerCodeText = new String();
                    }

                    QuestionTextArea.setText(questionText);
                    AnswerTextArea.setText(answerText);
                    AnswerCodeTextArea.setText(answerCodeText);
                }
            }
        });
    }

    public void initTagComboBox() {
        defCBoxModelTag = new DefaultComboBoxModel();
        defCBoxModelTagFilter = new DefaultComboBoxModel();

        tags = to.findAllTags();

        for (Tag currentTag : tags) {
            defCBoxModelTag.addElement(currentTag.getTagName());
            defCBoxModelTagFilter.addElement(currentTag.getTagName());
        }

        defCBoxModelTagFilter.addElement("empty");

        TagComboBox.setModel(defCBoxModelTag);
        TagFilterComboBox.setModel(defCBoxModelTagFilter);

        TagFilterComboBox.setSelectedIndex(defCBoxModelTagFilter.getIndexOf("empty"));

        TagFilterComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recreatingFilters();
            }
        });
    }
}
