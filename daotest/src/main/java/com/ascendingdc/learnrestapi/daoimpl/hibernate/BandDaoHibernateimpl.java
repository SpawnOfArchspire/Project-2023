package com.ascendingdc.learnrestapi.daoimpl.hibernate;

import com.ascendingdc.learnrestapi.dao.jdbc.AlbumDao;
import com.ascendingdc.learnrestapi.dao.jdbc.BandDao;
import com.ascendingdc.learnrestapi.entity.Album;
import com.ascendingdc.learnrestapi.entity.Band;
import com.ascendingdc.learnrestapi.util.HQLStatementUtil;
import com.ascendingdc.learnrestapi.util.HibernateUtil;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class BandDaoHibernateimpl implements BandDao{
    private Logger logger = (Logger) LoggerFactory.getLogger(BandDaoHibernateimpl.class);

    @Override
    public Band save(Band band) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            transaction = (Transaction) session.beginTransaction();
            session.persist(band);
            transaction.commit();
        } catch (Exception e) {
            logger.error("fail to insert a band, error={}", e.getMessage());
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }

        return band;
    }

    @Override
    public Band update(Band band) {
        Transaction transaction = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            transaction = (Transaction) session.beginTransaction();
            session.saveOrUpdate(band);
            transaction.commit();

        } catch (Exception e) {
            logger.error("fail to insert a band, error={}", e.getMessage());
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }

        return band;
    }

    @Override
    public boolean deleteByName(String bandName) {
        return false;
    }

    @Override
    public boolean deleteById(Long bandId) {
        return false;
    }

    @Override
    public boolean delete(Band band) {
        return false;
    }

    @Override
    public List<Band> getBands() {
        List<Band> bandList = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            Query<Band> query = session.createQuery(HQLStatementUtil.HQL_SELECT_ALL_BANDS);
            bandList = query.list();
        } catch (HibernateException he) {
            logger.error("fail to retrieve all bands, error={}", he.getMessage());
        }finally {
            session.close();
        }
        if(bandList == null)
            bandList = new ArrayList<Band>();
        return bandList;
    }

    @Override
    public Band getBandById(Long id) {
        Band band = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            Query<Band> query = session.createQuery(HQLStatementUtil.HQL_SELECT_BAND_BY_ID);
            query.setParameter("id", id);
        } catch (HibernateException he) {
            logger.error("fail to retrieve band with id={}, error={}", id, he.getMessage());
        }finally {
            session.close();
        }
        return band;
    }

    @Override
    public Band getBandByName(String bandName) {
        return null;
    }

    @Override
    public List<Band> getBandsWithCatalog() {
        return null;
    }

    @Override
    public Band getBandAndAlbumsAndGenresByBandId(Long bandId) {
        Band band = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        try{
            Query<Band> query = session.createQuery(HQLStatementUtil.HQL_SELECT_ALBUM_WITH_CATALOG_BY_BAND_ID);
            query.setParameter("id", bandId);
        } catch (HibernateException he) {
            logger.error("fail to retrieve band with id={}, error={}", bandId, he.getMessage());
        }finally {
            session.close();
        }
        return band;
    }

    @Override
    public Band getBandAndAlbumsAndGenresByBandName(String bandName) {
        return null;
    }
}
