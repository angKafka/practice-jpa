package org.rdutta.springadvancejpa;

import org.rdutta.springadvancejpa.entity.*;
import org.rdutta.springadvancejpa.service.AppService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@SpringBootApplication
public class SpringAdvanceJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAdvanceJpaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppService appService) {
        return runner -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choose the options:\n1.Register your self\n2.Find a instructor\n3.Delete a instructor\n4.Instructor Details\n5.Delete Instructor Details\n6.Add Instructor With Course\n7.Find Course By Instructor\n8.Filter Join Fetch Way" +
                    "\n9.Delete Instructor Bi-directional\n10.Save course with reviews\n11.Filer Courser&Review\n12.Remove Course&Review" +
                    "\n13.Save Course Student\n14.Find Course&Students");
            int choice = scanner.nextInt();

            switch (choice){
                case 1:
                    saveInstructorRecord(appService);
                    break;
                    case 2:
                        findInstructorRecordById(appService);
                        break;
                case 3:
                    deleteInstructorRecordById(appService);
                    break;
                case 4:
                    findInstructorDetailsByInstructorId(appService);
                    break;
                case 5:
                    deleteInstructorDetailsByInstructorId(appService);
                    break;
                case 6:
                    addInstructorWithCourse(appService);
                    break;
                case 7:
                    findCourseByInstructorId(appService);
                    break;
                case 8:
                    findInstructorRecordByJoinFetch(appService);
                    break;
                case 9:
                    deleteInstructorRecord(appService);
                    break;
                case 10:
                    createCourseWithReview(appService);
                    break;
                case 11:
                    getFilteredCourseReviews(appService);
                    break;
                case 12:
                    removeCourseReviews(appService);
                    break;
                case 13:
                    createCourseStudent(appService);
                    break;
                case 14:
                    findCourseAndStudentByCourseID(appService);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            System.exit(0);
        };
    }

    private void findCourseAndStudentByCourseID(AppService appService) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter course_id you wish to find student added : ");
            UUID course_id = UUID.fromString(scanner.nextLine());
            Course course = appService.getCourseStudentByCourseId(course_id);
            System.out.println("Course: "+appService.getCourseStudentByCourseId(course_id).getTitle());

            for(int i=0; i < course.getStudents().size(); i++){
                System.out.println((i+1)+". "+"Student: "+course.getStudents().get(i).getFirstname());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createCourseStudent(AppService appService) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter the title: ");
            String courseTitle = scanner.nextLine();
            System.out.println("Please enter student firstname: ");
            String firstname = scanner.nextLine();
            System.out.println("Please enter student lastname: ");
            String lastname = scanner.nextLine();
            System.out.println("Please enter student email: ");
            String email = scanner.nextLine();

            Course course = new Course(courseTitle);
            Student student = new Student(firstname,lastname, email);
            course.addStudent(student);

            appService.saveCourseStudent(course);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Successfully added student to the course");
    }

    private void removeCourseReviews(AppService appService) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter course_id you wish to delete: ");
            UUID course_id = UUID.fromString(scanner.nextLine());

            appService.deleteCourseReview(course_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Successfully deleted course & review");
    }

    private void getFilteredCourseReviews(AppService appService) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter course id: ");
            UUID course_id = UUID.fromString(scanner.nextLine());
            System.out.println("Course: "+appService.getCourseReview(course_id).getTitle());
            Course course = appService.getCourseReview(course_id);
            for(int i=0; i < course.getReviews().size(); i++){
                System.out.println("Comments: \n"+(i+1)+". "+appService.getCourseReview(course_id).getReviews().get(i).getComment());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createCourseWithReview(AppService appService) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter title: ");
            String title = scanner.nextLine();
            System.out.println("Please enter comment: ");
            String comment = scanner.nextLine();

            Course course = new Course(title);
            Review review = new Review(comment);
            course.addReview(review);

            appService.saveCourseWithReviews(course);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void findCourseByInstructorId(AppService appService) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter instructor id you wish to find course: ");
            UUID instructor_id = UUID.fromString(scanner.nextLine());

            Instructor instructor = appService.getInstructorByInstructorId(instructor_id);
            System.out.println(instructor);

            List<Course> courses = appService.getCoursesByInstructorId(instructor_id);
            instructor.setCourses(courses);
            System.out.println(instructor.getCourses());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addInstructorWithCourse(AppService appService){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter your firstname: ");
            String firstName = scanner.nextLine();
            System.out.println("Please enter your lastname: ");
            String lastName = scanner.nextLine();
            System.out.println("Please enter your email: ");
            String email = scanner.nextLine();
            System.out.println("Please enter your youtube_channel: ");
            String youtube = scanner.nextLine();
            System.out.println("Please enter your hobby: ");
            String hobby = scanner.nextLine();
            System.out.println("Please enter your course name: ");
            String courseName = scanner.nextLine();

            Instructor instructor = new Instructor(firstName, lastName, email);
            instructor.setInstructorDetails(new InstructorDetails(youtube, hobby));
            Course course = new Course(courseName);
            instructor.add(course);
            scanner.close();
            appService.createInstructor(instructor);

        } catch (Exception e) {
            e.printStackTrace();

        }

        System.out.println("Successfully saved your record!");
    }
    private void deleteInstructorDetailsByInstructorId(AppService appService) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter instructor details id you wish to delete: ");
            UUID instructor_details_id = UUID.fromString(scanner.nextLine());

            appService.deleteInstructorDetailsByInstructorId(instructor_details_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Successfully deleted instructor");
    }

    private void findInstructorDetailsByInstructorId(AppService appService) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter instructor detail id you wish to find: ");
            UUID instructor_details_id = UUID.fromString(scanner.nextLine());

            InstructorDetails instructorDetails = appService.getInstructorDetailsById(instructor_details_id);
            System.out.println(instructorDetails);
            System.out.println(instructorDetails.getInstructor());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void deleteInstructorRecordById(AppService appService) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter instructor id you wish to delete: ");
            UUID instructor_id = UUID.fromString(scanner.nextLine());

            appService.removeInstructorById(instructor_id);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Successfully deleted instructor");
    }


    public void saveInstructorRecord(AppService appService){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter your firstname: ");
            String firstName = scanner.nextLine();
            System.out.println("Please enter your lastname: ");
            String lastName = scanner.nextLine();
            System.out.println("Please enter your email: ");
            String email = scanner.nextLine();
            System.out.println("Please enter your youtube_channel: ");
            String youtube = scanner.nextLine();
            System.out.println("Please enter your hobby: ");
            String hobby = scanner.nextLine();


            Instructor instructor = new Instructor(firstName, lastName, email);
            instructor.setInstructorDetails(new InstructorDetails(youtube, hobby));
            scanner.close();
            appService.createInstructor(instructor);

        } catch (Exception e) {
            e.printStackTrace();

        }

        System.out.println("Successfully saved your record!");
    }

    public void  findInstructorRecordById(AppService appService){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter instructor id you wish to find: ");
            UUID instructor_id = UUID.fromString(scanner.nextLine());

           Instructor instructor = appService.getInstructorByInstructorId(instructor_id);
            System.out.println(instructor);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void  findInstructorRecordByJoinFetch(AppService appService){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter instructor id you wish to find using join fetch: ");
            UUID instructor_id = UUID.fromString(scanner.nextLine());

            Instructor instructor = appService.getInstructorByInstructorIdJoinFetch(instructor_id);
            System.out.println(instructor);
            System.out.println(instructor.getCourses());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteInstructorRecord(AppService appService){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please enter instructor id you wish to delete: ");
            UUID instructor_id = UUID.fromString(scanner.nextLine());
            appService.deleteInstructorById(instructor_id);
        }
    }
}
