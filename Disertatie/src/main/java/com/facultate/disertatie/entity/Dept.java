package com.facultate.disertatie.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DEPT")
public class Dept {
	@Id
	@Column(name = "id", nullable = false, updatable=false)
	private Long deptId;
	
	@Column(name = "nume_dept", length=30)
	private String numeDept;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "dept_manager", joinColumns = @JoinColumn(name = "dept_id"), inverseJoinColumns = @JoinColumn(name = "manager_id"))
	private AppPerso manager;
	
	@JsonIgnore
	@OneToMany(mappedBy="dept", fetch = FetchType.LAZY)
	private Set<AppPerso> angajati;

	public Set<AppPerso> getAngajati() {
		
		Set<AppPerso> Angajati = angajati;
		for (AppPerso angajat: angajati){
			AppUser user = angajat.getUser();
			Set<AppRole> userRoles = user.getRoles();
			for (AppRole role: userRoles) {
				if (role.getRoleName() != null && role.getRoleName().equalsIgnoreCase("ROLE_MANAGER")) {
					Angajati.remove(angajat);
				}
			}
		}
		return Angajati;
	}

	public void setAngajati(Set<AppPerso> angajati) {
		this.angajati = angajati;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getNumeDept() {
		return numeDept;
	}

	public void setNumeDept(String numeDept) {
		this.numeDept = numeDept;
	}

	public AppPerso getManager() {
		return manager;
	}

	public void setManager(AppPerso manager) {
		this.manager = manager;
	}
	
}