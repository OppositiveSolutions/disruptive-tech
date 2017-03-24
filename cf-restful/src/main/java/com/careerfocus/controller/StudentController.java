package com.careerfocus.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@RestController
@RequestMapping("/students")
public class StudentController {

	private final Logger logger = Logger.getLogger(this.getClass().getSimpleName().trim());

	@Autowired
	StudentService studentService;
	
//	@Autowired
//	Scheduler scheduler;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection<Student> getAllStudents(HttpServletRequest request, HttpServletResponse response) {

		logger.setLevel(Level.DEBUG);

		logger.info("THIS IS A LOG INFO LOGGING");
		logger.debug("THIS IS A LOG DEBUG LOGGING");
		logger.error("THIS IS A LOG ERROR LOGGING");
		logger.trace("THIS IS A LOG TRACE LOGGING");
		logger.warn("THIS IS A LOG WARN LOGGING");

//		JobDetail detail = JobBuilder.newJob().ofType(JobRunner.class).withIdentity("jobName123456", "jobGroup123456")
//				.requestRecovery(true)
//				.storeDurably().withDescription("Invoke Sample Job service...").build();
//		
//		String cronExpr = "0/10 * * * * ?";
//		try {
//			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger42", "group52")
//					.withSchedule(CronScheduleBuilder.cronSchedule(cronExpr)).forJob("jobName123456", "jobGroup123456")
//					.build();
//			if (!scheduler.isStarted()) {
//				logger.info("SCHEDULER NOT STARTED... STARING....");
//				scheduler.start();
//			}
//			scheduler.scheduleJob(detail, trigger);
//		} catch (SchedulerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		// CronTriggerFactoryBean bean =
		// SchedulerConfig.createCronTrigger(detail, cronExpr);

		// bean.
		
	

//		return studentService.getAllStudents();
		
		return new ArrayList<Student>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{
				add(new Student(1, "john", 101));
				add(new Student(2, "will", 102));
				add(new Student(3, "jack", 103));
				add(new Student(4, "bill", 104));
			}
		};
	}
}
