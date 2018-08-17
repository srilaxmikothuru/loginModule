package com.capgemini.capstore.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.capstore.entities.Authentication;

@Repository
@Transactional
public class AuthenticationDAO {

	@Autowired
	private EntityManager entityManager;

	public List<String> getRoleNames(String mobileNumber) {
		
		String sql = "select userAccess from Authentication where mobileNo = :mobileNo";
		Query query = this.entityManager.createQuery(sql, String.class);
		query.setParameter("mobileNo", mobileNumber);
		return query.getResultList();
	}


	public Authentication findUserAccount(String mobile_no) {
		try {
			String sql = "Select e from " + Authentication.class.getName() + " e " //
					+ " Where e.mobileNo = :mobileNo ";

			Query query = entityManager.createQuery(sql, Authentication.class);
			query.setParameter("mobileNo", mobile_no);

			return (Authentication) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public void passwordChange(String mobile_num, String newpwd, String confirmpwd) {
		try {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			System.out.println(newpwd);
			newpwd = encoder.encode(newpwd);	
			
			System.out.println("num: "+mobile_num);
			System.out.println("pwd: "+newpwd);
			
			String sql ="update Authentication as e set e.password =:pwd  Where e.mobileNo =:mobile";

			Query query = entityManager.createQuery(sql, Authentication.class);
			query.setParameter("mobile", mobile_num);
			query.setParameter("pwd", newpwd);
			System.out.println("before query update");
			int res= query.executeUpdate();
			System.out.println("after query "+res);
		} catch (NoResultException e) {
			System.out.println("rrrrrrr: "+e.getMessage());
		}
		
	}


}
