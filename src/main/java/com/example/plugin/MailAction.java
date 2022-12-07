package com.example.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import org.jetbrains.annotations.NotNull;

public class MailAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();

        if (selectedText != null) {
            ToEmailForm form = new ToEmailForm(selectedText, e);
            form.SendEmail();
        } else {
            Messages.showMessageDialog("Please select something to send a message", "Mailing", Messages.getInformationIcon());
        }

    }

    @Override
    public boolean isDumbAware() {
        return super.isDumbAware();
    }
}
