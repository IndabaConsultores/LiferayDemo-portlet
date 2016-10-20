package com.traintium.books;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.commons.lang.StringUtils;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.traintium.books.model.Book;
import com.traintium.books.service.BookLocalServiceUtil;

/**
 * Portlet implementation class BooksPortlet
 */
public class BooksPortlet extends MVCPortlet {
 

	public void saveBook(ActionRequest actionRequest, ActionResponse actionResponse) throws SystemException{
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		String nombre = ParamUtil.getString(actionRequest, "nombre");
		String autor = ParamUtil.getString(actionRequest, "autor");
		int anio = ParamUtil.getInteger(actionRequest, "anio",0);
		
		_log.debug("Save book " +  nombre + " by " + autor + " written in " + anio);
		if(StringUtils.isEmpty(nombre)){
			SessionErrors.add(actionRequest, "error-empty-name");
		}
		if(StringUtils.isEmpty(autor)){
			SessionErrors.add(actionRequest, "error-empty-author");
		}
		if(anio==0){
			SessionErrors.add(actionRequest, "error-empty-year");
		}
		if(!SessionErrors.isEmpty(actionRequest)){
			_log.debug("Algún dato no es correcto");
			PortalUtil.copyRequestParameters(actionRequest, actionResponse);
			return;
		}
		
		Book book = BookLocalServiceUtil.createBook(CounterLocalServiceUtil.increment());
		book.setNombre(nombre);
		book.setAutor(autor);
		book.setAnio(anio);
		book.setGroupId(themeDisplay.getScopeGroupId());
		book.setCompanyId(themeDisplay.getCompanyId());
		book.setUserId(themeDisplay.getUserId());
		book.setCreateDate(new Date());
		book.setModifiedDate(new Date());
		
		BookLocalServiceUtil.addBook(book);
		
		
	}
	
	Log _log = LogFactoryUtil.getLog(this.getClass());
}
