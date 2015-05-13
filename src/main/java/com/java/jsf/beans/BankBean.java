package com.java.jsf.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import java.util.Date;
import java.util.List;

import com.java.jsf.model.dao.BankDAO;
import com.java.jsf.model.dao.service.BankDAOService;
import com.java.jsf.model.entities.TblBank;

@ManagedBean
@SessionScoped
public class BankBean {

	private final BankDAOService BANK_SERVICE = BankDAO.getInstance();

	private TblBank bank;
	private List<TblBank> listBank;

	public BankBean() {
		if (bank == null) {
			bank = new TblBank();
		}
	}

	public List<TblBank> getListBank() {
		listBank = BANK_SERVICE.getListBank();
		return listBank;
	}

	public void addBank(ActionEvent event) {
		String msg;
		Date date = new Date();
		TblBank bank = new TblBank(this.bank.getName(), date,
				this.bank.getStatus(), this.bank.getPublic_());
		if (BANK_SERVICE.delBank(BANK_SERVICE.getBankById(this.bank.getId()))) {
			msg = "Add bank succesfully!";
		} else {
			msg = "Add bank failed!";
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				msg, "Message!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void updateBank(ActionEvent event) {
		String msg;
		Date date = new Date();
		TblBank bank = new TblBank(this.bank.getId(), this.bank.getName(),
				date, this.bank.getStatus(), this.bank.getPublic_());
		if (BANK_SERVICE.updateBank(bank)) {
			msg = "Update bank succesfully!";
		} else {
			msg = "Add bank failed!";
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				msg, "Message!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void delBank(ActionEvent event) {
		String msg;
		if (BANK_SERVICE.delBank(BANK_SERVICE.getBankById(this.bank.getId()))) {
			msg = "Deleted bank succesfully!";
		} else {
			msg = "Deleted bank failed!";
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				msg, "Message!");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public TblBank getBank() {
		return bank;
	}

	public void setBank(TblBank bank) {
		this.bank = bank;
	}

}
