package org.rdutta.springadvancejpa.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "instructor_details")
public class InstructorDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "instructor_details_id")
    private UUID instructor_details_id;
    @Column(name = "youtube_channel")
    private String youtube_channel;
    @Column(name = "hobby")
    private String hobby;

//    @OneToOne(mappedBy = "instructorDetails", cascade = CascadeType.ALL)
//    private Instructor instructor;

    @OneToOne(mappedBy = "instructorDetails", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    private Instructor instructor;


    public InstructorDetails() {}

    public InstructorDetails(String youtube_channel, String hobby) {
        this.youtube_channel = youtube_channel;
        this.hobby = hobby;
    }

    public UUID getInstructor_details_id() {
        return instructor_details_id;
    }


    public void setInstructor_details_id(UUID instructor_details_id) {
        this.instructor_details_id = instructor_details_id;
    }

    public String getYoutube_channel() {
        return youtube_channel;
    }

    public void setYoutube_channel(String youtube_channel) {
        this.youtube_channel = youtube_channel;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "InstructorDetails{" +
                "instructor_details_id=" + instructor_details_id +
                ", youtube_channel='" + youtube_channel + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
