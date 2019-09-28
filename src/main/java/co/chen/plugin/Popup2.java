package co.chen.plugin;

import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonShortcuts;
import com.intellij.openapi.keymap.KeymapUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.popup.list.ListPopupImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class Popup2 extends AnAction {
    @NotNull
    private EditorTextField myTextField;

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();

        if (project == null) {
            return;
        }

        myTextField = MultiValueAutoComplete.create(project);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(myTextField, BorderLayout.CENTER);
        ListPopupImpl listPopup = (ListPopupImpl) JBPopupFactory.getInstance().createActionGroupPopup("Git", new PopupGroup(),
                e.getDataContext(), JBPopupFactory.ActionSelectionAid.NUMBERING, true, e.getPlace());
        listPopup.setAdText(KeymapUtil.getShortcutsText(CommonShortcuts.CTRL_ENTER.getShortcuts()) + " to finish");
        listPopup.setRequestFocus(true);

        listPopup.setMinimumSize(new Dimension(500, 20));
        listPopup.showCenteredInCurrentWindow(project);
    }

    @Override
    public boolean isDumbAware() {
        return false;
    }

    private class PopupGroup extends ActionGroup {

        @NotNull
        @Override
        public AnAction[] getChildren(@Nullable AnActionEvent e) {

            return new AnAction[0];
        }

    }

}