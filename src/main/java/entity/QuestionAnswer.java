package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Question_Answer")
public class QuestionAnswer {
    public QuestionAnswer() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "answer_text")
    private String answerText;

    @Column(name = "code_text")
    private String answerExampleCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getAnswerExampleCode() {
        return answerExampleCode;
    }

    public void setAnswerExampleCode(String answerExampleCode) {
        this.answerExampleCode = answerExampleCode;
    }
}
