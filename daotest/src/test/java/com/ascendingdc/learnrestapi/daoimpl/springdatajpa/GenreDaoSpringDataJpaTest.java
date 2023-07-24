package com.ascendingdc.learnrestapi.daoimpl.springdatajpa;

import com.ascendingdc.learnrestapi.dao.jdbc.GenreDao;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GenreDaoSpringDataJpaTest {
    private Logger logger = (Logger) LoggerFactory.getLogger(GenreDaoSpringDataJpaTest.class);

    @Autowired
    private GenreDao genreDao;
}
