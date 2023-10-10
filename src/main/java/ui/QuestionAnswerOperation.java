package ui;

import entity.QuestionAnswer;
import jakarta.persistence.*;

import java.util.List;

public class QuestionAnswerOperation {

    private String question;
    private String answer;
    private String codeText;

    private Long tagId;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCodeText() {
        return codeText;
    }

    public void setCodeText(String codeText) {
        this.codeText = codeText;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    private EntityManagerFactory emf;
    private EntityManager em;

    @Deprecated
    public QuestionAnswerOperation(String question, String answer, String codeText) {
        this.question = question;
        this.answer = answer;
        this.codeText = codeText;

        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public QuestionAnswerOperation() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public void performInsertNewFAQ(String question, String answer, String codeText, Long tagId) {
        this.question = question;
        this.answer = answer;
        this.codeText = codeText;
        this.tagId = tagId;

        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setQuestionText(question);
        questionAnswer.setAnswerText(answer);

        String example_code_several_lines = codeText;

        questionAnswer.setAnswerExampleCode(example_code_several_lines);
        questionAnswer.setTagId(tagId);

        em.getTransaction().begin();
        em.persist(questionAnswer);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public List findWithName(String name) {
        return em.createQuery("Select faq from QuestionAnswer faq where faq.questionText LIKE :qName")
                .setParameter("qName", name)
                .getResultList();
    }

    public List findAllData() {
        return em.createQuery("Select faq from QuestionAnswer faq")
                .getResultList();
    }

    public void storedFunctionExample() {
        StoredProcedureQuery spq = em.createStoredProcedureQuery("JPA_Test.records_number");

        spq.registerStoredProcedureParameter(1, Integer.class, ParameterMode.OUT);

        boolean result = spq.execute();

        int countValue = (Integer) spq.getOutputParameterValue(1);
        System.out.println("countValue: " + countValue);
    }

}
