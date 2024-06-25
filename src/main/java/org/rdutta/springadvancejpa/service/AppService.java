package org.rdutta.springadvancejpa.service;

import org.rdutta.springadvancejpa.entity.Course;
import org.rdutta.springadvancejpa.entity.Instructor;
import org.rdutta.springadvancejpa.entity.InstructorDetails;
import org.rdutta.springadvancejpa.entity.Student;

import java.util.List;
import java.util.UUID;

public interface AppService {
    void createInstructor(Instructor instructor);
    Instructor getInstructorByInstructorId(UUID instructorId);
    void removeInstructorById(UUID instructorId);
    InstructorDetails getInstructorDetailsById(UUID instructorDetailId);
    void deleteInstructorDetailsByInstructorId(UUID instructorId);
    List<Course> getCoursesByInstructorId(UUID instructorId);
    Instructor getInstructorByInstructorIdJoinFetch(UUID instructorId);
    // Update Course and Instructor
    void deleteInstructorById(UUID instructorId);
    void saveCourseWithReviews(Course course);
    Course getCourseReview(UUID courseId);
    void deleteCourseReview(UUID courseId);
    void saveCourseStudent(Course course);
    Course getCourseStudentByCourseId(UUID courseId);
}
