package com.careerfocus.controller;

import com.careerfocus.constants.UIMessages;
import com.careerfocus.entity.Category;
import com.careerfocus.service.CategoryService;
import com.careerfocus.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService service;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response getAllCategories(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return Response.ok(service.getAllCategories()).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Response getCategory(HttpServletRequest request, HttpServletResponse response,
                                @PathVariable("id") Integer id) throws Exception {
        return Response.ok(service.getCategory(id)).build();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response saveVideoTutorial(HttpServletRequest request, HttpServletResponse response,
                                      @RequestBody Category category) throws Exception {
        return Response.ok(service.addCategory(category)).build();
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public Response editVideoTutorial(HttpServletRequest request, HttpServletResponse response,
                                      @RequestBody Category category) throws Exception {
        return Response.ok(service.addCategory(category)).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Response deleteVideoTutorial(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("id") Integer id) throws Exception {
        service.deleteCategory(id);
        return Response.ok().message(UIMessages.CATEGORY_DELETED).build();
    }

    @RequestMapping(value = "/page-size/{pageSize}/page-no/{pageNo}", method = RequestMethod.GET)
    public Response getTutorialsForPage(HttpServletRequest request, HttpServletResponse response,
                                        @PathVariable("pageSize") Integer pageSize, @PathVariable("pageNo") Integer pageNo) throws Exception {
        return Response.ok(service.getCategories(pageSize, pageNo)).build();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Response findCategories(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam("key") String key) throws Exception {
        return Response.ok(service.findCategory(key)).build();
    }

}
