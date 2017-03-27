package com.careerfocus.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.careerfocus.model.Student;
import com.careerfocus.service.StudentService;
import com.sun.jersey.core.header.OutBoundHeaders;
import com.sun.jersey.core.spi.factory.ResponseImpl;

@RestController
@RequestMapping("/students")
public class StudentController {

	private final Logger logger = Logger.getLogger(this.getClass().getSimpleName().trim());

	@Autowired
	StudentService studentService;

	// @Autowired
	// Scheduler scheduler;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Response getAllStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);

		logger.info("THIS IS A LOG INFO LOGGING");
		logger.debug("THIS IS A LOG DEBUG LOGGING");
		logger.error("THIS IS A LOG ERROR LOGGING");
		logger.trace("THIS IS A LOG TRACE LOGGING");
		logger.warn("THIS IS A LOG WARN LOGGING");
		
	
		
		

		// JobDetail detail =
		// JobBuilder.newJob().ofType(JobRunner.class).withIdentity("jobName123456",
		// "jobGroup123456")
		// .requestRecovery(true)
		// .storeDurably().withDescription("Invoke Sample Job
		// service...").build();
		//
		// String cronExpr = "0/10 * * * * ?";
		// try {
		// Trigger trigger =
		// TriggerBuilder.newTrigger().withIdentity("trigger42", "group52")
		// .withSchedule(CronScheduleBuilder.cronSchedule(cronExpr)).forJob("jobName123456",
		// "jobGroup123456")
		// .build();
		// if (!scheduler.isStarted()) {
		// logger.info("SCHEDULER NOT STARTED... STARING....");
		// scheduler.start();
		// }
		// scheduler.scheduleJob(detail, trigger);
		// } catch (SchedulerException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// CronTriggerFactoryBean bean =
		// SchedulerConfig.createCronTrigger(detail, cronExpr);

		// bean.

		// return studentService.getAllStudents();

		ArrayList<Student> list = new ArrayList<Student>() {

			private static final long serialVersionUID = 1L;

			{
				add(new Student(1, "john", 101));
				add(new Student(2, "will", 102));
				add(new Student(3, "jack", 103));
				add(new Student(4, "bill", 104));
			}
		};

//		logger.info(Response.ok(list, MediaType.APPLICATION_JSON).build().);

		return Response.ok(list, MediaType.APPLICATION_JSON).build();

	}

}
