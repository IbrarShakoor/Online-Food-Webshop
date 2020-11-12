package in.co.online.food.delivery.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.online.food.delivery.bean.FoodChartBean;
import in.co.online.food.delivery.bean.UserBean;
import in.co.online.food.delivery.exception.ApplicationException;
import in.co.online.food.delivery.exception.DuplicateRecordException;
import in.co.online.food.delivery.model.ChartModel;
import in.co.online.food.delivery.util.DataUtility;
import in.co.online.food.delivery.util.ServletUtility;

/**
 * Servlet implementation class ChartCtl
 */
@WebServlet(name = "FoodChartCtl", urlPatterns = { "/ctl/FoodChartCtl" })
public class FoodChartCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(FoodChartCtl.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ChartCtl doGet method start");
		FoodChartBean bean=new FoodChartBean();
		HttpSession session=request.getSession(true);
		UserBean uBean=(UserBean)session.getAttribute("user");
		ChartModel model=new ChartModel();
		
		
		long foId=DataUtility.getLong(request.getParameter("foD"));
		bean.setUserId(uBean.getId());
		
		try {
		
			bean.setFoodId(foId);
			long pk =model.add(bean);
			ServletUtility.redirect(OFDView.FOOD_LIST_CTL, request, response);
		
		
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
			} catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		log.debug("ChartCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
