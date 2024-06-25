package org.rdutta.springadvancejpa.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "review_id")
    private UUID review_id;
    @Column(name = "comment")
    private String comment;

    public Review() {}
    public Review(String comment) {
        this.comment = comment;
    }

    public UUID getReview_id() {
        return review_id;
    }

    public void setReview_id(UUID review_id) {
        this.review_id = review_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "review_id=" + review_id +
                ", comment='" + comment + '\'' +
                '}';
    }
}

