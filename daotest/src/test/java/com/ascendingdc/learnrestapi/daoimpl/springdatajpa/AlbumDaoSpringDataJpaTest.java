package com.ascendingdc.learnrestapi.daoimpl.springdatajpa;

import com.ascendingdc.learnrestapi.dao.jdbc.AlbumDao;
import com.ascendingdc.learnrestapi.entity.Album;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AlbumDaoSpringDataJpaTest {
    private Logger logger = (Logger) LoggerFactory.getLogger(AlbumDaoSpringDataJpaTest.class);

    @Autowired
    private AlbumDao albumDao;

}
