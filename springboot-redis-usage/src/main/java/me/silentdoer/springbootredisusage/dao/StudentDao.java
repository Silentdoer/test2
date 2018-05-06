package me.silentdoer.springbootredisusage.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import me.silentdoer.springbootredisusage.model.Student;

public interface StudentDao {

    int insert(@Param("pojo") Student pojo);

    int insertList(@Param("pojos") List< Student> pojo);

    List<Student> select(@Param("pojo") Student pojo);

    int update(@Param("pojo") Student pojo);

}
