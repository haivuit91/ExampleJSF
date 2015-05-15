package com.java.jsf.model.dao.service;

import java.util.List;

import com.java.jsf.model.entities.TblBank;

public interface BankDAOService {

	/**
	 * get all list Bank
	 * 
	 * @return list Bank
	 */
	public List<TblBank> getListBank();

	/**
	 * get list Bank by id
	 * 
	 * @param id
	 * @return Bank
	 */
	public TblBank getBankById(int id);

	/**
	 * check login
	 *
	 *** @param name
	 **** @param pwd
	 * @return true if check login successfully
	 */
	public boolean chenkLogin(String name);

	/**
	 * add new Bank
	 *
	 *** @param bank
	 * @return true if add Bank successfully
	 */
	public boolean addBank(TblBank bank);

	/**
	 * update Bank
	 *
	 ** @param bank
	 * @return true if update Bank successfully
	 */
	public boolean updateBank(TblBank bank);

	/**
	 * delete Bank
	 *
	 ** @param bank
	 * @return true if delete Bank successfully
	 */
	public boolean delBank(TblBank bank);
}
