package com.cn.lucky.morning.model.web.controller;

import com.cn.lucky.morning.model.common.mvc.MvcResult;
import com.cn.lucky.morning.model.service.BookAnalysisService;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller
public class SearchController {
    @Resource
    private BookAnalysisService bookAnalysisService;
    @RequestMapping(method = RequestMethod.POST,value = "search")
    public String search(@Param("name") String name, Model model){
        MvcResult result = bookAnalysisService.searchByName(name);
        if (!result.isSuccess()){
            model.addAttribute("msg",result.getMessage());
            return "public/error";
        }
        model.addAttribute("list",result.getVal("list"));
        model.addAttribute("name",name);
        return "front/search/list";
    }
}
