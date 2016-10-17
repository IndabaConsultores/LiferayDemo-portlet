package com.traintium.sample1;

import java.io.IOException;

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
		_log.debug("Estamos en doView");
		super.doView(renderRequest, renderResponse);
	}
	
	Log _log = LogFactoryUtil.getLog(this.getClass());
	
}
