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
package org.nbheaven.sqe.tools.pmd.codedefects.core.ui.result;

import javax.swing.tree.DefaultMutableTreeNode;
import org.nbheaven.sqe.tools.pmd.codedefects.core.PMDSession;

/**
 *
 * @author Florian Vogler
 */
class SessionNode extends DefaultMutableTreeNode {

    private PMDSession session;
    private long bugCount;

    /** Creates a new instance of BugInstanceNode */
    SessionNode(PMDSession session, long bugCount) {
        super(session, true);
        this.session = session;
        this.bugCount = bugCount;
    }

    PMDSession getSession() {
        return session;
    }

    long getBugCount() {
        return bugCount;
    }
}