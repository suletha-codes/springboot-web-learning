package com.example.springboot_web_learning.repository;

import com.example.springboot_web_learning.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    Optional<Student> findByName(String name);
    List<Student> findByNameContaining(String name);
    Page<Student> findAll(Pageable pageable);
    List<Student> findByAge(int age);
    List<Student> findByAgeGreaterThan(int age);
    List<Student> findByAgeLessThan(int age);
    List<Student> findByAgeGreaterThanEqual(int age);
    List<Student> findByAgeLessThanEqual(int age);
    List<Student> findByAgeBetween(int minAge, int maxAge);
   // Optional<Student> findByNameAndAge(String name, int age);
    List<Student> findByNameAndAge(String name, int age);
    List<Student> findByNameOrAge(String name, int age);
    List<Student> findByNameContainingIgnoreCase(String name);
    List<Student> findByNameStartingWith(String prefix);
    List<Student> findByNameEndingWith(String suffix);
    List<Student> findAllByOrderByAgeAsc();
    List<Student> findAllByOrderByAgeDesc();
    long  countByAge(int age);
    long countByAgeGreaterThan(int age);
    boolean existsByName(String name);
    boolean existsByAge(int age);
    long deleteByAge(int age);

    //Custom JPQL Queries

    @Query("SELECT DISTINCT s.name FROM Student s")
    List<String> findDistinctStudentsName();

    @Query("SELECT s FROM Student s WHERE s.name LIKE CONCAT(:prefix, '%')")
    List<Student> findNamesStartingWith(@Param("prefix") String prefix);

    @Query("SELECT s FROM Student s ORDER BY s.age ASC")
    List<Student> findStudentsOrderByAgeAscending();

    @Query("SELECT s FROM Student s ORDER BY s.age DESC")
    List<Student> findStudentsOrderByAgeDescending();

    @Query("SELECT s FROM Student s WHERE s.name = :name AND s.age > :age")
    List<Student> findStudentsByNameAndAgeGreaterThan(@Param("name") String name, @Param("age") int age);

    @Query("SELECT s FROM Student s WHERE s.name = :name OR s.age > :age")
    List<Student> findStudentsByNameOrAgeGreaterThan(@Param("name") String name, @Param("age") int age);

    @Query("SELECT s FROM Student s WHERE (s.name = :name1 OR s.name = :name2) AND s.age < :age")
    List<Student> findStudentsByNameAndAgeLessThan(@Param("name1") String name1, @Param("name2") String name2, @Param("age") int age);

    @Query("SELECT s FROM Student s WHERE s.name IN :names")
    List<Student> findByStudentNames(@Param("names") List<String> names);

    @Query("SELECT s FROM Student s WHERE s.age BETWEEN :minAge AND :maxAge")
    List<Student> findByStudentsAgeBetween(@Param("minAge") int minAge, @Param("maxAge") int maxAge);

    @Query("SELECT MAX(s.age) FROM Student s")
    Integer findMaximumAge();

    @Query("SELECT MIN(s.age) FROM Student s")
    Integer findMinimumAge();

    @Query("SELECT AVG(s.age) FROM Student s")
    Double findAverageAge();

    @Query("SELECT COUNT(s) FROM Student s")
    Long countAllStudents();

    @Query("SELECT s.age, COUNT(s) FROM Student s GROUP BY s.age")
    List<Object[]> countStudentGroupByAge();

    @Query("SELECT s.age, COUNT(s) FROM Student s " + "GROUP BY s.age HAVING COUNT(s) > 2")
    List<Object[]> countStudentHaving();

    @Query("SELECT SUM(s.age) FROM Student s")
    Long findSumOfAge();

}
