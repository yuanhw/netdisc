package netdisc.servlet;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import netdisc.util.HibernateUtil;
import netdisc.util.UpFileUtil;
import netdisc.web.SendCode;

public class InitSetListener implements ServletContextListener {

	public InitSetListener() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		HibernateUtil.getSessionFactory().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		HibernateUtil.getSessionFactory();
		SendCode.init("https://api.netease.im/sms/sendcode.action",
				"2e1c466cae638c8b6005dee0a3733b92", 
				"dd68eb2e699c", "3054747");
		UpFileUtil.setBasepath(sce.getServletContext().getRealPath("/")+"userfiles"+File.separator);
	}

}
