package db;

import models.Artist;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DBArtist {
    public static void saveArtist(Artist artist) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(artist);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Artist> getArtists()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Artist> artists = null;
        try {
            transaction = session.beginTransaction();
            artists = session.createQuery("from Artist").list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return artists;
    }

    public static Artist getArtistById(int id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Artist artist = null;
        try {
            transaction = session.beginTransaction();
            String queryString = "from Artist where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            artist = (Artist) query.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return artist;
    }

    public static void deleteArtist(int id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Artist artist = null;
        try {
            transaction = session.beginTransaction();
            String queryString = "from Artist where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", id);
            artist = (Artist) query.uniqueResult();
            session.delete(artist);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void updateArtist(Artist artist) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(artist);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
