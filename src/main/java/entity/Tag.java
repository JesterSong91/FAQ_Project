package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "FAQ_TAG")
public class Tag {
    public Tag() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Column(name = "tag_name")
    private String tagName;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }


}
