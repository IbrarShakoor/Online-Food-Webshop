package in.co.online.food.delivery.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.online.food.delivery.bean.DessertBean;
import in.co.online.food.delivery.bean.RestaurantBean;
import in.co.online.food.delivery.bean.UserBean;
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.exception.DatabaseException;
import in.co.online.food.delivery.exception.DuplicateRecordException;
import in.co.online.food.delivery.util.JDBCDataSource;

public class DessertModel {

	private static Logger log = Logger.getLogger(DessertModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM OF_DESSERT");
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

	public Integer nextDessertId() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(DESSERTID) FROM OF_DESSERT");
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
	
	//Connection with SQL Database and filling the values into respective bean class
	public DessertBean findByName(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_DESSERT WHERE DESSERTNAME=?");
		DessertBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new DessertBean();
				bean.setId(rs.getLong(1));
				bean.setRestaurantId(rs.getLong(2));
				bean.setRestaurantName(rs.getString(3));
				bean.setDessertId(rs.getLong(4));
				bean.setDessertName(rs.getString(5));
				bean.setDessertDescription(rs.getString(6));
				bean.setImage(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				bean.setFoodPrice(rs.getLong(12));
				bean.setDisscount(rs.getLong(13));
				bean.setFinalPrice(rs.getLong(14));

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
	
	public DessertBean findByNameAndRestId(String login,long id) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_DESSERT WHERE DESSERTNAME=? AND RESTAURANTID=?");
		DessertBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			pstmt.setLong(2,id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new DessertBean();
				bean.setId(rs.getLong(1));
				bean.setRestaurantId(rs.getLong(2));
				bean.setRestaurantName(rs.getString(3));
				bean.setDessertId(rs.getLong(4));
				bean.setDessertName(rs.getString(5));
				bean.setDessertDescription(rs.getString(6));
				bean.setImage(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				bean.setFoodPrice(rs.getLong(12));
				bean.setDisscount(rs.getLong(13));
				bean.setFinalPrice(rs.getLong(14));
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
	
	public DessertBean findByPk(long pk) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_DESSERT WHERE ID=?");
		DessertBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new DessertBean();
				bean.setId(rs.getLong(1));
				bean.setRestaurantId(rs.getLong(2));
				bean.setRestaurantName(rs.getString(3));
				bean.setDessertId(rs.getLong(4));
				bean.setDessertName(rs.getString(5));
				bean.setDessertDescription(rs.getString(6));
				bean.setImage(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				bean.setFoodPrice(rs.getLong(12));
				bean.setDisscount(rs.getLong(13));
				bean.setFinalPrice(rs.getLong(14));

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
	
	public long add(DessertBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		DessertBean existbean = findByNameAndRestId(bean.getDessertName(),bean.getRestaurantId());

		if (existbean != null) {
			throw new DuplicateRecordException("Dessert is already this Restaurant");
		}

		RestaurantModel model = new RestaurantModel();
		
		RestaurantBean uBean = model.findByPk(bean.getRestaurantId());
		
		bean.setFinalPrice((bean.getFoodPrice()*bean.getDisscount())/100);

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO OF_DESSERT VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getRestaurantId());
			pstmt.setString(3, uBean.getRestaurantName());
			pstmt.setLong(4, nextDessertId());
			pstmt.setString(5, bean.getDessertName());
			pstmt.setString(6, bean.getDessertDescription());
			pstmt.setString(7, bean.getImage());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12,bean.getFoodPrice());
			pstmt.setLong(13,bean.getDisscount());
			pstmt.setLong(14,bean.getFinalPrice());
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
	
	public void delete(DessertBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM OF_DESSERT WHERE ID=?");
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
	
	public void update(DessertBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		DessertBean existbean = findByNameAndRestId(bean.getDessertName(),bean.getRestaurantId());
		// Check if updated LoginId already exist
		if (existbean != null && !(existbean.getId() == bean.getId())) {
			throw new DuplicateRecordException("Dessert is already this Restaurant");
		}
		
		RestaurantModel model = new RestaurantModel();
		
		RestaurantBean uBean = model.findByPk(bean.getRestaurantId());
		
		bean.setFinalPrice((bean.getFoodPrice()*bean.getDisscount())/100);

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE OF_DESSERT SET RestaurantId=?,RestaurantName=?,DessertId=?,DessertName=?,DessertDescription=?,Image=?,"
					+ "CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,foodPrice=?,disscount=?,finalPrice=? WHERE ID=?");
			pstmt.setLong(1, bean.getRestaurantId());
			pstmt.setString(2, uBean.getRestaurantName());
			pstmt.setLong(3, bean.getDessertId());
			pstmt.setString(4, bean.getDessertName());
			pstmt.setString(5, bean.getDessertDescription());
			pstmt.setString(6, bean.getImage());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11,bean.getFoodPrice());
			pstmt.setLong(12,bean.getDisscount());
			pstmt.setLong(13,bean.getFinalPrice());
			pstmt.setLong(14, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	
	public List search(DessertBean bean) throws ApplicationException {
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

	public List search(DessertBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM OF_DESSERT WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getRestaurantName() != null && bean.getRestaurantName().length() > 0) {
				sql.append(" AND RestaurantName like '" + bean.getRestaurantName() + "%'");
			}
			if (bean.getDessertName() != null && bean.getDessertName().length() > 0) {
				sql.append(" AND DessertName like '" + bean.getDessertName() + "%'");
			}
			
			
			if (bean.getRestaurantId() > 0) {
				sql.append(" AND RestaurantId = " + bean.getRestaurantId());
			}
			
			if (bean.getDessertId() > 0) {
				sql.append(" AND DESSERTId = " + bean.getDessertId());
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
				bean = new DessertBean();
				bean.setId(rs.getLong(1));
				bean.setRestaurantId(rs.getLong(2));
				bean.setRestaurantName(rs.getString(3));
				bean.setDessertId(rs.getLong(4));
				bean.setDessertName(rs.getString(5));
				bean.setDessertDescription(rs.getString(6));
				bean.setImage(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				bean.setFoodPrice(rs.getLong(12));
				bean.setDisscount(rs.getLong(13));
				bean.setFinalPrice(rs.getLong(14));
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
		StringBuffer sql = new StringBuffer("select * from OF_DESSERT");
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
				DessertBean bean = new DessertBean();
				bean.setId(rs.getLong(1));
				bean.setRestaurantId(rs.getLong(2));
				bean.setRestaurantName(rs.getString(3));
				bean.setDessertId(rs.getLong(4));
				bean.setDessertName(rs.getString(5));
				bean.setDessertDescription(rs.getString(6));
				bean.setImage(rs.getString(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				bean.setFoodPrice(rs.getLong(12));
				bean.setDisscount(rs.getLong(13));
				bean.setFinalPrice(rs.getLong(14));

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
