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
package org.nbheaven.sqe.tools.checkstyle.codedefects.core;

import java.util.Collection;
import org.nbheaven.sqe.core.java.utils.FileObjectUtilities;
import org.nbheaven.sqe.core.java.utils.ProjectUtilities;
import org.netbeans.api.project.SourceGroup;
import org.openide.filesystems.FileObject;

/**
 *
 * @author fvo
 */
final class CheckstyleProjectScannerJob extends CheckstyleScannerJob {

    private CheckstyleSession session;

    public CheckstyleProjectScannerJob(CheckstyleSession session) {
        super(session.getProject());
        this.session = session;
    }

    @Override
    protected void executeCheckstyle() {
        SourceGroup[] groups = ProjectUtilities.getJavaSourceGroups(getProject());
        for (SourceGroup g : groups) {
            FileObject rootOfSourceFolder = g.getRootFolder();
            Collection<FileObject> fullList = FileObjectUtilities.collectAllJavaSourceFiles(rootOfSourceFolder);
            executeCheckstyle(fullList);
        }
    }

    @Override
    protected final void postScan() {
        session.setResult(getCheckstyleResult());
        super.postScan();
        session.scanningDone();
    }
}
