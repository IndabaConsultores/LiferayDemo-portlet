package com.traintium.books;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class BooksPortlet
 */
public class BooksPortlet extends MVCPortlet {
 

	public void saveBook(ActionRequest actionRequest, ActionResponse actionResponse){
		String nombre = ParamUtil.getString(actionRequest, "nombre");
		String autor = ParamUtil.getString(actionRequest, "autor");
		int anio = ParamUtil.getInteger(actionRequest, "anio",0);
		
		_log.debug("Save book " +  nombre + " by " + autor + " written in " + anio);
		
		PortalUtil.copyRequestParameters(actionRequest, actionResponse);
	}
	
	Log _log = LogFactoryUtil.getLog(this.getClass());
}
