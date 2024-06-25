package org.rdutta.springadvancejpa.dao.implementation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.rdutta.springadvancejpa.dao.AppDAO;
import org.rdutta.springadvancejpa.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class AppDAOImpl implements AppDAO {
    private final EntityManager em;

    @Autowired
    public AppDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Instructor instructor) {
        em.persist(instructor);
    }

    @Override
    public Instructor findByInstructorId(UUID instructorId) {
        return em.find(Instructor.class, instructorId);
    }

    @Override
    public void delete(UUID instructorId) {
        Instructor instructor1 = em.find(Instructor.class, instructorId);
        em.remove(instructor1);
    }

    @Override
    public InstructorDetails findInstructorDetailsByInstructorId(UUID instructorDetailsId) {
        return em.find(InstructorDetails.class, instructorDetailsId);
    }

    @Override
    public void deleteInstructorDetailsByInstructorId(UUID instructorDetailsId) {
        InstructorDetails instructorDetails = em.find(InstructorDetails.class, instructorDetailsId);
        instructorDetails.getInstructor().setInstructorDetails(null);
        em.remove(instructorDetails);
    }

    @Override
    public List<Course> findCoursesByInstructorId(UUID instructorId) {
        TypedQuery<Course> query = em.createQuery("FROM Course WHERE instructor.instructor_id = :instructor_id", Course.class);
        query.setParameter("instructor_id", instructorId);
        List<Course> resultList = query.getResultList();

        return resultList;
    }

    @Override
    public Instructor findInstructorByInstructorIdJoinFetch(UUID instructorId) {
        TypedQuery<Instructor> query = em.createQuery("FROM Instructor AS i JOIN FETCH i.courses WHERE i.instructor_id = :instructorId", Instructor.class);
        query.setParameter("instructorId", instructorId);
        Instructor result = query.getSingleResult();
        return result;
    }


    @Override
    public void deleteInstructorByInstructorId(UUID instructorId) {
        Instructor instructor = em.find(Instructor.class, instructorId);

        List<Course> courses = instructor.getCourses();

        for (Course course : courses) {
            course.setInstructor(null);
        }
        em.remove(instructor);
    }

    @Override
    public void saveCourseWithReviews(Course course) {
        em.persist(course);
    }

    @Override
    public Course filerCourseAndReviews(UUID courseId) {
        TypedQuery<Course> query = em.createQuery("FROM Course c JOIN FETCH c.reviews  WHERE c.course_id = :courseId", Course.class);
        query.setParameter("courseId", courseId);
        Course resultList = query.getSingleResult();
       return resultList;
    }

    @Override
    public void deleteCourseWithReviews(UUID courseId) {
        Course course = em.find(Course.class, courseId);
        if (course != null) {
            List<Review> reviews = course.getReviews();
            for (Review review : reviews) {
                em.remove(review);
            }
            em.remove(course);
        } else {
            System.out.println("Course not found with ID: " + courseId);
        }
    }

    @Override
    public void saveCourseWithStudents(Course course) {
        em.persist(course);
    }

    @Override
    public Course filerCourseAndStudentsByCourseId(UUID courseId) {
        TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c JOIN FETCH c.students WHERE c.course_id = :course_id",Course.class);
        query.setParameter("course_id", courseId);

        // execute query
        Course course = query.getSingleResult();
        return course;
    }
}

