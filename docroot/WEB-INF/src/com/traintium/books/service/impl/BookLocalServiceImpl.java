/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.traintium.books.service.impl;

import java.util.List;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.traintium.books.model.Book;
import com.traintium.books.service.base.BookLocalServiceBaseImpl;

/**
 * The implementation of the book local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.traintium.books.service.BookLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author aritz
 * @see com.traintium.books.service.base.BookLocalServiceBaseImpl
 * @see com.traintium.books.service.BookLocalServiceUtil
 */
public class BookLocalServiceImpl extends BookLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.traintium.books.service.BookLocalServiceUtil} to access the book local service.
	 */
	public List<Book> getBooksByGroupId(long groupId, int start, int end) throws SystemException{
		return bookPersistence.findByGroupId(groupId, start, end);
	}
	
	public long countBooksByGroupId(long groupId) throws SystemException{
		return bookPersistence.countByGroupId(groupId);
	}
	
	public List<Book> findBooksEntreAnios(long groupId, int anioMin, int anioMax, int start, int end) throws SystemException{
		DynamicQuery bookQuery = DynamicQueryFactoryUtil.forClass(Book.class);
		if(anioMin!=-1)
			bookQuery.add(RestrictionsFactoryUtil.ge("anio", anioMin));
		if(anioMax!=-1)
			bookQuery.add(RestrictionsFactoryUtil.le("anio", anioMax));
		
		bookQuery.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		
		return bookPersistence.findWithDynamicQuery(bookQuery , start, end );
	}
	
	public long countBooksEntreAnios(long groupId, int anioMin, int anioMax, int start, int end) throws SystemException{
		DynamicQuery bookQuery = DynamicQueryFactoryUtil.forClass(Book.class);
		if(anioMin!=-1)
			bookQuery.add(RestrictionsFactoryUtil.ge("anio", anioMin));
		if(anioMax!=-1)
			bookQuery.add(RestrictionsFactoryUtil.le("anio", anioMax));
		
		bookQuery.add(RestrictionsFactoryUtil.eq("groupId", groupId));
		
		return bookPersistence.countWithDynamicQuery(bookQuery);
	}
}