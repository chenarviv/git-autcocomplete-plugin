package co.chen.plugin;

import com.intellij.openapi.project.Project;
import git4idea.GitLocalBranch;
import git4idea.GitUtil;
import git4idea.commands.Git;
import git4idea.commands.GitCommandResult;
import git4idea.commands.GitImpl;
import git4idea.repo.GitRemote;
import git4idea.repo.GitRepoInfo;
import git4idea.repo.GitRepository;
import git4idea.repo.GitRepositoryManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class Panel extends JPanel implements KeyListener {
    private Project project;
    @Override
    public void keyTyped(KeyEvent e) {

        String[] args = new String[]{"ping", "www.google.com"};
        try {
            Process p = Runtime.getRuntime().exec("ping www.google.com");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("keyPressed: " + e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String[] args = new String[]{"ping", "www.google.com"};
        try {
            Process p = Runtime.getRuntime().exec("ping www.google.com");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
     /*       Robot r = null;
            try {
                String[] args = new String[] {"ping", "www.google.com"};
                Process proc = new ProcessBuilder(args).start();*/
/*
                r = new Robot();
                int keyCode = KeyEvent.VK_ENTER; // the A key
                r.keyPress(keyCode);
                int keyCode2 = KeyEvent.VK_F12;
                r.keyPress(keyCode2);
                r.keyPress(keyCode);
                r.keyPress(keyCode2);
        System.out.println("keyPressed: " + e);
*/

            // add(new EditorTextField("dddddddddd"));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        String[] args = new String[]{"ping", "www.google.com"};
        try {
            Process p = Runtime.getRuntime().exec("ping www.google.com");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Panel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public Panel(LayoutManager layout) {
        super(layout);
    }

    public Panel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public Panel() {
    }
}
