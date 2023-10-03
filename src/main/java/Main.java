import entity.Message;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        Message message = new Message();
        message.setText("And again, JPA!");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(message);
        System.out.println("Em: " + em);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
}
