/*
 * Copyright 2008 Niclas Hedhman. All rights Reserved.
 *
 * Licensed  under the  Apache License,  Version 2.0  (the "License");
 * you may not use  this file  except in  compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed  under the  License is distributed on an "AS IS" BASIS,
 * WITHOUT  WARRANTIES OR CONDITIONS  OF ANY KIND, either  express  or
 * implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sql.generation.api.vendor.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * <p>
 * This is a copy from file located Qi4j's bootstrap project, since there is no java.util.ServiceLoader in Java 1.5.
 * This is from org.qi4j.bootstrap.internal package.
 * </p>
 *
 * <p>
 * Update 27.9.2010 - I (Stanislav Muhametsin) made a small syntactical change to private/protected methods in order to
 * remove compile warnings.
 * </p>
 *
 * @see http://download.oracle.com/javase/6/docs/api/java/util/ServiceLoader.html
 */
public final class ServiceLoader {
    private final static LinkedList<ClassLoader> loaders = new LinkedList<>();

    static {
        ServiceLoader.addClassloader(ServiceLoader.class.getClassLoader());
    }

    public static void addClassloader(final ClassLoader loader) {
        ServiceLoader.loaders.add(loader);
    }

    public static void removeClassloader(final ClassLoader loader) {
        ServiceLoader.loaders.remove(loader);
    }

    public <T> Iterable<T> providers(final Class<T> neededType)
            throws IOException {
        final LinkedList<T> result = new LinkedList<>();
        for (final ClassLoader loader : ServiceLoader.loaders) {
            final Enumeration<?> cfg = loader.getResources("META-INF/services/" + neededType.getName());
            while (cfg.hasMoreElements()) {
                final URL rc = (URL) cfg.nextElement();
                this.processResource(loader, result, rc, neededType);
            }
        }
        return result;
    }

    public <T> T firstProvider(final Class<T> neededType)
            throws IOException {
        final Iterator<T> allProviders = this.providers(neededType).iterator();
        if (allProviders.hasNext()) {
            return allProviders.next();
        }
        System.err.println("No provider found for " + neededType + ".");
        return null;
    }

    private <T> void processResource(final ClassLoader classLoader, final LinkedList<T> result, final URL rc, final Class<T> neededType)
            throws IOException {
        InputStream in = null;
        BufferedReader rd = null;
        try {
            in = rc.openStream();
            rd = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

            String line = rd.readLine();
            while (null != line) {
                final String providerClassName = this.trimLine(line);
                if (line.length() > 0) {
                    this.processProvider(result, classLoader, providerClassName, neededType);
                }
                line = rd.readLine();
            }
        } finally {
            if (rd != null) {
                rd.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    private String trimLine(String line) {
        final int hashPos = line.indexOf('#');
        if (hashPos >= 0) {
            line = line.substring(0, hashPos);
        }
        line = line.trim();
        return line;
    }

    private <T> void processProvider(final LinkedList<T> result, final ClassLoader classLoader, final String providerClassName,
                                     final Class<T> neededType) {
        final Class<? extends T> provider = this.loadProvider(classLoader, providerClassName, neededType);
        if (provider == null) {
            return;
        }
        final T instance = this.instantiateProvider(provider);
        if (instance == null) {
            return;
        }
        if (!result.contains(instance)) {
            result.add(instance);
        }
    }

    private <T> Class<? extends T> loadProvider(final ClassLoader ldr, final String providerClassName, final Class<T> neededType) {
        try {
            final Class<?> providerClass = ldr.loadClass(providerClassName);
            return providerClass.asSubclass(neededType);
        } catch (final ClassCastException ex) {
            System.err.println("Class " + providerClassName + " was not of " + neededType.getName() + " subtype.");
        } catch (final ClassNotFoundException ex) {
            System.err.println("Class " + providerClassName + " was not found. Skipping.");
        }
        return null;
    }

    private <T> T instantiateProvider(final Class<T> provider) {
        try {
            return provider.newInstance();
        } catch (final InstantiationException ex) {
            System.err.println("Class " + provider.getName() + " is an interface or abstract class.");
        } catch (final IllegalAccessException ex) {
            System.err.println("Class " + provider.getName()
                    + " is not accessible. Make sure it is public and have a public no-args constructor.");
        }
        return null;
    }
}
