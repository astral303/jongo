/*
 * Copyright (C) 2011 Benoit GUEROUT <bguerout at gmail dot com> and Yves AMSELLEM <amsellem dot yves at gmail dot com>
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
 */

package org.jongo.marshall.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jongo.Mapper;
import org.jongo.ObjectIdUpdater;
import org.jongo.marshall.Marshaller;
import org.jongo.marshall.Unmarshaller;
import org.jongo.marshall.jackson.configuration.AbstractMappingBuilder;
import org.jongo.marshall.jackson.configuration.Mapping;
import org.jongo.query.JsonQueryFactory;
import org.jongo.query.QueryFactory;

public class JacksonMapper implements Mapper {

    private final JacksonEngine engine;
    private final JacksonObjectIdUpdater objectIdUpdater;
    private final JsonQueryFactory queryFactory;

    private JacksonMapper(Mapping mapping) {
        this.engine = new JacksonEngine(mapping);
        this.queryFactory = new JsonQueryFactory(engine);
        this.objectIdUpdater = new JacksonObjectIdUpdater();
    }

    public Marshaller getMarshaller() {
        return engine;
    }

    public Unmarshaller getUnmarshaller() {
        return engine;
    }

    public ObjectIdUpdater getObjectIdUpdater() {
        return objectIdUpdater;
    }

    public QueryFactory getQueryFactory() {
        return queryFactory;
    }

    public static class Builder extends AbstractMappingBuilder<Builder> {

        public Builder() {
            super();
        }

        public Builder(ObjectMapper mapper) {
            super(mapper);
        }

        public Mapper build() {
            return new JacksonMapper(innerMapping());
        }

        @Override
        protected Builder getBuilderInstance() {
            return this;
        }
    }


}