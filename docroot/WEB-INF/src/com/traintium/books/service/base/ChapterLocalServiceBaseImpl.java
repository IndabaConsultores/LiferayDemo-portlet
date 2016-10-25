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

package com.traintium.books.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.BaseLocalServiceImpl;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.persistence.UserPersistence;

import com.traintium.books.model.Chapter;
import com.traintium.books.service.ChapterLocalService;
import com.traintium.books.service.persistence.BookPersistence;
import com.traintium.books.service.persistence.ChapterPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the chapter local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.traintium.books.service.impl.ChapterLocalServiceImpl}.
 * </p>
 *
 * @author aritz
 * @see com.traintium.books.service.impl.ChapterLocalServiceImpl
 * @see com.traintium.books.service.ChapterLocalServiceUtil
 * @generated
 */
public abstract class ChapterLocalServiceBaseImpl extends BaseLocalServiceImpl
	implements ChapterLocalService, IdentifiableBean {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link com.traintium.books.service.ChapterLocalServiceUtil} to access the chapter local service.
	 */

	/**
	 * Adds the chapter to the database. Also notifies the appropriate model listeners.
	 *
	 * @param chapter the chapter
	 * @return the chapter that was added
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Chapter addChapter(Chapter chapter) throws SystemException {
		chapter.setNew(true);

		return chapterPersistence.update(chapter);
	}

	/**
	 * Creates a new chapter with the primary key. Does not add the chapter to the database.
	 *
	 * @param chapterId the primary key for the new chapter
	 * @return the new chapter
	 */
	@Override
	public Chapter createChapter(long chapterId) {
		return chapterPersistence.create(chapterId);
	}

	/**
	 * Deletes the chapter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chapterId the primary key of the chapter
	 * @return the chapter that was removed
	 * @throws PortalException if a chapter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Chapter deleteChapter(long chapterId)
		throws PortalException, SystemException {
		return chapterPersistence.remove(chapterId);
	}

	/**
	 * Deletes the chapter from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chapter the chapter
	 * @return the chapter that was removed
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Chapter deleteChapter(Chapter chapter) throws SystemException {
		return chapterPersistence.remove(chapter);
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(Chapter.class,
			clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return chapterPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.traintium.books.model.impl.ChapterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return chapterPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.traintium.books.model.impl.ChapterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return chapterPersistence.findWithDynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery)
		throws SystemException {
		return chapterPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows that match the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows that match the dynamic query
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
		Projection projection) throws SystemException {
		return chapterPersistence.countWithDynamicQuery(dynamicQuery, projection);
	}

	@Override
	public Chapter fetchChapter(long chapterId) throws SystemException {
		return chapterPersistence.fetchByPrimaryKey(chapterId);
	}

	/**
	 * Returns the chapter with the primary key.
	 *
	 * @param chapterId the primary key of the chapter
	 * @return the chapter
	 * @throws PortalException if a chapter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Chapter getChapter(long chapterId)
		throws PortalException, SystemException {
		return chapterPersistence.findByPrimaryKey(chapterId);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException, SystemException {
		return chapterPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the chapters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.traintium.books.model.impl.ChapterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of chapters
	 * @param end the upper bound of the range of chapters (not inclusive)
	 * @return the range of chapters
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Chapter> getChapters(int start, int end)
		throws SystemException {
		return chapterPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of chapters.
	 *
	 * @return the number of chapters
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int getChaptersCount() throws SystemException {
		return chapterPersistence.countAll();
	}

	/**
	 * Updates the chapter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param chapter the chapter
	 * @return the chapter that was updated
	 * @throws SystemException if a system exception occurred
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Chapter updateChapter(Chapter chapter) throws SystemException {
		return chapterPersistence.update(chapter);
	}

	/**
	 * Returns the book local service.
	 *
	 * @return the book local service
	 */
	public com.traintium.books.service.BookLocalService getBookLocalService() {
		return bookLocalService;
	}

	/**
	 * Sets the book local service.
	 *
	 * @param bookLocalService the book local service
	 */
	public void setBookLocalService(
		com.traintium.books.service.BookLocalService bookLocalService) {
		this.bookLocalService = bookLocalService;
	}

	/**
	 * Returns the book remote service.
	 *
	 * @return the book remote service
	 */
	public com.traintium.books.service.BookService getBookService() {
		return bookService;
	}

	/**
	 * Sets the book remote service.
	 *
	 * @param bookService the book remote service
	 */
	public void setBookService(
		com.traintium.books.service.BookService bookService) {
		this.bookService = bookService;
	}

	/**
	 * Returns the book persistence.
	 *
	 * @return the book persistence
	 */
	public BookPersistence getBookPersistence() {
		return bookPersistence;
	}

	/**
	 * Sets the book persistence.
	 *
	 * @param bookPersistence the book persistence
	 */
	public void setBookPersistence(BookPersistence bookPersistence) {
		this.bookPersistence = bookPersistence;
	}

	/**
	 * Returns the chapter local service.
	 *
	 * @return the chapter local service
	 */
	public com.traintium.books.service.ChapterLocalService getChapterLocalService() {
		return chapterLocalService;
	}

	/**
	 * Sets the chapter local service.
	 *
	 * @param chapterLocalService the chapter local service
	 */
	public void setChapterLocalService(
		com.traintium.books.service.ChapterLocalService chapterLocalService) {
		this.chapterLocalService = chapterLocalService;
	}

	/**
	 * Returns the chapter remote service.
	 *
	 * @return the chapter remote service
	 */
	public com.traintium.books.service.ChapterService getChapterService() {
		return chapterService;
	}

	/**
	 * Sets the chapter remote service.
	 *
	 * @param chapterService the chapter remote service
	 */
	public void setChapterService(
		com.traintium.books.service.ChapterService chapterService) {
		this.chapterService = chapterService;
	}

	/**
	 * Returns the chapter persistence.
	 *
	 * @return the chapter persistence
	 */
	public ChapterPersistence getChapterPersistence() {
		return chapterPersistence;
	}

	/**
	 * Sets the chapter persistence.
	 *
	 * @param chapterPersistence the chapter persistence
	 */
	public void setChapterPersistence(ChapterPersistence chapterPersistence) {
		this.chapterPersistence = chapterPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.service.CounterLocalService getCounterLocalService() {
		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.service.CounterLocalService counterLocalService) {
		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.service.ResourceLocalService getResourceLocalService() {
		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.service.ResourceLocalService resourceLocalService) {
		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.service.UserLocalService getUserLocalService() {
		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.service.UserLocalService userLocalService) {
		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.service.UserService userService) {
		this.userService = userService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	public void afterPropertiesSet() {
		Class<?> clazz = getClass();

		_classLoader = clazz.getClassLoader();

		PersistedModelLocalServiceRegistryUtil.register("com.traintium.books.model.Chapter",
			chapterLocalService);
	}

	public void destroy() {
		PersistedModelLocalServiceRegistryUtil.unregister(
			"com.traintium.books.model.Chapter");
	}

	/**
	 * Returns the Spring bean ID for this bean.
	 *
	 * @return the Spring bean ID for this bean
	 */
	@Override
	public String getBeanIdentifier() {
		return _beanIdentifier;
	}

	/**
	 * Sets the Spring bean ID for this bean.
	 *
	 * @param beanIdentifier the Spring bean ID for this bean
	 */
	@Override
	public void setBeanIdentifier(String beanIdentifier) {
		_beanIdentifier = beanIdentifier;
	}

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		Thread currentThread = Thread.currentThread();

		ClassLoader contextClassLoader = currentThread.getContextClassLoader();

		if (contextClassLoader != _classLoader) {
			currentThread.setContextClassLoader(_classLoader);
		}

		try {
			return _clpInvoker.invokeMethod(name, parameterTypes, arguments);
		}
		finally {
			if (contextClassLoader != _classLoader) {
				currentThread.setContextClassLoader(contextClassLoader);
			}
		}
	}

	protected Class<?> getModelClass() {
		return Chapter.class;
	}

	protected String getModelClassName() {
		return Chapter.class.getName();
	}

	/**
	 * Performs an SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) throws SystemException {
		try {
			DataSource dataSource = chapterPersistence.getDataSource();

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
					sql, new int[0]);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = com.traintium.books.service.BookLocalService.class)
	protected com.traintium.books.service.BookLocalService bookLocalService;
	@BeanReference(type = com.traintium.books.service.BookService.class)
	protected com.traintium.books.service.BookService bookService;
	@BeanReference(type = BookPersistence.class)
	protected BookPersistence bookPersistence;
	@BeanReference(type = com.traintium.books.service.ChapterLocalService.class)
	protected com.traintium.books.service.ChapterLocalService chapterLocalService;
	@BeanReference(type = com.traintium.books.service.ChapterService.class)
	protected com.traintium.books.service.ChapterService chapterService;
	@BeanReference(type = ChapterPersistence.class)
	protected ChapterPersistence chapterPersistence;
	@BeanReference(type = com.liferay.counter.service.CounterLocalService.class)
	protected com.liferay.counter.service.CounterLocalService counterLocalService;
	@BeanReference(type = com.liferay.portal.service.ResourceLocalService.class)
	protected com.liferay.portal.service.ResourceLocalService resourceLocalService;
	@BeanReference(type = com.liferay.portal.service.UserLocalService.class)
	protected com.liferay.portal.service.UserLocalService userLocalService;
	@BeanReference(type = com.liferay.portal.service.UserService.class)
	protected com.liferay.portal.service.UserService userService;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private String _beanIdentifier;
	private ClassLoader _classLoader;
	private ChapterLocalServiceClpInvoker _clpInvoker = new ChapterLocalServiceClpInvoker();
}