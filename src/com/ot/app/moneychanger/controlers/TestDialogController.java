///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.ot.app.moneychanger.controlers;
//
//import com.ot.app.moneychanger.dialogs.panels.TestPanel;
//import com.ot.app.moneychanger.main.Concierge;
//import com.ot.app.moneychanger.main.ConfigBean;
//import java.awt.Color;
//import java.awt.event.ActionEvent;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//import javax.swing.AbstractAction;
//import javax.swing.JDialog;
//import javax.swing.JTextField;
//import javax.swing.event.DocumentListener;
//import net.sf.swinglib.SwingUtil;
//import net.sf.swinglib.UIHelper;
//import net.sf.swinglib.actions.DocumentCheckReturn;
//import net.sf.swinglib.actions.ReturnAction;
//import net.sf.swinglib.field.DocumentWatcher;
//
///**
// *
// * @author cameron
// */
//public class TestDialogController {
//
//    private Concierge _concierge;
//    private JDialog _testDialog;
//    private TestPanel _testPanel;
//    private Set<DocumentListener> _documentListenerSet;
//    private Map<String, Boolean> _isReadyMap;
//    private ConfigBean _prefs;
//    private OKAction _bOk;
//
//    public TestDialogController(Concierge concierge) {
//        _concierge = concierge;
//    }
//
//    public void Show() {
//        if (_testDialog == null) {
//            buildTestPanel();
//            buildFields();
//            buildTestDialog();
//            Refresh();
//        }
//
//        _testDialog.pack();
//        SwingUtil.centerAndShow(_testDialog, _concierge.getDialogOwner());
//    }
//
//    private void buildTestPanel() {
//        TestPanel tp = new TestPanel();
//        _testPanel = tp;
//    }
//
//    private void buildFields() {
//        _isReadyMap = new HashMap<String, Boolean>();
//        _documentListenerSet = new HashSet<DocumentListener>();
//        _testPanel.jTextField_Test.setText(_concierge.getConfig().getTimeOut());
//        _documentListenerSet.add(testTextField(_testPanel.jTextField_Test, ".+\\.test\\.com", _isReadyMap));
//
//        _testPanel.jButton_Test.setAction(new OKAction());
//    }
//
//    private DocumentListener testTextField(final JTextField field, final String regex, final Map<String, Boolean> isReady) {
//        DocumentCheckReturn testDocReturn = new DocumentCheckReturn() {
//
//            @Override
//            public void Valid() {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//
//            @Override
//            public void Invalid() {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//                
//        };
//        ReturnAction a =new ReturnAction() {
//
//            @Override
//            public void ReturnAction() {
//                throw new UnsupportedOperationException("Not supported yet.");
//            }
//        };
//        
//        return new DocumentWatcher(field.getDocument(), testDocReturn, regex,a );
//    }
//    
//       
//    
//    
//    private void Refresh() {
//        boolean ready = true;
//        for (boolean b : _isReadyMap.values()) {
//            ready = b;
//            if (!ready) {
//                break;
//            }
//        }
//        _testPanel.jButton_Test.setEnabled(ready);
//    }
//
//    private void buildTestDialog() {
//        _testDialog = UIHelper.newDialog(_concierge.getDialogOwner(), "Preferences", _testPanel);
//    }
//
//    private class OKAction extends AbstractAction {
//
//        public OKAction() {
//            super("OK");
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent ignored) {
//            // note: this dialog will be rarely used; no reason to leave
//            //       it around, consuming resources
//            _testDialog.dispose();
//        }
//    }
//}
