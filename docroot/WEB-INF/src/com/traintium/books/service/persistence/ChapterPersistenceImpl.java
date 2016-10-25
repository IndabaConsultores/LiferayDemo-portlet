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

package com.traintium.books.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.traintium.books.NoSuchChapterException;
import com.traintium.books.model.Chapter;
import com.traintium.books.model.impl.ChapterImpl;
import com.traintium.books.model.impl.ChapterModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the chapter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author aritz
 * @see ChapterPersistence
 * @see ChapterUtil
 * @generated
 */
public class ChapterPersistenceImpl extends BasePersistenceImpl<Chapter>
	implements ChapterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ChapterUtil} to access the chapter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ChapterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ChapterModelImpl.ENTITY_CACHE_ENABLED,
			ChapterModelImpl.FINDER_CACHE_ENABLED, ChapterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ChapterModelImpl.ENTITY_CACHE_ENABLED,
			ChapterModelImpl.FINDER_CACHE_ENABLED, ChapterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ChapterModelImpl.ENTITY_CACHE_ENABLED,
			ChapterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_BOOKID = new FinderPath(ChapterModelImpl.ENTITY_CACHE_ENABLED,
			ChapterModelImpl.FINDER_CACHE_ENABLED, ChapterImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByBookId",
			new String[] {
				Long.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BOOKID =
		new FinderPath(ChapterModelImpl.ENTITY_CACHE_ENABLED,
			ChapterModelImpl.FINDER_CACHE_ENABLED, ChapterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByBookId",
			new String[] { Long.class.getName() },
			ChapterModelImpl.BOOKID_COLUMN_BITMASK |
			ChapterModelImpl.NUMERO_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_BOOKID = new FinderPath(ChapterModelImpl.ENTITY_CACHE_ENABLED,
			ChapterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByBookId",
			new String[] { Long.class.getName() });

	/**
	 * Returns all the chapters where bookId = &#63;.
	 *
	 * @param bookId the book ID
	 * @return the matching chapters
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Chapter> findByBookId(long bookId) throws SystemException {
		return findByBookId(bookId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the chapters where bookId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.traintium.books.model.impl.ChapterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param bookId the book ID
	 * @param start the lower bound of the range of chapters
	 * @param end the upper bound of the range of chapters (not inclusive)
	 * @return the range of matching chapters
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Chapter> findByBookId(long bookId, int start, int end)
		throws SystemException {
		return findByBookId(bookId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the chapters where bookId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.traintium.books.model.impl.ChapterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param bookId the book ID
	 * @param start the lower bound of the range of chapters
	 * @param end the upper bound of the range of chapters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching chapters
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Chapter> findByBookId(long bookId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BOOKID;
			finderArgs = new Object[] { bookId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_BOOKID;
			finderArgs = new Object[] { bookId, start, end, orderByComparator };
		}

		List<Chapter> list = (List<Chapter>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Chapter chapter : list) {
				if ((bookId != chapter.getBookId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_CHAPTER_WHERE);

			query.append(_FINDER_COLUMN_BOOKID_BOOKID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(ChapterModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(bookId);

				if (!pagination) {
					list = (List<Chapter>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Chapter>(list);
				}
				else {
					list = (List<Chapter>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first chapter in the ordered set where bookId = &#63;.
	 *
	 * @param bookId the book ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chapter
	 * @throws com.traintium.books.NoSuchChapterException if a matching chapter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Chapter findByBookId_First(long bookId,
		OrderByComparator orderByComparator)
		throws NoSuchChapterException, SystemException {
		Chapter chapter = fetchByBookId_First(bookId, orderByComparator);

		if (chapter != null) {
			return chapter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bookId=");
		msg.append(bookId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChapterException(msg.toString());
	}

	/**
	 * Returns the first chapter in the ordered set where bookId = &#63;.
	 *
	 * @param bookId the book ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching chapter, or <code>null</code> if a matching chapter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Chapter fetchByBookId_First(long bookId,
		OrderByComparator orderByComparator) throws SystemException {
		List<Chapter> list = findByBookId(bookId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last chapter in the ordered set where bookId = &#63;.
	 *
	 * @param bookId the book ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chapter
	 * @throws com.traintium.books.NoSuchChapterException if a matching chapter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Chapter findByBookId_Last(long bookId,
		OrderByComparator orderByComparator)
		throws NoSuchChapterException, SystemException {
		Chapter chapter = fetchByBookId_Last(bookId, orderByComparator);

		if (chapter != null) {
			return chapter;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("bookId=");
		msg.append(bookId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchChapterException(msg.toString());
	}

	/**
	 * Returns the last chapter in the ordered set where bookId = &#63;.
	 *
	 * @param bookId the book ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching chapter, or <code>null</code> if a matching chapter could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Chapter fetchByBookId_Last(long bookId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByBookId(bookId);

		if (count == 0) {
			return null;
		}

		List<Chapter> list = findByBookId(bookId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the chapters before and after the current chapter in the ordered set where bookId = &#63;.
	 *
	 * @param chapterId the primary key of the current chapter
	 * @param bookId the book ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next chapter
	 * @throws com.traintium.books.NoSuchChapterException if a chapter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Chapter[] findByBookId_PrevAndNext(long chapterId, long bookId,
		OrderByComparator orderByComparator)
		throws NoSuchChapterException, SystemException {
		Chapter chapter = findByPrimaryKey(chapterId);

		Session session = null;

		try {
			session = openSession();

			Chapter[] array = new ChapterImpl[3];

			array[0] = getByBookId_PrevAndNext(session, chapter, bookId,
					orderByComparator, true);

			array[1] = chapter;

			array[2] = getByBookId_PrevAndNext(session, chapter, bookId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Chapter getByBookId_PrevAndNext(Session session, Chapter chapter,
		long bookId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_CHAPTER_WHERE);

		query.append(_FINDER_COLUMN_BOOKID_BOOKID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(ChapterModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(bookId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(chapter);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Chapter> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the chapters where bookId = &#63; from the database.
	 *
	 * @param bookId the book ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByBookId(long bookId) throws SystemException {
		for (Chapter chapter : findByBookId(bookId, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(chapter);
		}
	}

	/**
	 * Returns the number of chapters where bookId = &#63;.
	 *
	 * @param bookId the book ID
	 * @return the number of matching chapters
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByBookId(long bookId) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_BOOKID;

		Object[] finderArgs = new Object[] { bookId };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CHAPTER_WHERE);

			query.append(_FINDER_COLUMN_BOOKID_BOOKID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(bookId);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_BOOKID_BOOKID_2 = "chapter.bookId = ?";

	public ChapterPersistenceImpl() {
		setModelClass(Chapter.class);
	}

	/**
	 * Caches the chapter in the entity cache if it is enabled.
	 *
	 * @param chapter the chapter
	 */
	@Override
	public void cacheResult(Chapter chapter) {
		EntityCacheUtil.putResult(ChapterModelImpl.ENTITY_CACHE_ENABLED,
			ChapterImpl.class, chapter.getPrimaryKey(), chapter);

		chapter.resetOriginalValues();
	}

	/**
	 * Caches the chapters in the entity cache if it is enabled.
	 *
	 * @param chapters the chapters
	 */
	@Override
	public void cacheResult(List<Chapter> chapters) {
		for (Chapter chapter : chapters) {
			if (EntityCacheUtil.getResult(
						ChapterModelImpl.ENTITY_CACHE_ENABLED,
						ChapterImpl.class, chapter.getPrimaryKey()) == null) {
				cacheResult(chapter);
			}
			else {
				chapter.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all chapters.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(ChapterImpl.class.getName());
		}

		EntityCacheUtil.clearCache(ChapterImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the chapter.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Chapter chapter) {
		EntityCacheUtil.removeResult(ChapterModelImpl.ENTITY_CACHE_ENABLED,
			ChapterImpl.class, chapter.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Chapter> chapters) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Chapter chapter : chapters) {
			EntityCacheUtil.removeResult(ChapterModelImpl.ENTITY_CACHE_ENABLED,
				ChapterImpl.class, chapter.getPrimaryKey());
		}
	}

	/**
	 * Creates a new chapter with the primary key. Does not add the chapter to the database.
	 *
	 * @param chapterId the primary key for the new chapter
	 * @return the new chapter
	 */
	@Override
	public Chapter create(long chapterId) {
		Chapter chapter = new ChapterImpl();

		chapter.setNew(true);
		chapter.setPrimaryKey(chapterId);

		return chapter;
	}

	/**
	 * Removes the chapter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param chapterId the primary key of the chapter
	 * @return the chapter that was removed
	 * @throws com.traintium.books.NoSuchChapterException if a chapter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Chapter remove(long chapterId)
		throws NoSuchChapterException, SystemException {
		return remove((Serializable)chapterId);
	}

	/**
	 * Removes the chapter with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the chapter
	 * @return the chapter that was removed
	 * @throws com.traintium.books.NoSuchChapterException if a chapter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Chapter remove(Serializable primaryKey)
		throws NoSuchChapterException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Chapter chapter = (Chapter)session.get(ChapterImpl.class, primaryKey);

			if (chapter == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchChapterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(chapter);
		}
		catch (NoSuchChapterException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Chapter removeImpl(Chapter chapter) throws SystemException {
		chapter = toUnwrappedModel(chapter);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(chapter)) {
				chapter = (Chapter)session.get(ChapterImpl.class,
						chapter.getPrimaryKeyObj());
			}

			if (chapter != null) {
				session.delete(chapter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (chapter != null) {
			clearCache(chapter);
		}

		return chapter;
	}

	@Override
	public Chapter updateImpl(com.traintium.books.model.Chapter chapter)
		throws SystemException {
		chapter = toUnwrappedModel(chapter);

		boolean isNew = chapter.isNew();

		ChapterModelImpl chapterModelImpl = (ChapterModelImpl)chapter;

		Session session = null;

		try {
			session = openSession();

			if (chapter.isNew()) {
				session.save(chapter);

				chapter.setNew(false);
			}
			else {
				session.merge(chapter);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !ChapterModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((chapterModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BOOKID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						chapterModelImpl.getOriginalBookId()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BOOKID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BOOKID,
					args);

				args = new Object[] { chapterModelImpl.getBookId() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_BOOKID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_BOOKID,
					args);
			}
		}

		EntityCacheUtil.putResult(ChapterModelImpl.ENTITY_CACHE_ENABLED,
			ChapterImpl.class, chapter.getPrimaryKey(), chapter);

		return chapter;
	}

	protected Chapter toUnwrappedModel(Chapter chapter) {
		if (chapter instanceof ChapterImpl) {
			return chapter;
		}

		ChapterImpl chapterImpl = new ChapterImpl();

		chapterImpl.setNew(chapter.isNew());
		chapterImpl.setPrimaryKey(chapter.getPrimaryKey());

		chapterImpl.setChapterId(chapter.getChapterId());
		chapterImpl.setNumero(chapter.getNumero());
		chapterImpl.setNombre(chapter.getNombre());
		chapterImpl.setBookId(chapter.getBookId());

		return chapterImpl;
	}

	/**
	 * Returns the chapter with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the chapter
	 * @return the chapter
	 * @throws com.traintium.books.NoSuchChapterException if a chapter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Chapter findByPrimaryKey(Serializable primaryKey)
		throws NoSuchChapterException, SystemException {
		Chapter chapter = fetchByPrimaryKey(primaryKey);

		if (chapter == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchChapterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return chapter;
	}

	/**
	 * Returns the chapter with the primary key or throws a {@link com.traintium.books.NoSuchChapterException} if it could not be found.
	 *
	 * @param chapterId the primary key of the chapter
	 * @return the chapter
	 * @throws com.traintium.books.NoSuchChapterException if a chapter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Chapter findByPrimaryKey(long chapterId)
		throws NoSuchChapterException, SystemException {
		return findByPrimaryKey((Serializable)chapterId);
	}

	/**
	 * Returns the chapter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the chapter
	 * @return the chapter, or <code>null</code> if a chapter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Chapter fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Chapter chapter = (Chapter)EntityCacheUtil.getResult(ChapterModelImpl.ENTITY_CACHE_ENABLED,
				ChapterImpl.class, primaryKey);

		if (chapter == _nullChapter) {
			return null;
		}

		if (chapter == null) {
			Session session = null;

			try {
				session = openSession();

				chapter = (Chapter)session.get(ChapterImpl.class, primaryKey);

				if (chapter != null) {
					cacheResult(chapter);
				}
				else {
					EntityCacheUtil.putResult(ChapterModelImpl.ENTITY_CACHE_ENABLED,
						ChapterImpl.class, primaryKey, _nullChapter);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(ChapterModelImpl.ENTITY_CACHE_ENABLED,
					ChapterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return chapter;
	}

	/**
	 * Returns the chapter with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param chapterId the primary key of the chapter
	 * @return the chapter, or <code>null</code> if a chapter with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Chapter fetchByPrimaryKey(long chapterId) throws SystemException {
		return fetchByPrimaryKey((Serializable)chapterId);
	}

	/**
	 * Returns all the chapters.
	 *
	 * @return the chapters
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Chapter> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<Chapter> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the chapters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.traintium.books.model.impl.ChapterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of chapters
	 * @param end the upper bound of the range of chapters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of chapters
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Chapter> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Chapter> list = (List<Chapter>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_CHAPTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CHAPTER;

				if (pagination) {
					sql = sql.concat(ChapterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Chapter>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Chapter>(list);
				}
				else {
					list = (List<Chapter>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the chapters from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Chapter chapter : findAll()) {
			remove(chapter);
		}
	}

	/**
	 * Returns the number of chapters.
	 *
	 * @return the number of chapters
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CHAPTER);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the chapter persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.traintium.books.model.Chapter")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Chapter>> listenersList = new ArrayList<ModelListener<Chapter>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Chapter>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(ChapterImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_CHAPTER = "SELECT chapter FROM Chapter chapter";
	private static final String _SQL_SELECT_CHAPTER_WHERE = "SELECT chapter FROM Chapter chapter WHERE ";
	private static final String _SQL_COUNT_CHAPTER = "SELECT COUNT(chapter) FROM Chapter chapter";
	private static final String _SQL_COUNT_CHAPTER_WHERE = "SELECT COUNT(chapter) FROM Chapter chapter WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "chapter.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Chapter exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Chapter exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(ChapterPersistenceImpl.class);
	private static Chapter _nullChapter = new ChapterImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Chapter> toCacheModel() {
				return _nullChapterCacheModel;
			}
		};

	private static CacheModel<Chapter> _nullChapterCacheModel = new CacheModel<Chapter>() {
			@Override
			public Chapter toEntityModel() {
				return _nullChapter;
			}
		};
}