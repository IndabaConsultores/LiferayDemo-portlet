package com.traintium.books;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.apache.commons.lang.StringUtils;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
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

	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String viewName = ParamUtil.get(renderRequest, "viewName", "");
		
		if(viewName.equals("/html/books/detail.jsp")){
			long bookId = ParamUtil.getLong(renderRequest, "bookId", 0);
			_log.debug("obteniendo book " + bookId);
			try {
				if(bookId!=0){
					renderRequest.setAttribute("book", BookLocalServiceUtil.getBook(bookId));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			include(viewName, renderRequest, renderResponse);
		}
		else{
			int cur = ParamUtil.getInteger(renderRequest, "cur", 1);
			int delta = ParamUtil.getInteger(renderRequest, "delta", Constants.DEFAULT_DELTA);
	
			try {
				List<Book> books = BookLocalServiceUtil.getBooksByGroupId(themeDisplay.getScopeGroupId(), (cur - 1) * delta,
						cur * delta);
				renderRequest.setAttribute("books", books);
				renderRequest.setAttribute("booksCount",
						BookLocalServiceUtil.countBooksByGroupId(themeDisplay.getScopeGroupId()));
	
			} catch (SystemException e) {
	
				e.printStackTrace();
			}
			super.doView(renderRequest, renderResponse);
		}
	}

	public void saveBook(ActionRequest actionRequest, ActionResponse actionResponse) throws SystemException, PortalException {

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long bookId = ParamUtil.getLong(actionRequest, "bookId", 0);
		
		String nombre = ParamUtil.getString(actionRequest, "nombre");
		String autor = ParamUtil.getString(actionRequest, "autor");
		int anio = ParamUtil.getInteger(actionRequest, "anio", 0);

		_log.debug("Save book " + nombre + " by " + autor + " written in " + anio);
		if (StringUtils.isEmpty(nombre)) {
			SessionErrors.add(actionRequest, "error-empty-name");
		}
		if (StringUtils.isEmpty(autor)) {
			SessionErrors.add(actionRequest, "error-empty-author");
		}
		if (anio == 0) {
			SessionErrors.add(actionRequest, "error-empty-year");
		}
		if (!SessionErrors.isEmpty(actionRequest)) {
			_log.debug("Algún dato no es correcto");
			PortalUtil.copyRequestParameters(actionRequest, actionResponse);
			return;
		}
		
		Book book = null;
		if(bookId==0){
			book = BookLocalServiceUtil.createBook(CounterLocalServiceUtil.increment());
			book.setCreateDate(new Date());
			book.setGroupId(themeDisplay.getScopeGroupId());
			book.setCompanyId(themeDisplay.getCompanyId());
			book.setUserId(themeDisplay.getUserId());
		}
		else{
			book = BookLocalServiceUtil.getBook(bookId);
		}
		
		book.setNombre(nombre);
		book.setAutor(autor);
		book.setAnio(anio);
		
		book.setModifiedDate(new Date());

		BookLocalServiceUtil.updateBook(book);

	}
	
	public void deleteBook(ActionRequest actionRequest, ActionResponse actionResponse) throws SystemException, PortalException {

		long bookId = ParamUtil.getLong(actionRequest, "bookId", 0);
		
		if(bookId!=0){
			BookLocalServiceUtil.deleteBook(bookId);
		}
	}
	
	

	Log _log = LogFactoryUtil.getLog(this.getClass());
}
