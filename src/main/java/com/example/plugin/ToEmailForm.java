package com.example.plugin;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnActionEvent;

import javax.swing.*;

public class ToEmailForm extends JFrame {
    private JPanel emailForm;
    private JLabel emailMessage;
    private JTextField email;
    private JButton okButton;
    private JTextField subject;
    private JLabel subjectMessage;
    private final String messageBody;
    private final AnActionEvent e;

    public ToEmailForm(String text, AnActionEvent event) {
        messageBody = text;
        e = event;
    }

    public void SendEmail() {
        setContentPane(emailForm);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        okButton.addActionListener(actionEvent -> {
            setVisible(false);
            Email.composeEmail(email.getText(), messageBody, subject.getText());
            NotificationGroup ng = NotificationGroup.findRegisteredGroup("PerformancePlugin");
            Notifications.Bus.notify(new Notification(String.valueOf(ng),
                    String.format("Message was sent to %s", email.getText()),
                    NotificationType.INFORMATION),
                    e.getProject());
//            Messages.showMessageDialog("Message sent", "Mailing", Messages.getInformationIcon());
        });

        setVisible(true);
    }


}
