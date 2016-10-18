package com.traintium.sample1;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class Sample1Portlet
 */
public class Sample1Portlet extends MVCPortlet {
 
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		_log.debug("Estamos en doView de sample1");
		super.doView(renderRequest, renderResponse);
	}
	
	public void actionSaludo(ActionRequest actionRequest, ActionResponse actionResponse){
		_log.debug("Estamos en actionSaludo");
	}
	
	Log _log = LogFactoryUtil.getLog(this.getClass());
	
}
