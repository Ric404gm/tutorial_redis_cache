package com.example.tutoral;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.tutoral.serviceDao.TutorialServiceDao;

@SpringBootTest
class TutoralApplicationTests {

	@Autowired
	public TutorialServiceDao tutorialServiceDao;  

	@Test
	void contextLoads() {


		assertTrue( !tutorialServiceDao.findAll().isEmpty() );
		
		assertNotNull( tutorialServiceDao.findByTitleContaining("volumen") );
		assertNotNull( tutorialServiceDao.findByPublished(false) );

	}

}
