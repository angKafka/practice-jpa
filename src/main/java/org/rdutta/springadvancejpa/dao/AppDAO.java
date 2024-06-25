package org.rdutta.springadvancejpa.dao;

import org.rdutta.springadvancejpa.entity.Course;
import org.rdutta.springadvancejpa.entity.Instructor;
import org.rdutta.springadvancejpa.entity.InstructorDetails;
import org.rdutta.springadvancejpa.entity.Student;

import java.util.List;
import java.util.UUID;

public interface AppDAO {
    void save(Instructor instructor);
    Instructor findByInstructorId(UUID instructorId);
    void delete(UUID instructorId);
    InstructorDetails findInstructorDetailsByInstructorId(UUID instructorDetailsId);
    void deleteInstructorDetailsByInstructorId(UUID instructorDetailsId);
    List<Course> findCoursesByInstructorId(UUID instructorId);
    Instructor findInstructorByInstructorIdJoinFetch(UUID instructorId);
    // Update Course and Instructor

    void deleteInstructorByInstructorId(UUID instructorId);
    void saveCourseWithReviews(Course course);
    Course filerCourseAndReviews(UUID courseId);
    void deleteCourseWithReviews(UUID courseId);

    void saveCourseWithStudents(Course course);
    Course filerCourseAndStudentsByCourseId(UUID courseId);
}
