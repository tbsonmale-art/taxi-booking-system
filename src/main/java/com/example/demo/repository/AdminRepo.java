package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;

import jakarta.transaction.Transactional;

import java.util.Optional;



@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>
{
	//select sn,username,password from Admin where username=?
	Optional<Admin> findByUsername(String username);
	
	@Modifying//it tells JPA to create native query
	@Transactional 
	@Query(value = "update admin set username=:newusername,password=:newpassword where username=:oldusername",
	nativeQuery=true)
	public int updateCredentials(
			@Param("newusername")String newusername,
			@Param("newpassword")String newpassword,
			@Param("oldusername")String oldusername
			);
}


//@Modifying 
//ही annotation JPA ला सांगते की हा query "modifying query" आहे म्हणजे हा query फक्त data fetch करण्यासाठी नाही तर 
//data बदलण्यासाठी (update/delete/insert) आहे.
//
//@Transactional 
//ही annotation Spring ला सांगते की हा method "transaction" मध्ये चालवला जावा. 
//म्हणजे update होत असताना काही error आली तर बदल rollback केले जातील आणि database सुरक्षित राहील.

//@Query(value = "update admin set username=:newusername,password=:newpassword where username=:oldusername",
//    nativeQuery=true)
//ही annotation actual SQL query define करते.
//value मध्ये आपण native SQL लिहिलेला आहे: 
//- "update admin" म्हणजे admin table update करा
//- "set username=:newusername,password=:newpassword" म्हणजे नवीन username आणि password सेट करा
//- "where username=:oldusername" म्हणजे जिथे जुना username आहे तिथेच हा बदल करा
//nativeQuery=true म्हणजे हा query JPA च्या JPQL मध्ये नाही तर direct SQL मध्ये execute होईल.
//public int updateCredentials(
//हा method declaration आहे जो हा update query चालवेल.
//हा int परत करतो म्हणजे किती rows update झाल्या ते परत मिळेल.

// @Param("newusername") String newusername,
//  हा parameter आहे जो query मधल्या ":newusername" ला value देईल.
//  युजरने दिलेला नवीन username इथे पास केला जातो.

// @Param("newpassword") String newpassword,
//  हा parameter query मधल्या ":newpassword" ला value देईल.
//  युजरने दिलेला नवीन password इथे पास केला जातो.

// @Param("oldusername") String oldusername
//  हा parameter query मधल्या ":oldusername" ला value देईल.
//  ज्याचा username बदलायचा आहे तो जुना username इथे पास होतो.


























