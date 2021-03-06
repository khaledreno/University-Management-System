package com.khaled.university_management.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    @NotBlank
    private String first_name;

    @Column(name = "last_name")
    @NotBlank
    private String last_name;

    @Column(name = "number_active_subjects")
    private int number_active_subjects;

    @Column(name = "email")
    @NotBlank
    private String email;

    //Relation between teacher and students
    @ManyToOne(cascade = {CascadeType.DETACH , CascadeType.MERGE , CascadeType.REFRESH , CascadeType.PERSIST})
    @JoinColumn(name = "instructor_id")
    private Teacher instructor;

    //Relation between students and courses
    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="student_course",
            joinColumns=@JoinColumn(name="student_id"),
            inverseJoinColumns=@JoinColumn(name="course_id")
    )
    private Set<Course> courses = new HashSet<>();

    public Student() {
    }

    public Student(String first_name, String last_name, int number_active_subjects, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.number_active_subjects = number_active_subjects;
        this.email = email+"@uni.com";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getNumber_active_subjects() {
        return number_active_subjects;
    }

    public void setNumber_active_subjects(int number_active_subjects) {
        this.number_active_subjects = number_active_subjects;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email+"@uni.com";
    }

    public Teacher getInstructor() {
        return instructor;
    }

    public void setInstructor(Teacher instructor) {
        this.instructor = instructor;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", number_active_subjects=" + number_active_subjects +
                ", email='" + email + '\'' +
                ", instructor=" + instructor +
                ", courses=" + courses +
                '}';
    }
}
