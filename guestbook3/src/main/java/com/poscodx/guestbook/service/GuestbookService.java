
package com.poscodx.guestbook.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.poscodx.guestbook.repository.GuestbookLogRepository;
import com.poscodx.guestbook.repository.GuestbookRepository;
import com.poscodx.guestbook.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Autowired
	private GuestbookRepository guestbookRepository;

	@Autowired
	private GuestbookLogRepository guestbookLogRepository;

	public List<GuestbookVo> getContentsList() {
		return guestbookRepository.findAll();
	}
	
	public void deleteContents(Long no, String password) {
		// TX:BEGIN ///////////////////////////
		TransactionSynchronizationManager.initSynchronization();

		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		
		try {
			GuestbookVo vo = guestbookRepository.findByNo(no);
			System.out.println(vo);
			if(vo == null) {
				return;
			}
			
			int count = guestbookRepository.deleteByNoAndPassword(no, password);
			System.out.println(count);
			if(count == 1) {
				guestbookLogRepository.update(vo.getRegDate());
			}
			// TX:END(SUCCESS) ////////////////
			transactionManager.commit(status);
		} catch(Throwable e) {
			// TX:END(FAIL) ///////////////////
			transactionManager.rollback(status);
		}
	}
	
	@Transactional
	public void addContents(GuestbookVo vo) {
		// 트랜잭션 동기(Connection) 초기화  
		TransactionSynchronizationManager.initSynchronization();
		
		Connection conn = DataSourceUtils.getConnection(dataSource);
		
		try {
			// TX:BEGIN //////////////
			conn.setAutoCommit(false);
		
			int count = guestbookLogRepository.update();
		
			if(count == 0) {
				guestbookLogRepository.insert();
			}
	
			guestbookRepository.insert(vo);
			
			// TX:END(SUCCESS) ///////
			conn.commit();
		} catch(Throwable e) {
			// TX:END(FAIL) ///////
			try {
				conn.rollback();
			} catch(SQLException ignored) {
			}
		} finally {
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}
}
