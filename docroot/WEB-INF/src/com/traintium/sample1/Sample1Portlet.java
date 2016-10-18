package com.traintium.sample1;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class Sample1Portlet
 */
public class Sample1Portlet extends MVCPortlet {
 
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		String nombre = ParamUtil.getString(renderRequest, "nombre", "");
		_log.debug("doView " + nombre);
		super.doView(renderRequest, renderResponse);
	}
	
	public void actionSaludo(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException{
		
		String nombre = ParamUtil.getString(actionRequest, "nombre", "");
		String numStr = ParamUtil.getString(actionRequest, "num", "");
		_log.debug("Estamos en actionSaludo " + nombre);
		if(!Validator.isNumber(numStr)){
			SessionErrors.add(actionRequest, "error-key-no-num");
		}
		
		
		if(nombre.equals("")){
			SessionErrors.add(actionRequest, "error-key-nombre-vacio");
		}
		
		PortalUtil.copyRequestParameters(actionRequest, actionResponse);

		
	}
	
	Log _log = LogFactoryUtil.getLog(this.getClass());
	
}
