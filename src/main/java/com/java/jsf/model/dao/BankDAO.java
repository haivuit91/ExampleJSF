package com.java.jsf.model.dao;

import java.util.List;

import com.java.jsf.hibernate.HibernateUtil;
import com.java.jsf.model.dao.service.BankDAOService;
import com.java.jsf.model.entities.TblBank;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BankDAO implements BankDAOService {

	private static Session session;

	private static HibernateUtil util;

	private static BankDAO bankDAO;

	public static BankDAO getInstance() {
		if (bankDAO == null) {
			bankDAO = new BankDAO();
		}
		return bankDAO;
	}

	@Override
	public List<TblBank> getListBank() {
		List<TblBank> bank = null;
		session = util.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			bank = session.createCriteria(TblBank.class).list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bank;
	}

	@Override
	public TblBank getBankById(int id) {
		TblBank bank = null;
		session = util.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			bank = (TblBank) session.get(TblBank.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bank;
	}

	@Override
	public boolean addBank(TblBank bank) {
		boolean check = false;
		session = util.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(bank);
			check = true;
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return check;
	}

	@Override
	public boolean updateBank(TblBank bank) {
		boolean check = false;
		session = util.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(bank);
			check = true;
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return check;
	}

	@Override
	public boolean delBank(TblBank bank) {
		boolean check = false;
		session = util.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(bank);
			check = true;
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return check;
	}
}
