package ui;

import entity.QuestionAnswer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class QuestionAnswerOperation {

    private String question;
    private String answer;
    private String codeText;

    public QuestionAnswerOperation(String question, String answer, String codeText) {
        this.question = question;
        this.answer = answer;
        this.codeText = codeText;
    }

    public void performInsertNewFAQ() {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setQuestionText(question);
        questionAnswer.setAnswerText(answer);

        String example_code_several_lines = codeText;

        questionAnswer.setAnswerExampleCode(example_code_several_lines);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(questionAnswer);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
