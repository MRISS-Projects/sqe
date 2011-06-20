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
package org.nbheaven.sqe.tools.pmd.codedefects.core.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import org.nbheaven.sqe.tools.pmd.codedefects.core.PMDSession;
import org.nbheaven.sqe.tools.pmd.codedefects.core.ui.result.ResultPanel;
import org.nbheaven.sqe.core.api.SQEManager;
import org.netbeans.api.project.Project;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
public final class PMDTopComponent extends TopComponent {

    private static PMDTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "org/nbheaven/sqe/tools/pmd/codedefects/core/resources/pmd.png";
    private static final String PREFERRED_ID = "PMDTopComponent";
    private SQEManagerListener sqeManagerListener;
    private PMDSession activeSession = null;
    private final JComponent emptyComponent;

    private PMDTopComponent() {
        sqeManagerListener = new SQEManagerListener(this);
        initComponents();

        emptyComponent = new JLabel("<No Results available>");
        emptyComponent.setBackground(Color.WHITE);

        setName(NbBundle.getMessage(PMDTopComponent.class, "CTL_PMDTopComponent"));
        setToolTipText(NbBundle.getMessage(PMDTopComponent.class, "HINT_PMDTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
//        putClientProperty("netbeans.winsys.tc.keep_preferred_size_when_slided_in", Boolean.TRUE);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resultPanel = new javax.swing.JPanel();
        actionsToolbar = new javax.swing.JToolBar();

        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        setLayout(new java.awt.BorderLayout());

        resultPanel.setLayout(new java.awt.BorderLayout());
        add(resultPanel, java.awt.BorderLayout.CENTER);

        actionsToolbar.setFloatable(false);
        actionsToolbar.setOrientation(1);
        actionsToolbar.setRollover(true);
        add(actionsToolbar, java.awt.BorderLayout.WEST);
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_formAncestorAdded

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar actionsToolbar;
    private javax.swing.JPanel resultPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized PMDTopComponent getDefault() {
        if (instance == null) {
            instance = new PMDTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the PMDTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized PMDTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(PMDTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof PMDTopComponent) {
            return (PMDTopComponent) win;
        }
        Logger.getLogger(PMDTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID +
                "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        SQEManager.getDefault().addPropertyChangeListener(SQEManager.PROP_ACTIVE_PROJECT, sqeManagerListener);
        setActiveSessionByProject(SQEManager.getDefault().getActiveProject());
    }

    @Override
    public void componentClosed() {
        SQEManager.getDefault().removePropertyChangeListener(SQEManager.PROP_ACTIVE_PROJECT, sqeManagerListener);
        setActiveSession(null);
    }

    /** replaces this in object stream */
    @Override
    public Object writeReplace() {
        return new ResolvableHelper();
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    private void setActiveSessionByProject(final Project project) {
        if (!EventQueue.isDispatchThread()) {
            EventQueue.invokeLater(new Runnable() {

                public void run() {
                    setActiveSessionByProject(project);
                }
            });
            return;
        }

        if (null != project) {
            setActiveSession(project.getLookup().lookup(PMDSession.class));
            AbstractNode node = new AbstractNode(Children.LEAF, Lookups.singleton(project));
            setActivatedNodes(new Node[]{node});
        } else {
            setActiveSession(null);
            setActivatedNodes(new Node[0]);
        }
    }

    private void setActiveSession(final PMDSession session) {
        assert EventQueue.isDispatchThread() : "setActiveSession has to be called on EventQueue";
        if (activeSession != session) {
            this.activeSession = session;

            JComponent component;
            if (null != activeSession) {
                component = new ResultPanel(activeSession);
            } else {
                component = emptyComponent;
            }

            resultPanel.removeAll();
            resultPanel.add(component, BorderLayout.CENTER);
            resultPanel.invalidate();
            revalidate();
            repaint();
        }
    }

    private static class SQEManagerListener implements PropertyChangeListener {

        private final PMDTopComponent component;

        private SQEManagerListener(PMDTopComponent component) {
            this.component = component;
        }

        public void propertyChange(PropertyChangeEvent evt) {
            if (SQEManager.PROP_ACTIVE_PROJECT.equals(evt.getPropertyName())) {
                component.setActiveSessionByProject((Project) evt.getNewValue());
            }
        }
    }

    final static class ResolvableHelper implements Serializable {

        private static final long serialVersionUID = 1L;

        public Object readResolve() {
            return PMDTopComponent.getDefault();
        }
    }
}
