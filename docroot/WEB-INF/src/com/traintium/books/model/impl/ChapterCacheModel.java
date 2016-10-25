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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.traintium.books.model.Chapter;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Chapter in entity cache.
 *
 * @author aritz
 * @see Chapter
 * @generated
 */
public class ChapterCacheModel implements CacheModel<Chapter>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{chapterId=");
		sb.append(chapterId);
		sb.append(", numero=");
		sb.append(numero);
		sb.append(", nombre=");
		sb.append(nombre);
		sb.append(", bookId=");
		sb.append(bookId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Chapter toEntityModel() {
		ChapterImpl chapterImpl = new ChapterImpl();

		chapterImpl.setChapterId(chapterId);
		chapterImpl.setNumero(numero);

		if (nombre == null) {
			chapterImpl.setNombre(StringPool.BLANK);
		}
		else {
			chapterImpl.setNombre(nombre);
		}

		chapterImpl.setBookId(bookId);

		chapterImpl.resetOriginalValues();

		return chapterImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		chapterId = objectInput.readLong();
		numero = objectInput.readInt();
		nombre = objectInput.readUTF();
		bookId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(chapterId);
		objectOutput.writeInt(numero);

		if (nombre == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(nombre);
		}

		objectOutput.writeLong(bookId);
	}

	public long chapterId;
	public int numero;
	public String nombre;
	public long bookId;
}