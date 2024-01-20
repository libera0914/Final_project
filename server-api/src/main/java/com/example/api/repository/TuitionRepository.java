package com.example.api.repository;

import com.example.api.model.entity.Tuition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Date: 2023/3/5 14:01
 * @Description:
 */
@Repository
public interface TuitionRepository extends JpaRepository<Tuition,String> {

    @Query("select a from Tuition as a where a.courseTitle like CONCAT('%',?1,'%') or a.courseCode like CONCAT('%',?1,'%')")
    Page<Tuition> findAllByPage(String keyWord, Pageable pageable);

    @Query("select a from Tuition as a where a.courseTitle like CONCAT('%',?1,'%') or a.courseCode like CONCAT('%',?1,'%')")
    List<Tuition> findAllByTitle(String keyWord);

    @Query("select a from Tuition as a where a.degree = ?1 and a.from = ?2  and a.types = ?3 and (a.courseTitle like CONCAT('%',?4,'%') or a.courseCode like CONCAT('%',?4,'%'))")
    List<Tuition> findAllByDegree(String degree,String from,String types,String keyWord);

    @Query("select a from Tuition as a where a.degree = ?1 and a.from = ?2  and a.types = ?3 and (a.courseTitle like CONCAT('%',?4,'%') or a.courseCode like CONCAT('%',?4,'%'))")
    Page<Tuition> findAllByDegreePage(String degree,String from,String types,String keyWord, Pageable pageable);




    @Query("select a from Tuition as a where a.from = ?1")
    List<Tuition> findAllByFrom(String from);

    @Query("select a from Tuition as a where a.from = ?1")
    Page<Tuition> findAllByFromPage(String from, Pageable pageable);

    @Query("select a from Tuition as a where a.types = ?1")
    List<Tuition> findAllByTypes(String types);

    @Query("select a from Tuition as a where a.types = ?1")
    Page<Tuition> findAllByTypesPage(String types, Pageable pageable);
    @Query("select a from Tuition as a where a.degree = ?1 and a.from = ?2  and a.types = ?3 and (a.courseTitle like CONCAT('%',?4,'%') or a.courseCode like CONCAT('%',?4,'%'))" +
            " and a.courseName in (?5)")
    List<Tuition> findAllByTitleName(String degree, String from, String types, String keyWord, String[] courseName);

    @Query("select a from Tuition as a where a.degree = ?1 and a.from = ?2  and a.types = ?3 and (a.courseTitle like CONCAT('%',?4,'%') or a.courseCode like CONCAT('%',?4,'%'))" +
            " and a.courseName in (?5)")
    Page<Tuition> findAllByTitleNamePage(String degree, String from, String types, String keyWord, String[] courseName, Pageable pageable);
    @Query("select a from Tuition as a where a.degree = ?1 and a.from = ?2  and a.types = ?3 and (a.courseTitle like CONCAT('%',?4,'%') or a.courseCode like CONCAT('%',?4,'%'))" +
            " and a.courseTitle in (?5)")
    List<Tuition> findAllByTitles(String degree, String from, String types, String keyWord, String[] courseTitles);
    @Query("select a from Tuition as a where a.degree = ?1 and a.from = ?2  and a.types = ?3 and (a.courseTitle like CONCAT('%',?4,'%') or a.courseCode like CONCAT('%',?4,'%'))" +
            " and a.courseTitle in (?5)")
    Page<Tuition> findAllByTitlesPage(String degree, String from, String types, String keyWord, String[] courseTitles, Pageable pageable);

    @Query("select a from Tuition as a where a.degree = ?1 and a.from = ?2  and a.types = ?3 and (a.courseTitle like CONCAT('%',?4,'%') or a.courseCode like CONCAT('%',?4,'%'))" +
            " and a.courseTitle in (?5) and a.courseName in (?6)")
    List<Tuition> findAllByTitlesAndName(String degree, String from, String types, String keyWord, String[] courseTitles, String[] courseName);
    @Query("select a from Tuition as a where a.degree = ?1 and a.from = ?2  and a.types = ?3 and (a.courseTitle like CONCAT('%',?4,'%') or a.courseCode like CONCAT('%',?4,'%'))" +
            " and a.courseTitle in (?5) and a.courseName in (?6)")
    Page<Tuition> findAllByTitlesPageAndName(String degree, String from, String types, String keyWord, String[] courseTitles, String[] courseName, Pageable pageable);

    @Query(value="select a.course_name  from tuition a where  a.degree = :degree and a.from = :from  and a.types = :types group by a.course_name", nativeQuery = true)
    List<Object[]> findCourse(@Param("degree") String degree, @Param("from") String from, @Param("types") String types);

    @Query(value="select a.course_title,a.course_name from tuition a where  a.degree = :degree and a.from = :from  and a.types = :types  group by a.course_title,a.course_name", nativeQuery = true)
    List<Map<String, Object>> findMajor(@Param("degree") String degree, @Param("from") String from, @Param("types") String types);
}
