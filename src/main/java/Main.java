import entity.QuestionAnswer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setQuestionText("My fourth question!");
        questionAnswer.setAnswerText("My fourth answer with code!");

        String example_code_several_lines = "@Entity \n" +
                "public class Message {\n" +
                "   \n" +
                "public Message() {\n" +
                "   \n" +
                "}  \n"+
                "   \n" +
                "} \n";

        questionAnswer.setAnswerExampleCode(example_code_several_lines);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(questionAnswer);
        //System.out.println("Em: " + em);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
