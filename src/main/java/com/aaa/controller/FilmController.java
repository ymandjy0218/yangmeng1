package com.aaa.controller;

import com.aaa.entily.FilmInfo;
import com.aaa.entily.TableData;
import com.aaa.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @projectName: yangmeng
 * @package: com.aaa.controller
 * @className: FilmController
 * @author: ym
 * @description: TODO
 * @date: 2023/2/15 16:02
 */
@Controller
@RequestMapping("film")
public class FilmController {
    @Resource
    FilmService FilmService;
    @RequestMapping("add")
    @ResponseBody
    public int add(FilmInfo filmInfo){
         return FilmService.add(filmInfo);
     }
    @RequestMapping("delete")
    @ResponseBody
    public int delete(Integer id){
        return FilmService.delete(id);
    }
    @RequestMapping("update")
    @ResponseBody
    public int update(FilmInfo filmInfo){
        return FilmService.update(filmInfo);
    }
    @ResponseBody
    @RequestMapping("list")
    public TableData list(){
        return FilmService.list();
    }
    @ResponseBody
    @RequestMapping("lists")
    public TableData lists(){
        return FilmService.lists();
    }
}
