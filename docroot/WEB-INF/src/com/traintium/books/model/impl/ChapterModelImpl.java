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

package com.traintium.books.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import com.traintium.books.model.Chapter;
import com.traintium.books.model.ChapterModel;
import com.traintium.books.model.ChapterSoap;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the Chapter service. Represents a row in the &quot;book_Chapter&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.traintium.books.model.ChapterModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ChapterImpl}.
 * </p>
 *
 * @author aritz
 * @see ChapterImpl
 * @see com.traintium.books.model.Chapter
 * @see com.traintium.books.model.ChapterModel
 * @generated
 */
@JSON(strict = true)
public class ChapterModelImpl extends BaseModelImpl<Chapter>
	implements ChapterModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a chapter model instance should use the {@link com.traintium.books.model.Chapter} interface instead.
	 */
	public static final String TABLE_NAME = "book_Chapter";
	public static final Object[][] TABLE_COLUMNS = {
			{ "chapterId", Types.BIGINT },
			{ "numero", Types.INTEGER },
			{ "nombre", Types.VARCHAR },
			{ "bookId", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table book_Chapter (chapterId LONG not null primary key,numero INTEGER,nombre VARCHAR(75) null,bookId LONG)";
	public static final String TABLE_SQL_DROP = "drop table book_Chapter";
	public static final String ORDER_BY_JPQL = " ORDER BY chapter.numero ASC";
	public static final String ORDER_BY_SQL = " ORDER BY book_Chapter.numero ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.traintium.books.model.Chapter"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.traintium.books.model.Chapter"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.traintium.books.model.Chapter"),
			true);
	public static long BOOKID_COLUMN_BITMASK = 1L;
	public static long NUMERO_COLUMN_BITMASK = 2L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static Chapter toModel(ChapterSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		Chapter model = new ChapterImpl();

		model.setChapterId(soapModel.getChapterId());
		model.setNumero(soapModel.getNumero());
		model.setNombre(soapModel.getNombre());
		model.setBookId(soapModel.getBookId());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<Chapter> toModels(ChapterSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<Chapter> models = new ArrayList<Chapter>(soapModels.length);

		for (ChapterSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.traintium.books.model.Chapter"));

	public ChapterModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _chapterId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setChapterId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _chapterId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Chapter.class;
	}

	@Override
	public String getModelClassName() {
		return Chapter.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("chapterId", getChapterId());
		attributes.put("numero", getNumero());
		attributes.put("nombre", getNombre());
		attributes.put("bookId", getBookId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long chapterId = (Long)attributes.get("chapterId");

		if (chapterId != null) {
			setChapterId(chapterId);
		}

		Integer numero = (Integer)attributes.get("numero");

		if (numero != null) {
			setNumero(numero);
		}

		String nombre = (String)attributes.get("nombre");

		if (nombre != null) {
			setNombre(nombre);
		}

		Long bookId = (Long)attributes.get("bookId");

		if (bookId != null) {
			setBookId(bookId);
		}
	}

	@JSON
	@Override
	public long getChapterId() {
		return _chapterId;
	}

	@Override
	public void setChapterId(long chapterId) {
		_chapterId = chapterId;
	}

	@JSON
	@Override
	public int getNumero() {
		return _numero;
	}

	@Override
	public void setNumero(int numero) {
		_columnBitmask = -1L;

		_numero = numero;
	}

	@JSON
	@Override
	public String getNombre() {
		if (_nombre == null) {
			return StringPool.BLANK;
		}
		else {
			return _nombre;
		}
	}

	@Override
	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	@JSON
	@Override
	public long getBookId() {
		return _bookId;
	}

	@Override
	public void setBookId(long bookId) {
		_columnBitmask |= BOOKID_COLUMN_BITMASK;

		if (!_setOriginalBookId) {
			_setOriginalBookId = true;

			_originalBookId = _bookId;
		}

		_bookId = bookId;
	}

	public long getOriginalBookId() {
		return _originalBookId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Chapter.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Chapter toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Chapter)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		ChapterImpl chapterImpl = new ChapterImpl();

		chapterImpl.setChapterId(getChapterId());
		chapterImpl.setNumero(getNumero());
		chapterImpl.setNombre(getNombre());
		chapterImpl.setBookId(getBookId());

		chapterImpl.resetOriginalValues();

		return chapterImpl;
	}

	@Override
	public int compareTo(Chapter chapter) {
		int value = 0;

		if (getNumero() < chapter.getNumero()) {
			value = -1;
		}
		else if (getNumero() > chapter.getNumero()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Chapter)) {
			return false;
		}

		Chapter chapter = (Chapter)obj;

		long primaryKey = chapter.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		ChapterModelImpl chapterModelImpl = this;

		chapterModelImpl._originalBookId = chapterModelImpl._bookId;

		chapterModelImpl._setOriginalBookId = false;

		chapterModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Chapter> toCacheModel() {
		ChapterCacheModel chapterCacheModel = new ChapterCacheModel();

		chapterCacheModel.chapterId = getChapterId();

		chapterCacheModel.numero = getNumero();

		chapterCacheModel.nombre = getNombre();

		String nombre = chapterCacheModel.nombre;

		if ((nombre != null) && (nombre.length() == 0)) {
			chapterCacheModel.nombre = null;
		}

		chapterCacheModel.bookId = getBookId();

		return chapterCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{chapterId=");
		sb.append(getChapterId());
		sb.append(", numero=");
		sb.append(getNumero());
		sb.append(", nombre=");
		sb.append(getNombre());
		sb.append(", bookId=");
		sb.append(getBookId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.traintium.books.model.Chapter");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>chapterId</column-name><column-value><![CDATA[");
		sb.append(getChapterId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>numero</column-name><column-value><![CDATA[");
		sb.append(getNumero());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>nombre</column-name><column-value><![CDATA[");
		sb.append(getNombre());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>bookId</column-name><column-value><![CDATA[");
		sb.append(getBookId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Chapter.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			Chapter.class
		};
	private long _chapterId;
	private int _numero;
	private String _nombre;
	private long _bookId;
	private long _originalBookId;
	private boolean _setOriginalBookId;
	private long _columnBitmask;
	private Chapter _escapedModel;
}