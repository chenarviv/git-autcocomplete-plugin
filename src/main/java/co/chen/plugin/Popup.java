package co.chen.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonShortcuts;
import com.intellij.openapi.keymap.KeymapUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.ComponentPopupBuilder;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.EditorTextField;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class Popup extends AnAction {
    @NotNull
    private EditorTextField myTextField;

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();

        if (project == null) {
            return;
        }

        myTextField = MultiValueAutoComplete.create(project);
        JPanel panel = new Panel(new BorderLayout());
        panel.add(myTextField, BorderLayout.CENTER);

        ComponentPopupBuilder builder = JBPopupFactory.getInstance().createComponentPopupBuilder(panel, myTextField)
                .setCancelOnClickOutside(true)
                .setAdText(KeymapUtil.getShortcutsText(CommonShortcuts.CTRL_ENTER.getShortcuts()) + " to finish")
                .setRequestFocus(true)
                .setResizable(true)
                .setMayBeParent(true);


        final JBPopup popup = builder.createPopup();
        popup.setMinimumSize(new Dimension(400, 20));
        popup.showCenteredInCurrentWindow(project);

    }

    @Override
    public boolean isDumbAware() {
        return false;
    }

}


