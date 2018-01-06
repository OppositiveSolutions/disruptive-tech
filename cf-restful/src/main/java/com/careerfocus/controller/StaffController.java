package com.careerfocus.controller;

import com.careerfocus.model.request.AddStaffVO;
import com.careerfocus.service.StaffService;
import com.careerfocus.util.response.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    StaffService staffService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Response addStaff(HttpServletRequest request, HttpServletResponse response,
                               @RequestBody AddStaffVO staff) throws Exception {
        return staffService.addStaff(staff);
    }

    @RequestMapping(value = "/pageSize/{pageSize}/pageNo/{pageNo}", method = RequestMethod.GET)
    public Response getStaff(HttpServletRequest request, HttpServletResponse response,
                               @PathVariable("pageSize") int pageSize, @PathVariable("pageNo") int pageNo) throws Exception {
        return Response.ok(staffService.getStaff(pageSize, pageNo)).build();
    }

    @RequestMapping(value = "/pageSize/{pageSize}/pageNo/{pageNo}/search", method = RequestMethod.GET)
    public Response searchByName(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "key") String key, @PathVariable("pageSize") int pageSize,
                                 @PathVariable("pageNo") int pageNo) throws Exception {
        return Response.ok(staffService.findStaffsByName(key, pageSize, pageNo)).build();
    }

}
