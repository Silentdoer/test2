package me.silentdoer.springbootredisusage.dao;

import me.silentdoer.springbootredisusage.model.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import me.silentdoer.springbootredisusage.dao.StudentDao;

//@Service
public class StudentService {

    // TODO @Resource确实是required=true
    @Resource
    private StudentDao studentDao;

    public int insert(Student pojo){
        return studentDao.insert(pojo);
    }

    public int insertList(List< Student> pojos){
        return studentDao.insertList(pojos);
    }

    public List<Student> select(Student pojo){
        return studentDao.select(pojo);
    }

    public int update(Student pojo){
        return studentDao.update(pojo);
    }

}
