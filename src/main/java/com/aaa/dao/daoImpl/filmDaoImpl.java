package com.aaa.dao.daoImpl;

import com.aaa.dao.filmDao;
import com.aaa.entily.FilmInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @projectName: yangmeng
 * @package: com.aaa.dao.daoImpl
 * @className: filmDaoImpl
 * @author: ym
 * @description: TODO
 * @date: 2023/2/15 15:55
 */
@Repository
public class filmDaoImpl implements filmDao {
    @Resource
    JdbcTemplate jdbcTemplate;
    @Override
    public int add(FilmInfo filmInfo) {
        String sql="insert into film_info (film_name, film_type, film_duration, release_date, film_remark) values (?,?,?,?,?);";
        return jdbcTemplate.update(sql,filmInfo.getFilmName(),filmInfo.getFilmType(),filmInfo.getFilmDuration(),filmInfo.getReleaseDate(),filmInfo.getFilmRemark());
    }

    @Override
    public List<Map<String, Object>> list() {
        String sql="select ti.type_name as typeName,f.film_id as filmId,f.film_name as filmName,f.film_type as filmType,f.film_duration as filmDuration,f.release_date as releaseDate,f.film_remark as filmRemark from film_info f left join type_info ti on ti.type_id = f.film_type";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<Map<String, Object>> lists() {
        String sql="select * from type_info";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public int delete(Integer id) {
        String sql="delete from film_info where film_id=?";
        return jdbcTemplate.update(sql,id);
    }

    @Override
    public int update(FilmInfo filmInfo) {
        String sql="update film_info set film_name = ?,film_type=?,film_duration=?,release_date=?,film_remark=? where film_id=?";
        return jdbcTemplate.update(sql,filmInfo.getFilmName(),filmInfo.getFilmType(),filmInfo.getFilmDuration(),filmInfo.getReleaseDate(),filmInfo.getFilmRemark(),filmInfo.getFilmId());
    }
}
