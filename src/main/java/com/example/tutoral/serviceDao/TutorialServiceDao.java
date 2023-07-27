package com.example.tutoral.serviceDao;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.example.tutoral.dto.Tutorial;

@Service
@EnableCaching
public class TutorialServiceDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(TutorialServiceDao.class);       
    private  List<Tutorial> tutorials;


    @PostConstruct
    public void loadDefaults (){

    this.tutorials =   Arrays.asList( 
        new Tutorial().id(20).desc(" Antes de universio ").isPublished(true) ,
        new Tutorial().id(10).desc(" Antes de volumen ").isPublished(false)  );
        LOGGER.info(" *  OK Tutorials Loaded {}*" , this.tutorials.toString());

    }
    
    
  @Cacheable( value =  "tutorials" )
  public List<Tutorial> findAll() {
    
    return this.tutorials;
  }

  @Cacheable(value =  "tutorials")
  public List<Tutorial> findByTitleContaining(String title) {
    
    return  this.tutorials.stream().filter( 
              item ->  item.getDescripcion().contains(title)  )
                .collect(Collectors.toList()) ;
  }

  @Cacheable("tutorial")
  public Optional<Tutorial> findById(long id) {
    return this.tutorials.stream().filter ( item -> item.getId() == id).findFirst();
  }

  @Cacheable("published_tutorials")
  public List<Tutorial> findByPublished(boolean isPublished) {
    return this.tutorials.stream().filter ( item -> item.isPublished()).collect(Collectors.toList())  ;
  }

}
