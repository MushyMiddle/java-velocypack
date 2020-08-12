/*
 * DISCLAIMER
 *
 * Copyright 2016 ArangoDB GmbH, Cologne, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright holder is ArangoDB GmbH, Cologne, Germany
 */

package com.arangodb.velocypack.internal;

import com.arangodb.velocypack.VPackDeserializationContext;
import com.arangodb.velocypack.VPackDeserializer;
import com.arangodb.velocypack.VPackSlice;
import com.arangodb.velocypack.exception.VPackException;
import com.arangodb.velocypack.exception.VPackParserException;
import com.arangodb.velocypack.internal.util.DateUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * @author Mark Vollmary
 *
 */
public class VPackDeserializers {

	private VPackDeserializers() {
		super();
	}

	public static final VPackDeserializer<String> STRING = new VPackDeserializer<String>() {
		@Override
		public String deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return vpack.getAsString();
		}
	};
	public static final VPackDeserializer<Boolean> BOOLEAN = new VPackDeserializer<Boolean>() {
		@Override
		public Boolean deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return vpack.getAsBoolean();
		}
	};
	public static final VPackDeserializer<Integer> INTEGER = new VPackDeserializer<Integer>() {
		@Override
		public Integer deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return vpack.getAsInt();
		}
	};
	public static final VPackDeserializer<Long> LONG = new VPackDeserializer<Long>() {
		@Override
		public Long deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return vpack.getAsLong();
		}
	};
	public static final VPackDeserializer<Short> SHORT = new VPackDeserializer<Short>() {
		@Override
		public Short deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return vpack.getAsShort();
		}
	};
	public static final VPackDeserializer<Double> DOUBLE = new VPackDeserializer<Double>() {
		@Override
		public Double deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return vpack.getAsDouble();
		}
	};
	public static final VPackDeserializer<Float> FLOAT = new VPackDeserializer<Float>() {
		@Override
		public Float deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return vpack.getAsFloat();
		}
	};
	public static final VPackDeserializer<BigInteger> BIG_INTEGER = new VPackDeserializer<BigInteger>() {
		@Override
		public BigInteger deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return vpack.getAsBigInteger();
		}
	};
	public static final VPackDeserializer<BigDecimal> BIG_DECIMAL = new VPackDeserializer<BigDecimal>() {
		@Override
		public BigDecimal deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return vpack.getAsBigDecimal();
		}
	};
	public static final VPackDeserializer<Number> NUMBER = new VPackDeserializer<Number>() {
		@Override
		public Number deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return vpack.getAsNumber();
		}
	};
	public static final VPackDeserializer<Character> CHARACTER = new VPackDeserializer<Character>() {
		@Override
		public Character deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return vpack.getAsChar();
		}
	};
	public static final VPackDeserializer<Date> DATE = new VPackDeserializer<Date>() {
		@Override
		public Date deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			final Date date;
			if (vpack.isString()) {
				try {
					date = DateUtil.parse(vpack.getAsString());
				} catch (final ParseException e) {
					throw new VPackParserException(e);
				}
			} else {
				date = vpack.getAsDate();
			}
			return date;
		}
	};
	public static final VPackDeserializer<java.sql.Date> SQL_DATE = new VPackDeserializer<java.sql.Date>() {

		@Override
		public java.sql.Date deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			final java.sql.Date date;
			if (vpack.isString()) {
				try {
					date = new java.sql.Date(DateUtil.parse(vpack.getAsString()).getTime());
				} catch (final ParseException e) {
					throw new VPackParserException(e);
				}
			} else {
				date = vpack.getAsSQLDate();
			}
			return date;
		}
	};
	public static final VPackDeserializer<java.sql.Timestamp> SQL_TIMESTAMP = new VPackDeserializer<Timestamp>() {
		@Override
		public Timestamp deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			final java.sql.Timestamp date;
			if (vpack.isString()) {
				try {
					date = new java.sql.Timestamp(DateUtil.parse(vpack.getAsString()).getTime());
				} catch (final ParseException e) {
					throw new VPackParserException(e);
				}
			} else {
				date = vpack.getAsSQLTimestamp();
			}
			return date;
		}
	};
	public static final VPackDeserializer<VPackSlice> VPACK = new VPackDeserializer<VPackSlice>() {
		@Override
		public VPackSlice deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return vpack;
		}
	};
	public static final VPackDeserializer<UUID> UUID = new VPackDeserializer<UUID>() {
		@Override
		public UUID deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return java.util.UUID.fromString(vpack.getAsString());
		}
	};
	public static final VPackDeserializer<byte[]> BYTE_ARRAY = new VPackDeserializer<byte[]>() {
		@Override
		public byte[] deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) throws VPackException {
			return Base64.getDecoder().decode(vpack.getAsString());
		}

	};
	public static final VPackDeserializer<Byte> BYTE = new VPackDeserializer<Byte>() {
		@Override
		public Byte deserialize(
			final VPackSlice parent,
			final VPackSlice vpack,
			final VPackDeserializationContext context) {
			return vpack.getAsByte();
		}
	};

}
