package com.careerfocus.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.careerfocus.entity.UploadFile;

@Repository
public class FileUploadDAO {

//	@Autowired
//	private FactoryBean<SessionFactory> sessionFactory;
//
//	public FileUploadDAO() {
//	}
//
//	@Transactional
//	public void save(UploadFile uploadFile) {
//		try {
//			sessionFactory.getObject().getCurrentSession().save(uploadFile);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
