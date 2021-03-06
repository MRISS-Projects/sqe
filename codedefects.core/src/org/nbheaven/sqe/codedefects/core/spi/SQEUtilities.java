/* Copyright 2005,2006 Sven Reimers, Florian Vogler
 *
 * This file is part of the Software Quality Environment Project.
 *
 * The Software Quality Environment Project is free software:
 * you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation,
 * either version 2 of the License, or (at your option) any later version.
 *
 * The Software Quality Environment Project is distributed in the hope that
 * it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.nbheaven.sqe.codedefects.core.spi;

import java.util.Collection;
import org.nbheaven.sqe.codedefects.core.api.QualityProvider;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

public class SQEUtilities {

    private SQEUtilities() {}

    private static final Lookup.Result<QualityProvider> providers = Lookups.forPath("SQE/Providers/CodeDefects").lookupResult(QualityProvider.class);

    public static Collection<? extends QualityProvider> getProviders() {
        return providers.allInstances();
    }

}
