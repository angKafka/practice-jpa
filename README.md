# Educational Platform Database

This repository contains the SQL schema for an educational platform, designed to manage instructors, courses, students, and reviews. The schema ensures data integrity and enforces unique constraints where necessary.

## Schema Overview

### Tables

1. **instructor_details**
    - **instructor_details_id** (uuid, Primary Key)
    - **youtube_channel** (VARCHAR(50))
    - **hobby** (VARCHAR(50))

2. **instructor**
    - **instructor_id** (uuid, Primary Key)
    - **firstname** (VARCHAR(50))
    - **lastname** (VARCHAR(50))
    - **email** (VARCHAR(150), Unique)
    - **instructor_details_id** (uuid, Foreign Key)

3. **course**
    - **course_id** (uuid, Primary Key)
    - **title** (VARCHAR(150), Unique)
    - **instructor_id** (uuid, Foreign Key)

4. **review**
    - **review_id** (uuid, Primary Key)
    - **comment** (VARCHAR(300))
    - **course_id** (uuid, Foreign Key)

5. **student**
    - **student_id** (uuid, Primary Key)
    - **firstname** (VARCHAR(50))
    - **lastname** (VARCHAR(50))
    - **email** (VARCHAR(150), Unique)

6. **course_student**
    - **course_id** (uuid, Foreign Key)
    - **student_id** (uuid, Foreign Key)

### SQL Scripts

#### Create Tables

```sql
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS instructor_details(
    instructor_details_id uuid PRIMARY KEY,
    youtube_channel VARCHAR(50),
    hobby VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS instructor(
    instructor_id uuid PRIMARY KEY,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    email VARCHAR(150),
    instructor_details_id uuid,
    FOREIGN KEY (instructor_details_id) REFERENCES instructor_details(instructor_details_id)
);

CREATE TABLE IF NOT EXISTS course(
    course_id uuid PRIMARY KEY,
    title VARCHAR(150),
    instructor_id uuid,
    FOREIGN KEY (instructor_id) REFERENCES instructor(instructor_id)
);

CREATE TABLE IF NOT EXISTS review(
    review_id uuid PRIMARY KEY,
    comment VARCHAR(300),
    course_id uuid,
    FOREIGN KEY (course_id) REFERENCES course(course_id)
);

CREATE TABLE IF NOT EXISTS student(
    student_id uuid PRIMARY KEY,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    email VARCHAR(150)
);

CREATE TABLE IF NOT EXISTS course_student(
    course_id uuid,
    student_id uuid,
    FOREIGN KEY (course_id) REFERENCES course(course_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id)
);

ALTER TABLE instructor ADD CONSTRAINT email_uniqueness_id UNIQUE (email);
ALTER TABLE student ADD CONSTRAINT email_uniqueness_idx UNIQUE (email);
ALTER TABLE course ADD CONSTRAINT title_uniqueness_idxx UNIQUE (title);
