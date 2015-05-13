package com.java.jsf.model.test;

import java.util.Date;
import java.util.List;

import com.java.jsf.model.dao.BankDAO;
import com.java.jsf.model.dao.service.BankDAOService;
import com.java.jsf.model.entities.TblBank;

public class Test {

	public final BankDAOService BANK_SERVICE = BankDAO.getInstance();

	public void getList() {
		List<TblBank> listBank = BANK_SERVICE.getListBank();
		for (TblBank tblBank : listBank) {
			System.out.print(tblBank.getId());
			System.out.print(tblBank.getName());
			System.out.print(tblBank.getModifyTime());
			System.out.print(tblBank.getStatus());
			System.out.print(tblBank.getPublic_());
		}
	}

	public boolean addBank() {
		Date now = new Date();
		TblBank bank = new TblBank("Test 3", now, 3, false);
		if (BANK_SERVICE.addBank(bank)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateBank() {
		Date date = new Date();
		TblBank bank = new TblBank(3, "Test update", date, 2, true);
		if (BANK_SERVICE.updateBank(bank)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		Test test = new Test();

		// test.getList();

		if (test.addBank()) {
			System.out.print("ok");
		} else {
			System.out.print("false");
		}

		/*
		 * if (test.updateBank()) { System.out.print("ok"); } else {
		 * System.out.print("false"); }
		 */

	}
}
