package in.co.online.food.delivery.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.online.food.delivery.bean.FoodChartBean;
import in.co.online.food.delivery.bean.DessertBean;
import in.co.online.food.delivery.bean.FoodBean;
import in.co.online.food.delivery.bean.RestaurantBean;
import in.co.online.food.delivery.bean.UserBean;
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.exception.DatabaseException;
import in.co.online.food.delivery.exception.DuplicateRecordException;
import in.co.online.food.delivery.util.JDBCDataSource;

public class ChartModel {
	
	
	private static Logger log = Logger.getLogger(ChartModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM OF_FOODCHART");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	public Integer nextChartId() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(CHARTID) FROM OF_FOODCHART");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	
	public FoodChartBean findByPk(long pk) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_FOODCHART WHERE ID=?");
		FoodChartBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			//connection building 
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				//Filling the Class FoodChartBean
				bean = new FoodChartBean();
				bean.setId(rs.getLong(1));
				bean.setUserId(rs.getLong(2));
				bean.setUserName(rs.getString(3));
				bean.setFoodId(rs.getLong(4));
				bean.setFoodName(rs.getString(5));
				bean.setChartId(rs.getLong(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}
	
	
	public long add(FoodChartBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		UserModel uModel=new UserModel();
		UserBean uBean=uModel.findByPK(bean.getUserId());

		FoodBean fBean=null;
		
		
		FoodModel fModel=new FoodModel();
		fBean=fModel.findByPk(bean.getFoodId());
		bean.setFoodName(fBean.getFoodName());
		

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO OF_FOODCHART VALUES(?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getUserId());
			pstmt.setString(3, uBean.getFirstName()+" "+uBean.getLastName());
			pstmt.setLong(4, bean.getFoodId());
			pstmt.setString(5,bean.getFoodName());
			pstmt.setLong(6, nextChartId());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}
	
	
	public void delete(FoodChartBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM OF_FOODCHART WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	
	public List search(FoodChartBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search User with pagination
	 * 
	 * @return list : List of Users
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List search(FoodChartBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_FOODCHART WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getUserName() != null && bean.getUserName().length() > 0) {
				sql.append(" AND UserName like '" + bean.getUserName() + "%'");
			}
			
			if (bean.getFoodName() != null && bean.getFoodName().length() > 0) {
				sql.append(" AND FoodName like '" + bean.getFoodName() + "%'");
			}
			
			
			if (bean.getUserId() > 0) {
				sql.append(" AND UserId = " + bean.getUserId());
			}
			
			
			
			if (bean.getFoodId() > 0) {
				sql.append(" AND FoodId = " + bean.getFoodId());
			}
			
			
			

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("user model search  :"+sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new FoodChartBean();
				bean.setId(rs.getLong(1));
				bean.setUserId(rs.getLong(2));
				bean.setUserName(rs.getString(3));
				bean.setFoodId(rs.getLong(4));
				bean.setFoodName(rs.getString(5));
				bean.setChartId(rs.getLong(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}
	
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of User with pagination
	 * 
	 * @return list : List of users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from OF_FOODCHART");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		
		System.out.println("sql in list user :"+sql);
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FoodChartBean bean = new FoodChartBean();
				bean.setId(rs.getLong(1));
				bean.setUserId(rs.getLong(2));
				bean.setUserName(rs.getString(3));
				bean.setFoodId(rs.getLong(4));
				bean.setFoodName(rs.getString(5));
				bean.setChartId(rs.getLong(6));
				bean.setCreatedBy(rs.getString(7));
				bean.setModifiedBy(rs.getString(8));
				bean.setCreatedDatetime(rs.getTimestamp(9));
				bean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}

}
