package ui;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class TagOperation {

    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private EntityManagerFactory emf;
    private EntityManager em;

    public TagOperation() {
        emf = Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
    }

    public List findAllTags() {
        return em.createQuery("Select tag from Tag tag")
//                .setMaxResults(10)
                .getResultList();
    }
}
