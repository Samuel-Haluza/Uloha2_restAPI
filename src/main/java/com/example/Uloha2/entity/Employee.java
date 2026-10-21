package com.example.Uloha2.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Meno je povinné")
    @Size(min = 2, max = 50, message = "Meno musí mať 2-50 znakov")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "Priezvisko je povinné")
    @Size(min = 2, max = 50, message = "Priezvisko musí mať 2-50 znakov")
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @NotNull(message = "Dátum narodenia je povinný")
    @Past(message = "Dátum narodenia musí byť v minulosti")
    @Column(name = "birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @NotBlank(message = "Email je povinný")
    @Email(message = "Neplatný email")
    @Pattern(
            regexp = "^[A-Za-z0-9.!#$%&'*+/=?^_`{|}~-]+@[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?)+$",
            message = "Email musí mať platný formát"
    )
    @Size(max = 100, message = "Email môže mať najviac 100 znakov")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "Telefón je povinný")
    @Pattern(regexp = "^\\+?[0-9 ()-]{7,20}$", message = "Telefón musí mať platný formát")
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;


    @NotBlank(message = "Pracovná pozícia je povinná")
    @Size(min = 2, max = 100, message = "Pracovná pozícia musí mať 2-100 znakov")
    @Column(name="job_title", nullable = false, length = 100)
    private String jobTitle;

    @NotNull(message = "Plat je povinný")
    @PositiveOrZero(message = "Plat nemôže byť záporný")
    @Column(name="salary", nullable = false)
    private Double salary;

    @NotNull(message = "Údaj o plnom úväzku je povinný")
    @Column(name="full_time", nullable = false)
    private Boolean fullTime;

    public Employee() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Boolean getFullTime() {
        return fullTime;
    }

    public void setFullTime(Boolean fullTime) {
        this.fullTime = fullTime;
    }
}
