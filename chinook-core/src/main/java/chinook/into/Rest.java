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

import frege.run7.Func;
import frege.run7.Thunk;
import frege.runtime.Phantom.RealWorld;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Filter;

/**
 * Helper class for creating instances from lambda expressions
 *
 * @since 1.0.0
 *
 */
public final class Rest {

    /**
     * @since 0.2.1
     */
    static class Builder<A> {

        private final Request request;
        private final Response response;

        /**
         * @param request
         * @param response
         * @since 0.2.1
         */
        public Builder(final Request request, final Response response) {
            this.request = request;
            this.response = response;
        }

        /**
         * @param fn
         * @return
         * @since 0.2.1
         */
        public A execute(final Func.U<Request, Func.U<Response, Func.U<RealWorld, A>>> fn) {
            return fn
                .apply(Thunk.<Request>lazy(request))   // First parameter
                .call()
                .apply(Thunk.<Response>lazy(response)) // Second parameter
                .call()
                .apply(null)                           // Execute IO to get value
                .call();
        }
    }

    /**
     * Creates a new {@link Route} instance from a {@link Func.U} expression.
     *
     * @param fn The function representing the behavior of the
     * {@link Route#handle(spark.Request, spark.Response) } method
     */
    public static <A> spark.Route createRoute(final Func.U<Request, Func.U<Response, Func.U<RealWorld, A>>> fn) {
        return new Route() {
            @Override
            public Object handle(final Request request, final Response response) throws Exception {
                return new Builder<A>(request, response).execute(fn);
            }
        };
    }

    /**
     * Creates a new {@link Route} instance from a {@link Func.U} expression.
     *
     * @param fn The function representing the behavior of the
     * {@link Route#handle(spark.Request, spark.Response) } method
     * @since 0.2.1
     */
    public static spark.Filter createFilter(final Func.U<Request, Func.U<Response, Func.U<RealWorld, Short>>> fn) {
        return new Filter() {
            @Override
            public void handle(final Request request, final Response response) throws Exception {
                new Builder<Short>(request, response).execute(fn);
            }
        };
    }
}
