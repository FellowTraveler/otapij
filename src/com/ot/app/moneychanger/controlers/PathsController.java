/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ot.app.moneychanger.controlers;

import com.ot.app.moneychanger.dialogs.panels.PathsPanel;
import com.ot.app.moneychanger.main.Concierge;
import com.ot.app.moneychanger.main.helpers.OSType.typeOS;
import com.ot.app.moneychanger.actions.AbstractActions;
import com.ot.app.moneychanger.models.viewmodel.AbstractDialog;
import com.ot.app.moneychanger.models.viewmodel.AbstractFields;
import com.ot.app.moneychanger.models.viewmodel.AbstractViewModel;
import com.ot.app.moneychanger.actions.IActions;
import com.ot.app.moneychanger.controlers.PathsController.ValidationGroups.RemoveEnabled;
import com.ot.app.moneychanger.models.viewmodel.IDialog;
import com.ot.app.moneychanger.models.viewmodel.IFields;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.AbstractListModel;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import net.sf.swing.document.DocWatcher;
import net.sf.swinglib.actions.ReturnAction;
import net.sf.swinglib.validation.AbstractValidationGroup;
import net.sf.swinglib.validation.ValidationChangedEvent;
import net.sf.swinglib.validation.ValidationChangedEventListener;

/**
 *
 * @author cameron
 */
public class PathsController {

    private static IDialog _dialog;
    private static Fields _fields;
    private Actions _actions;
    private PathsViewModel _pathsViewModel;
    private static Concierge _concierge;
    private static PathsModel _pathsModel;
    private static ReturnAction _returnAction;
    private static RemoveEnabled _removeActionEnabled;

    // <editor-fold defaultstate="collapsed" desc="PathsController Methods">
    public PathsController(Concierge concierge, ReturnAction returnAction) {
        _concierge = concierge;
        _returnAction = returnAction;

        buildDialog();
    }

    private void buildDialog() {
        _pathsModel = new PathsModel(new PathsModel.PathsListModel());
        _removeActionEnabled = new ValidationGroups().new RemoveEnabled();
        _fields = new Fields();
        _actions = new Actions(_fields);
        _pathsViewModel = new PathsViewModel(_fields, _actions);
        _dialog = new Dialog(_pathsViewModel);
    }

    public void show() {
        _pathsModel.addPaths(_returnAction.getAction());
        _dialog.show();
    }

    public void close() {
        _dialog.close();
    }

    public enum ActionKeys {

        CLOSE, ADD, REMOVE
    }

    public enum FieldKeys {
    };
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Dialog">

    private static class Dialog extends AbstractDialog {

        public Dialog(PathsViewModel prefsViewModel) {
            super(new PathsPanel(prefsViewModel,  _pathsModel), _concierge);
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Paths View Model">

    private static class PathsViewModel extends AbstractViewModel<FieldKeys, ActionKeys> {

        public PathsViewModel(IFields<FieldKeys> fields, IActions<ActionKeys> actions) {
            super(fields, actions);
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Paths Model">

    public static class PathsModel {

        private static Collection<String> _paths = new HashSet<String>();
        private int _selectedIndex;
        public PathsListModel listModel;

        PathsModel(PathsListModel listModel) {
            this.listModel = listModel;
            _selectedIndex = 0;
        }
        
        public AbstractListModel GetAbstractListModel()
        {
            return listModel;
        }

        public static class PathsListModel extends AbstractListModel {
            
            public void fireContentsChanged()
            {
                fireContentsChanged(this, 0, this.getSize());
            }
            
            @Override
            public int getSize() {
                return _paths.toArray().length;
            }

            @Override
            public Object getElementAt(int index) {
                return _paths.toArray(new String[]{})[index];
            }
        }
        
        public void setSelectedElement(int index) {
            _selectedIndex = index -1;
        }

        public Object getSelectedElement() {
            System.out.println(_paths.toArray().length);
            System.out.println(_selectedIndex);
            if (_selectedIndex < 0) _selectedIndex = 0;
            return _paths.toArray()[_selectedIndex];
        }

        @Override
        public String toString() {
            StringBuilder pathList = new StringBuilder();
            for (String path : _paths) {
                pathList.append(path);
                pathList.append(";");
            }
            return pathList.toString();
        }

        public boolean isEmpty() {
            return _paths.isEmpty();
        }

        public PathsModel(List<String> paths) {
            _paths.addAll(paths);
        }

        public void addDefultPath(typeOS os) {
            switch (os) {
                case WIN: {
                    break;
                }
                case LINUX: {
                    addPath("/usr/local/lib");
                    break;
                }
                case MAC: {
                    addPath("/usr/local/lib");
                    break;
                }
                case UNIX: {
                    addPath("/usr/local/lib");
                    break;
                }
                case OTHER: {
                    addPath("/usr/local/lib");
                    break;
                }
            }
        }

        public void addPath(String path) {
            if (path != null) {
                _paths.add(path.toLowerCase());
            }
            listModel.fireContentsChanged();
            _returnAction.returnAction(this.toString());
        }

        public void addPaths(String paths) {
            List<String> pathsList = Arrays.asList(paths.split(";"));
            for (String path : pathsList) {
                if (path != null)
                if (! path.isEmpty()) {
                    System.out.println(path.toLowerCase());
                    _paths.add(path.toLowerCase());
                }
            }

            _returnAction.returnAction(this.toString());
        }

        public void remove(Object path) {
            if (path != null) {
                _paths.remove(path);
                
                listModel.fireContentsChanged();
                _returnAction.returnAction(this.toString());
            }
        }

        public void removeSelected() {
            remove(getSelectedElement());
            setSelectedElement(_paths.toArray().length);
        }

        public Collection<String> getPaths() {
            return _paths;
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Fields">

    private static class Fields extends AbstractFields<FieldKeys> {

        public Fields() {
            super(new EnumMap<FieldKeys, Document>(FieldKeys.class),
                    new EnumMap<FieldKeys, DocWatcher>(FieldKeys.class),
                    new EnumMap<FieldKeys, Boolean>(FieldKeys.class));
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Validation Groups">

    public static class ValidationGroups {

        public class RemoveEnabled extends AbstractValidationGroup<ActionKeys> {

            public RemoveEnabled() {
                super(EnumSet.noneOf(ActionKeys.class), new EnumMap<ActionKeys, Boolean>(ActionKeys.class));
            }

            @Override
            public void getFreshValues() {
                validationItemsStates.put(ActionKeys.REMOVE, !_pathsModel.isEmpty());
            }
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Actions">

    private static class Actions extends AbstractActions<FieldKeys, ActionKeys> {

        public Actions(IFields<FieldKeys> fields) {
            super(fields, new EnumMap<ActionKeys, Action>(ActionKeys.class));
            buildEndActions();
        }

        private void buildEndActions() {
            _actionsMap.put(ActionKeys.CLOSE, new CloseAction());
            _actionsMap.put(ActionKeys.ADD, new AddAction());
            _actionsMap.put(ActionKeys.REMOVE, new RemoveAction());
            _removeActionEnabled.addValidationChangedEventListener(new ValidationChangedEventListener() {

                @Override
                public void validationChangedEventOccurred(ValidationChangedEvent evt) {
                    _actionsMap.get(ActionKeys.REMOVE).setEnabled(evt.getNewState());
                }
            });
        }

        @Override
        public IDialog getDialog() {
            return _dialog;
        }

        @Override
        public void setConfig() {
            //do nothing, no config to set.
        }

        public class CloseAction extends AbstractAction {

            public CloseAction() {
                super("Close");
            }

            @Override
            public void actionPerformed(ActionEvent ignored) {
                getDialog().close();
            }
        }

        public class RemoveAction extends AbstractAction {

            public RemoveAction() {
                super("Remove");
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                _pathsModel.removeSelected();
                _removeActionEnabled.refreshValidationGroup();
            }
        }

        public class AddAction extends AbstractAction {

            private JFileChooser pathFolderChooser;

            public AddAction() {
                super("Add");
                pathFolderChooser = new JFileChooser();
                pathFolderChooser.setCurrentDirectory(new java.io.File("."));
                pathFolderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            }

            @Override
            public void actionPerformed(ActionEvent e) {

                int returnVal = pathFolderChooser.showOpenDialog(pathFolderChooser);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    _pathsModel.addPath(pathFolderChooser.getSelectedFile().getPath());
                } else {
                    System.out.println("Cancelled");
                }
                _removeActionEnabled.refreshValidationGroup();
            }
        }
    }
    // </editor-fold>
}
