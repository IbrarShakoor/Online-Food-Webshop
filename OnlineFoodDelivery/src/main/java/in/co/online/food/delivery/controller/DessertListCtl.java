package in.co.online.food.delivery.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.online.food.delivery.bean.BaseBean;
import in.co.online.food.delivery.bean.DessertBean;
import in.co.online.food.delivery.bean.RestaurantBean;
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.model.DessertModel;
import in.co.online.food.delivery.util.DataUtility;
import in.co.online.food.delivery.util.PropertyReader;
import in.co.online.food.delivery.util.ServletUtility;



/**
 * Servlet implementation class DessertListCtl
 */
@WebServlet(name = "DessertListCtl", urlPatterns = { "/ctl/DessertListCtl" })
public class DessertListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private final Logger log = Logger.getLogger(DessertListCtl.class);

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("DessertListCtl  populateBean method start");
		DessertBean bean = new DessertBean();
		
		bean.setDessertName(DataUtility.getString(request.getParameter("name")));
		
		log.debug("DessertListCtl  populateBean method end");

		return bean;
	}

	/**
	 * Contains display logic
	 */
	@Override
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("DessertListCtl  doGet method start");

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		DessertBean bean = (DessertBean) populateBean(request);
		DessertModel model = new DessertModel();

		List list = null;
		
		long dsid=DataUtility.getLong(request.getParameter("dsD"));

		try {
			
			if(dsid > 0) {
				
				DessertBean deletebean = new DessertBean();
				deletebean.setId(dsid);
				model.delete(deletebean);
				ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
			}
			
			list = model.search(bean, pageNo, pageSize);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			
			return;
		}

		if (list == null || list.size() == 0) {
			ServletUtility.setErrorMessage("No record found ", request);
		}

		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
		log.debug("DessertListCtl  doGet method end");

	}

	/**
	 * Contains submit logic
	 */
	@Override
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("DessertListCtl  doPost method start");

		List list = null;
		String ids = request.getParameter("ids");

		System.out.println("=============================" + ids);

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		DessertBean bean = (DessertBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		DessertModel model = new DessertModel();

		try {

			if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

				if (OP_SEARCH.equalsIgnoreCase(op)) {
					pageNo = 1;
				} else if (OP_NEXT.equalsIgnoreCase(op)) {
					pageNo++;
				} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
					pageNo--;
				} else if (OP_NEW.equalsIgnoreCase(op)) {
					ServletUtility.redirect(OFDView.DESSERT_CTL, request, response);
					return;
				}

			
			} else if (OP_DELETE.equalsIgnoreCase(op)) {
				pageNo = 1;
				if (ids != null) {
					DessertBean deletebean = new DessertBean();
					
						deletebean.setId(DataUtility.getInt(ids));
						System.out.println("===============vsafff==============vds" + deletebean.getId());
						model.delete(deletebean);
					
					ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
				} else {
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			} else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(OFDView.DESSERT_LIST_CTL, request, response);
				return;
			}

			list = model.search(bean, pageNo, pageSize);
			/*
			 * DessertBean be =(DessertBean) list;
			 * System.out.println(be.getName());
			 */

			if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("DessertListCtl  doPost method end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return OFDView.DESSERT_LIST_VIEW;
	}

}
