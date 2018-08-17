package com.capgemini.capstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.capstore.entities.Authentication;


public interface AuthenticationDAO extends JpaRepository<Authentication,Integer>{
	
	@Query("Select ur.userAccess from Authentication ur where ur.mobileNo =?1")
	public List<String> getRoleNames(String mobileNumber);
	
	@Query("Select e from Authentication e Where e.mobileNo =?1")
	public Authentication findUserAccount(String mobile_no);
	
	
/*
	@Autowired
	private EntityManager entityManager;

	public List<String> getRoleNames(String mobileNumber) {
		String sql = "Select ur.userAccess from " + Authentication.class.getName() + " ur " //
				+ " where ur.Authentication.mobileNo = :mobileNo ";

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
*/

}
