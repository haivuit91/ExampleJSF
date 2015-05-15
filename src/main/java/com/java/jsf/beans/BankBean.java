package com.java.jsf.beans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.java.jsf.model.dao.BankDAO;
import com.java.jsf.model.dao.service.BankDAOService;
import com.java.jsf.model.entities.TblBank;

@ManagedBean
@RequestScoped
public class BankBean {

	private final BankDAOService BANK_SERVICE = BankDAO.getInstance();

	private TblBank bank;
	private List<TblBank> listBank;
	private List<TblBank> filteredBank;
	private String msg;
	private List<String> _status;

	public BankBean() {
		if (bank == null) {
			bank = new TblBank();
		}
	}

	public List<TblBank> getListBank() {
		listBank = BANK_SERVICE.getListBank();
		return listBank;
	}

	public List<TblBank> getFilteredBank() {
		return filteredBank;
	}

	public void setFilteredBank(List<TblBank> filteredBank) {
		this.filteredBank = filteredBank;
	}

	public List<String> get_Status() {
		String[] status = new String[3];
		status[0] = "Using";
		status[1] = "Pause";
		status[2] = "Stop";
		_status = Arrays.asList(status);
		return _status;
	}

	public String doLogin() {
		if (BANK_SERVICE.chenkLogin(this.bank.getName())) {
			return "bank-management.jsf?faces-redirect=true";
		} else {
			msg = "Login failed!";
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Message!", msg));
			return "index.jsf";
		}
	}

	public void addBank(ActionEvent event) {
		Date date = new Date();
		TblBank bank = new TblBank(this.bank.getName(), date,
				this.bank.getStatus(), this.bank.getPublic_());
		if (BANK_SERVICE.addBank(bank)) {
			msg = "Add bank succesfully!";
		} else {
			msg = "Add bank failed!";
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Message!", msg));
	}

	public void updateBank(ActionEvent event) {
		Date date = new Date();
		TblBank bank = new TblBank(this.bank.getId(), this.bank.getName(),
				date, this.bank.getStatus(), this.bank.getPublic_());
		if (BANK_SERVICE.updateBank(bank)) {
			msg = "Update bank succesfully!";
		} else {
			msg = "Update bank failed!";
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Message!", msg));
	}

	/*public String setColor(int id) {
		String color = "";
		TblBank bank = BANK_SERVICE.getBankById(id);
		if (bank.getStatus() == "Using") {
			color = "green";
		} else if (bank.getStatus() == "Pause") {
			color = "yellow";
		} else {
			color = "red";
		}
		return color;
	}*/

	public void delBank(ActionEvent event) {
		if (BANK_SERVICE.delBank(BANK_SERVICE.getBankById(this.bank.getId()))) {
			msg = "Deleted bank succesfully!";
		} else {
			msg = "Deleted bank failed!";
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Message!", msg));
	}

	public TblBank getBank() {
		return bank;
	}

	public void setBank(TblBank bank) {
		this.bank = bank;
	}

}
