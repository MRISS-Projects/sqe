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
package org.nbheaven.sqe.tools.findbugs.codedefects.core.option;

import edu.umd.cs.findbugs.config.UserPreferences;
import edu.umd.cs.findbugs.L10N;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import edu.umd.cs.findbugs.BugPattern;
import edu.umd.cs.findbugs.DetectorFactory;
import edu.umd.cs.findbugs.DetectorFactoryCollection;
import edu.umd.cs.findbugs.Plugin;
import java.awt.Component;
import java.util.Vector;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.text.html.HTMLEditorKit;
import org.nbheaven.sqe.tools.findbugs.codedefects.core.settings.FindBugsSettings;

/**
 * Detector Configuration Panel
 *
 * @author Sven Reimers
 */
public class ConfigureDetectorsPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final int SPEED_COLUMN = 1;
    private static final int ENABLED_COLUMN = 2;

    private DefaultComboBoxModel<Plugin> pluginSelector;

    private ListCellRenderer<Object> pluginRenderer;

    private UserPreferences userPreferences = null;

    /**
     * Creates new form ConfigureDetectorsPanel
     */
    public ConfigureDetectorsPanel() {
        this(FindBugsSettings.getUserPreferences());
    }

    /**
     * Creates new form ConfigureDetectorsPanel
     */
    public ConfigureDetectorsPanel(UserPreferences userPreferences) {
        // the following line is needed to ensure plugins can be found !!
        preInitComponents();
        initComponents();
        setUserPreferences(userPreferences);
    }

    private void preInitComponents() {
        Vector<Plugin> plugins = new Vector<Plugin>();
        for (Iterator<Plugin> pluginIterator = DetectorFactoryCollection.instance().pluginIterator(); pluginIterator.hasNext();) {
            Plugin plugin = pluginIterator.next();
            plugins.add(plugin);
        }
        pluginSelector = new DefaultComboBoxModel<Plugin>(plugins);

        pluginRenderer = new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Object o = value;
                if (null != value) {
                    Plugin plugin = (Plugin) value;
                    o = plugin.getProvider() + "(" + plugin.getPluginId() + ")";
                }
                return super.getListCellRendererComponent(list, o, index, isSelected, cellHasFocus);
            }
        };
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
        postInitComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        detectorTableScrollPane = new javax.swing.JScrollPane();
        detectorTable = new javax.swing.JTable();
        detectorDescriptionScrollPane = new javax.swing.JScrollPane();
        detectorDescription = new javax.swing.JEditorPane();
        jComboBox1 = new javax.swing.JComboBox<Plugin>();
        restoreDefaultsButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        restoreDefaultsButton1 = new javax.swing.JButton();

        detectorTableScrollPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        detectorTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Bug Detector", "Speed", "Enabled"
            }
        ) {
            Class<?>[] types = new Class<?> [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class<?> getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        populateTable();
        detectorTable.getColumnModel().getColumn(ENABLED_COLUMN).setMaxWidth(60);
        detectorTable.getColumnModel().getColumn(SPEED_COLUMN).setMaxWidth(60);
        detectorTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        {
            DefaultTableModel m = (DefaultTableModel)detectorTable.getModel();
            m.setColumnIdentifiers( new String[]
                {
                    L10N.getLocalString("dlg.bugdetector_lbl", "Bug Detector"),
                    L10N.getLocalString("dlg.speed_lbl", "Speed"),
                    L10N.getLocalString("dlg.enabled_lbl", "Enabled"),
                });

                //DefaultSortedTableModel sortedModel = new DefaultSortedTableModel(m, detectorTable.getTableHeader());
                //detectorTable.setModel(sortedModel);
            }
            detectorTableScrollPane.setViewportView(detectorTable);

            detectorDescriptionScrollPane.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
            detectorDescriptionScrollPane.setPreferredSize(new java.awt.Dimension(110, 120));

            detectorDescription.setEditorKit(new HTMLEditorKit());
            detectorDescriptionScrollPane.setViewportView(detectorDescription);

            jComboBox1.setModel(pluginSelector);
            jComboBox1.setRenderer(pluginRenderer);
            jComboBox1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jComboBox1ActionPerformed(evt);
                }
            });

            restoreDefaultsButton.setText("Restore Defaults");
            restoreDefaultsButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            restoreDefaultsButton.setText(L10N.getLocalString("dlg.restoredefaults_btn", "Restore Defaults"));
            restoreDefaultsButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    restoreDefaultsButtonActionPerformed(evt);
                }
            });

            jLabel1.setText("Plugin Id");

            restoreDefaultsButton1.setText("Disable All Detectors");
            restoreDefaultsButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            restoreDefaultsButton.setText(L10N.getLocalString("dlg.restoredefaults_btn", "Restore Defaults"));
            restoreDefaultsButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    disableAllButton1ActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(detectorDescriptionScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                        .addComponent(detectorTableScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jComboBox1, 0, 492, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(restoreDefaultsButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(restoreDefaultsButton1)))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(detectorTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(detectorDescriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(restoreDefaultsButton)
                        .addComponent(restoreDefaultsButton1))
                    .addContainerGap())
            );
        }// </editor-fold>//GEN-END:initComponents

    private void disableAllButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disableAllButton1ActionPerformed
        Plugin plugin = (Plugin) jComboBox1.getSelectedItem();
        Iterator<DetectorFactory> i = plugin.getDetectorFactories().iterator();
        TableModel model = detectorTable.getModel();
        int row = 0;
        while (i.hasNext()) {
            DetectorFactory factory = i.next();
            if (!factory.isHidden()) {
                model.setValueAt(false, row++, ENABLED_COLUMN);
            }
        }
    }//GEN-LAST:event_disableAllButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        applyDetectorChangesToUserPreferences(userPreferences);
        populateTable();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * reverts the selected state of all the detectors to their defaults as
     * specified in the findbugs.xml file
     *
     * @param evt the swing event corresponding to the mouse click of the
     * Restore Defaults button
     */
	private void restoreDefaultsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreDefaultsButtonActionPerformed
            Plugin plugin = (Plugin) jComboBox1.getSelectedItem();
            Iterator<DetectorFactory> i = plugin.getDetectorFactories().iterator();
            TableModel model = detectorTable.getModel();
            int row = 0;
            while (i.hasNext()) {
                DetectorFactory factory = i.next();
                if (!factory.isHidden()) {
                    model.setValueAt(factory.isDefaultEnabled() ? Boolean.TRUE : Boolean.FALSE, row++, ENABLED_COLUMN);
                }
            }
	}//GEN-LAST:event_restoreDefaultsButtonActionPerformed

    /**
     * installs a list selection listener to populate the bottom details page
     * based on selection changes in top grid. A conversion from the table
     * sorter index to the base model index is done to get the correct details
     */
    private void postInitComponents() {
        // Listen to detector table selections so we can (hopefully)
        // display the description of the detector

        ListSelectionModel rowSM = detectorTable.getSelectionModel();
        rowSM.addListSelectionListener(new DetectorListListener());

        populateTable();
    }

    /**
     * populates the bottom detector details pane based on the detector selected
     *
     * @param factory the detector that is currently selected
     */
    private void viewDetectorDetails(DetectorFactory factory) {
        String detailHTML = factory.getDetailHTML();
        if (detailHTML == null) {
            detectorDescription.setText("");
        } else {
            detectorDescription.setContentType("text/html");
            detectorDescription.setText(detailHTML);
            StringBuffer toolTip = new StringBuffer(100);
            toolTip.append("<html><body><b>");
            toolTip.append(factory.getFullName());
            toolTip.append("</b><br><br><table border='1' width='100%'><tr><th>");
            toolTip.append(L10N.getLocalString("msg.bugpatternsreported_txt", "Bug Patterns Reported"));
            toolTip.append("</th></tr>");

            Collection<BugPattern> patterns = factory.getReportedBugPatterns();
            for (BugPattern pattern : patterns) {
                toolTip.append("<tr><td align='center'>");
                toolTip.append("[");
                toolTip.append(pattern.getAbbrev());
                toolTip.append("] ");
                toolTip.append(pattern.getType());
                toolTip.append("</td></tr>");
            }
            toolTip.append("</body></html>");
            detectorDescription.setToolTipText(toolTip.toString());
        }
    }

    /**
     * populates the Detector JTable model with all available detectors Due to
     * Netbeans form builder, populate table gets called before the tablesorter
     * is installed, so it is correct for the model retrieved from the table to
     * be assumed to be the base DefaultTableModel.
     */
    private void populateTable() {
        UserPreferences prefs = userPreferences;
        DefaultTableModel model = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"Bug Detector", "Speed", "Enabled"}
        ) {
            Class<?>[] types = new Class<?>[]{
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, true
            };

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        model.setColumnIdentifiers(new String[]{
            L10N.getLocalString("dlg.bugdetector_lbl", "Bug Detector"),
            L10N.getLocalString("dlg.speed_lbl", "Speed"),
            L10N.getLocalString("dlg.enabled_lbl", "Enabled"),});

        Plugin plugin = (Plugin) jComboBox1.getSelectedItem();

        if (null == plugin) {
            detectorTable.setModel(model);
            return;
        } else {
            factoryList.clear();
            Iterator<DetectorFactory> i = plugin.getDetectorFactories().iterator();
            while (i.hasNext()) {
                DetectorFactory factory = i.next();
                if (!factory.isHidden()) {
                    model.addRow(new Object[]{
                        factory.getShortName(),
                        factory.getSpeed(),
                        prefs.isDetectorEnabled(factory)
                        ? Boolean.TRUE : Boolean.FALSE
                    });
                    factoryList.add(factory);
                }
            }
        }

        detectorTable.setModel(model);
    }

    public void applyDetectorChangesToUserPreferences(UserPreferences userPreferences) {
        // Update new enabled/disabled status for the Detectors
        int num = factoryList.size();
        TableModel model = detectorTable.getModel();
        for (int i = 0; i < num; ++i) {
            DetectorFactory factory = factoryList.get(i);
            Boolean enabled = (Boolean) model.getValueAt(i, ENABLED_COLUMN);
            userPreferences.enableDetector(factory, enabled);
        }
    }

    private class DetectorListListener implements ListSelectionListener {

        @Override
        public void valueChanged(final ListSelectionEvent e) {
            if (e.getValueIsAdjusting()) {
                return;
            }

            ListSelectionModel lsm = (ListSelectionModel) e.getSource();
            if (!lsm.isSelectionEmpty()) {
                viewDetectorDetails(factoryList.get( detectorTable.convertRowIndexToModel(detectorTable.getSelectedRow())));
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane detectorDescription;
    private javax.swing.JScrollPane detectorDescriptionScrollPane;
    private javax.swing.JTable detectorTable;
    private javax.swing.JScrollPane detectorTableScrollPane;
    private javax.swing.JComboBox<Plugin> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton restoreDefaultsButton;
    private javax.swing.JButton restoreDefaultsButton1;
    // End of variables declaration//GEN-END:variables

    // My variables
    private ArrayList<DetectorFactory> factoryList = new ArrayList<DetectorFactory>();
}
