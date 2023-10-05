package ui;

import entity.QuestionAnswer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class QuestionAnswerOperation {

    private String question;
    private String answer;
    private String codeText;

    private EntityManagerFactory emf;
    private EntityManager em;

    public QuestionAnswerOperation(String question, String answer, String codeText) {
        this.question = question;
        this.answer = answer;
        this.codeText = codeText;

        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public void performInsertNewFAQ() {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setQuestionText(question);
        questionAnswer.setAnswerText(answer);

        String example_code_several_lines = codeText;

        questionAnswer.setAnswerExampleCode(example_code_several_lines);

        em.getTransaction().begin();
        em.persist(questionAnswer);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public List findWithName(String name) {
        return em.createQuery("Select faq from QuestionAnswer faq where faq.answerText LIKE :ansName")
                .setParameter("ansName", name)
                .setMaxResults(10)
                .getResultList();
    }

}
