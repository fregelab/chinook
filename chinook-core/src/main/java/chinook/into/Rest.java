/*
 * Copyright [2015] [Janus Lynd]
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
package chinook.into;

import frege.runtime.Lambda;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Helper class for creating instances from lambda expressions
 *
 * @since 1.0.0
 *
 */
public final class Rest {

    /**
     * Creates a new {@link Route} instance from a {@link Lambda} expression.
     *
     * @param fn The function representing the behavior of the
     * {@link Route#handle(spark.Request, spark.Response) } method
     */
    public static spark.Route createRoute(final Lambda fn) {
        return new Route() {
            @Override
            public Object handle(final Request request, final Response response) throws Exception {
                return fn.apply(request)
                         .apply(response)
                         .apply(null) // Forces IO to extract its value
                         .result()
                         .forced();
            }
        };
    }

}
