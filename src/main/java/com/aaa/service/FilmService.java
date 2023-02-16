package com.aaa.service;

import com.aaa.dao.daoImpl.filmDaoImpl;
import com.aaa.entily.FilmInfo;
import com.aaa.entily.TableData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @projectName: yangmeng
 * @package: com.aaa.service
 * @className: FilmService
 * @author: ym
 * @description: TODO
 * @date: 2023/2/15 16:00
 */
@Service
public class FilmService {
    @Resource
    filmDaoImpl studentDao;
    public int add(FilmInfo filmInfo){
        return studentDao.add(filmInfo);
    }
    public int delete(Integer id){
        return studentDao.delete(id);
    }
    public int update(FilmInfo filmInfo){
        return studentDao.update(filmInfo);
    }
    public TableData list(){
        List<Map<String, Object>> list = studentDao.list();
        System.out.println(list);
        return new TableData(list);
    }
    public TableData lists(){
        List<Map<String, Object>> list = studentDao.lists();
        System.out.println(list);
        return new TableData(list);
    }
}
